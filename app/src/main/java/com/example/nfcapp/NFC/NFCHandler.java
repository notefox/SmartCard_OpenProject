package com.example.nfcapp.NFC;

import android.app.Activity;
import android.content.Intent;
import android.nfc.NdefMessage;
import android.nfc.NdefRecord;
import android.nfc.NfcAdapter;
import android.nfc.NfcEvent;
import android.os.Build;
import android.os.Parcelable;

import androidx.fragment.app.Fragment;

import java.nio.charset.Charset;
import java.util.ArrayList;

public class NFCHandler extends Fragment implements NfcAdapter.OnNdefPushCompleteCallback,
        NfcAdapter.CreateNdefMessageCallback{

    //The array lists to hold our messages
    private ArrayList<String> messagesToSendArray = new ArrayList<>();
    private ArrayList<String> messagesReceivedArray = new ArrayList<>();

    private Activity activity;

    private NfcAdapter mNfcAdapter;

    public NFCHandler(Activity act, NfcAdapter adapter) {
        this.activity = act;
        this.mNfcAdapter = adapter;
    }

    /**
     * Check if NFC is available on device
     * @return true if NFC is available on device
     */
    public boolean checkNFC() {
        if(mNfcAdapter != null) {
            //This will refer back to createNdefMessage for what it will send
            mNfcAdapter.setNdefPushMessageCallback(this, activity);

            //This will be called if the message is sent successfully
            mNfcAdapter.setOnNdefPushCompleteCallback(this, activity);

            return true;
        }
        else {
            return false;
        }
    }

    /**
     * Add a message to ArrayList
     * @param newMessage String
     */
    public void addMessageToSend(String newMessage) {
        messagesToSendArray.add(newMessage);
    }

    /**
     * Converts messages to NdefRecord
     * @return NdefRecord
     */
    private NdefRecord[] createRecords() {

        NdefRecord[] records = new NdefRecord[messagesToSendArray.size() + 1];
        //To Create Messages Manually if API is less than
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.JELLY_BEAN) {
            for (int i = 0; i < messagesToSendArray.size(); i++){
                byte[] payload = messagesToSendArray.get(i).
                        getBytes(Charset.forName("UTF-8"));
                NdefRecord record = new NdefRecord(
                        NdefRecord.TNF_WELL_KNOWN,      //Our 3-bit Type name format
                        NdefRecord.RTD_TEXT,            //Description of our payload
                        new byte[0],                    //The optional id for our Record
                        payload);                       //Our payload for the Record

                records[i] = record;
            }
        }
        //Api is high enough that we can use createMime, which is preferred.
        else {
            for (int i = 0; i < messagesToSendArray.size(); i++){
                byte[] payload = messagesToSendArray.get(i).
                        getBytes(Charset.forName("UTF-8"));

                NdefRecord record = NdefRecord.createMime("text/plain",payload);
                records[i] = record;
            }
        }
        records[messagesToSendArray.size()] =
                NdefRecord.createApplicationRecord(activity.getPackageName());
        return records;
    }

    /**
     * Read NdefMessage and write in ArrayList
     * @param NfcIntent Intent
     * @return received messages as ArrayList
     */
    public ArrayList<String> handleNfcIntent(Intent NfcIntent) {
        if (NfcAdapter.ACTION_NDEF_DISCOVERED.equals(NfcIntent.getAction())) {
            Parcelable[] receivedArray =
                    NfcIntent.getParcelableArrayExtra(NfcAdapter.EXTRA_NDEF_MESSAGES);

            if(receivedArray != null) {
                messagesReceivedArray.clear();
                NdefMessage receivedMessage = (NdefMessage) receivedArray[0];
                NdefRecord[] attachedRecords = receivedMessage.getRecords();

                for (NdefRecord record:attachedRecords) {
                    String string = new String(record.getPayload());
                    //Make sure we don't pass along our AAR (Android Application Record)
                    if (string.equals(activity.getPackageName())) { continue; }
                    messagesReceivedArray.add(string);
                }
                //Toast.makeText(activity, "Received " + messagesReceivedArray.size() + " Messages", Toast.LENGTH_LONG).show();
            }
            //else {
            //    Toast.makeText(activity, "Received Blank Parcel", Toast.LENGTH_LONG).show();
            //}
        }
        return this.messagesReceivedArray;
    }

    @Override
    public NdefMessage createNdefMessage(NfcEvent nfcEvent) {
        //This will be called when another NFC capable device is detected.
        if (messagesToSendArray.size() == 0) {
            return null;
        }
        //We'll write the createRecords() method in just a moment
        NdefRecord[] recordsToAttach = createRecords();
        //When creating an NdefMessage we need to provide an NdefRecord[]
        return new NdefMessage(recordsToAttach);
    }

    @Override
    public void onNdefPushComplete(NfcEvent nfcEvent) {
        //This is called when the system detects that our NdefMessage was
        //Successfully sent.
        messagesToSendArray.clear();
    }
}
