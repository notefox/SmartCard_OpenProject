package com.example.nfcapp;

import android.content.Intent;
import android.nfc.NfcAdapter;
import android.os.Bundle;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.nfcapp.BusinessCardDir.BusinessCardItem;
import com.example.nfcapp.BusinessCardDir.CorporateTitle;
import com.example.nfcapp.NFC.NFCHandler;
import com.google.gson.Gson;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private BusinessCardItem card;

    //The array lists to hold our messages
    private ArrayList<String> messagesToSendArray = new ArrayList<>();
    private ArrayList<String> messagesReceivedArray = new ArrayList<>();

    //Text boxes to add and display our messages
    private EditText txtBoxAddMessage1;
    private EditText txtBoxAddMessage2;
    private EditText txtBoxAddMessage3;
    private EditText txtBoxAddMessage4;
    private TextView txtReceivedMessages;
    private TextView txtMessagesToSend;
    private Button btnAddMessage;

    private NfcAdapter mNfcAdapter;
    private NFCHandler nfc;

    //Buttom addMessage
    public void addMessage(View view) {
        String s1 = txtBoxAddMessage1.getText().toString();
        String s2 = txtBoxAddMessage2.getText().toString();
        String s3 = txtBoxAddMessage3.getText().toString();
        String s4 = txtBoxAddMessage4.getText().toString();
        card = new BusinessCardItem(null, s1, CorporateTitle.Chief_brand_officer, s2, s3, s4, "");
        Gson g = new Gson();
        String newMessage = g.toJson(card);
        messagesToSendArray.add(newMessage);
        nfc.addMessageToSend(newMessage);

        txtBoxAddMessage1.setText(null);
        txtBoxAddMessage2.setText(null);
        txtBoxAddMessage3.setText(null);
        txtBoxAddMessage4.setText(null);
        updateTextViews();

        Toast.makeText(this, "Added Message", Toast.LENGTH_LONG).show();
    }

    private void updateTextViews() {
        txtMessagesToSend.setText("Messages To Send:\n");
        //Populate Our list of messages we want to send
        if(messagesToSendArray.size() > 0) {
            for (int i = 0; i < messagesToSendArray.size(); i++) {
                txtMessagesToSend.append(getJson(messagesToSendArray.get(i)));
                txtMessagesToSend.append("\n");
            }
        }

        txtReceivedMessages.setText("Messages Received:\n");
        //Populate our list of messages we have received
        if (messagesReceivedArray.size() > 0) {
            for (int i = 0; i < messagesReceivedArray.size(); i++) {
                txtReceivedMessages.append(getJson(messagesReceivedArray.get(i)));
                txtReceivedMessages.append("\n");
            }
        }
    }

    private String getJson(String s) {
        Gson g = new Gson();
        BusinessCardItem temp = g.fromJson(s, BusinessCardItem.class);
        return "Name: " + temp.getName() + "\n"+ "Company: " + temp.getCompanyName() + "\n" + "Adress: " + temp.getAddress() + "\n" + "Phone: " + temp.getPhoneNumber() + "\n" + "Email: " + temp.getEmail() + "\n";
    }

    //Save our Array Lists of Messages for if the user navigates away
    @Override
    public void onSaveInstanceState(@NonNull Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);
        savedInstanceState.putStringArrayList("messagesToSend", messagesToSendArray);
        savedInstanceState.putStringArrayList("lastMessagesReceived",messagesReceivedArray);
    }

    //Load our Array Lists of Messages for when the user navigates back
    @Override
    public void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        messagesToSendArray = savedInstanceState.getStringArrayList("messagesToSend");
        messagesReceivedArray = savedInstanceState.getStringArrayList("lastMessagesReceived");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtBoxAddMessage1 = findViewById(R.id.txtBoxAddMessage1);
        txtBoxAddMessage2 = findViewById(R.id.txtBoxAddMessage2);
        txtBoxAddMessage3 = findViewById(R.id.txtBoxAddMessage3);
        txtBoxAddMessage4 = findViewById(R.id.txtBoxAddMessage4);
        txtMessagesToSend = findViewById(R.id.txtMessageToSend);
        txtReceivedMessages = findViewById(R.id.txtMessagesReceived);
        btnAddMessage = findViewById(R.id.buttonAddMessage);

        btnAddMessage.setClickable(false);

        btnAddMessage.setText("Add Message");

        final ButtonControl control = new ButtonControl(findViewById(R.id.txtBoxAddMessage1)
                                                        , findViewById(R.id.txtBoxAddMessage2)
                                                        , findViewById(R.id.txtBoxAddMessage3)
                                                        , findViewById(R.id.txtBoxAddMessage4)
                                                        , findViewById(R.id.buttonAddMessage));

        txtBoxAddMessage1.addTextChangedListener(control);
        txtBoxAddMessage2.addTextChangedListener(control);
        txtBoxAddMessage3.addTextChangedListener(control);
        txtBoxAddMessage4.addTextChangedListener(control);
        updateTextViews();


        mNfcAdapter = NfcAdapter.getDefaultAdapter(this);
        nfc = new NFCHandler(this, mNfcAdapter);

        //Check if NFC is available on device
        if (!nfc.checkNFC()) {
            Toast.makeText(this, "NFC not available on this device",
                    Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onNewIntent(Intent intent) {
        messagesReceivedArray = nfc.handleNfcIntent(intent);
        if (messagesReceivedArray != null)
            Toast.makeText(this, "Received " + messagesReceivedArray.size() + " Messages", Toast.LENGTH_LONG).show();
        else
            Toast.makeText(this, "Received Blank Parcel", Toast.LENGTH_LONG).show();
        updateTextViews();
    }

    @Override
    public void onResume() {
        super.onResume();
        messagesReceivedArray = nfc.handleNfcIntent(getIntent());

        updateTextViews();
    }
}
