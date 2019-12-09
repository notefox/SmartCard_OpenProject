package com.example.nfcapp;

import android.content.Intent;
import android.nfc.NfcAdapter;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.nfcapp.NFC.NFCHandler;
import com.google.gson.Gson;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private boolean active1 = false;
    private boolean active2 = false;

    //The array lists to hold our messages
    private ArrayList<String> messagesToSendArray = new ArrayList<>();
    private ArrayList<String> messagesReceivedArray = new ArrayList<>();

    //Text boxes to add and display our messages
    private EditText txtBoxAddMessage;
    private EditText txtBoxAddMessage2;
    private TextView txtReceivedMessages;
    private TextView txtMessagesToSend;

    private NfcAdapter mNfcAdapter;
    private NFCHandler nfc;

    //Buttom addMessage
    public void addMessage(View view) {
        String s1 = txtBoxAddMessage.getText().toString();
        String s2 = txtBoxAddMessage2.getText().toString();
        testObject t = new testObject(s1, s2);
        Gson g = new Gson();
        String newMessage = g.toJson(t);
        messagesToSendArray.add(newMessage);
        nfc.addMessageToSend(newMessage);

        txtBoxAddMessage.setText(null);
        txtBoxAddMessage2.setText(null);
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
        testObject t = g.fromJson(s, testObject.class);
        return "Text1: " + t.get1() + "\n" + "Text2: " + t.get2() + "\n";
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

        txtBoxAddMessage = findViewById(R.id.txtBoxAddMessage);
        txtBoxAddMessage2 = findViewById(R.id.txtBoxAddMessage2);
        txtMessagesToSend = findViewById(R.id.txtMessageToSend);
        txtReceivedMessages = findViewById(R.id.txtMessagesReceived);
        final Button btnAddMessage = findViewById(R.id.buttonAddMessage);

        btnAddMessage.setClickable(false);

        txtBoxAddMessage.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                if (editable.length() > 0) {
                    active1 = true;
                    if (active2)
                        btnAddMessage.setClickable(true);
                }
                else {
                    active1 = false;
                    btnAddMessage.setClickable(false);
                }
            }
        });

        txtBoxAddMessage2.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                if (editable.length() > 0) {
                    active2 = true;
                    if (active1)
                        btnAddMessage.setClickable(true);
                }
                else {
                    active2 = false;
                    btnAddMessage.setClickable(false);
                }
            }
        });

        btnAddMessage.setText("Add Message");
        updateTextViews();


        mNfcAdapter = NfcAdapter.getDefaultAdapter(this);
        nfc = new NFCHandler(this, mNfcAdapter);

        nfc.checkNFC(); //Check if NFC is available on device
    }

    @Override
    public void onNewIntent(Intent intent) {

        //TODO combine handleNfcIntent and getMessagesReceived together
        nfc.handleNfcIntent(intent);
        messagesReceivedArray = nfc.getMessagesReceived();

        updateTextViews();
    }

    @Override
    public void onResume() {
        super.onResume();

        updateTextViews(); //TODO, test if this one is too much updating

        //TODO handleNfcIntent und getMessagesRecived zusammenfügen
        nfc.handleNfcIntent(getIntent());
        messagesReceivedArray = nfc.getMessagesReceived();

        updateTextViews();
    }
}
