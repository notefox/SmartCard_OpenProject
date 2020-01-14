package com.example.nfcapp.NFC;

import android.content.Intent;
import android.nfc.NfcAdapter;

import java.util.ArrayList;

public interface NFCHandeable extends NfcAdapter.OnNdefPushCompleteCallback,
        NfcAdapter.CreateNdefMessageCallback {

    /**
     * Check if NFC is available on device
     * @return true if NFC is available on device
     */
    boolean checkNFC();

    /**
     * Add a message to send to ArrayList
     * @param newMessage String
     */
    void addMessageToSend(String newMessage);

    /**
     * Read NdefMessage and write in ArrayList
     * @param NfcIntent Intent
     * @return received messages as ArrayList
     */
    ArrayList<String> handleNfcIntent(Intent NfcIntent);
}
