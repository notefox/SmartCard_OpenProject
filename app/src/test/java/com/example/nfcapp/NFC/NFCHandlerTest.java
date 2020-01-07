package com.example.nfcapp.NFC;

import android.app.Activity;
import android.content.Intent;
import android.nfc.NdefMessage;
import android.nfc.NdefRecord;
import android.nfc.NfcAdapter;
import android.nfc.NfcEvent;
import android.os.Parcelable;

import org.junit.Test;
import org.mockito.Mockito;

import static org.mockito.Mockito.mock;
import static org.junit.Assert.*;

public class NFCHandlerTest {

    @Test
    public void checkNFCTrue() {
        Activity mockActivity = mock(Activity.class);
        NfcAdapter mockAdapter = mock(NfcAdapter.class);
        NFCHandler nfc = new NFCHandler(mockActivity, mockAdapter);
        assertTrue(nfc.checkNFC());
    }

    @Test
    public void checkNFCFalse() {
        Activity mockActivity = mock(Activity.class);
        NFCHandler nfc = new NFCHandler(mockActivity, null);
        assertFalse(nfc.checkNFC());
    }

    @Test
    public void handleNfcIntent() {
        String test = "test";
        Activity mockActivity = mock(Activity.class);
        NfcAdapter mockAdapter = mock(NfcAdapter.class);
        Intent mockIntent = mock(Intent.class);
        NdefMessage mockMessage = mock(NdefMessage.class);
        Parcelable[] messages = {mockMessage};
        NdefRecord mockRecord = mock(NdefRecord.class);
        NdefRecord[] records = {mockRecord};
        Mockito.when(mockIntent.getAction()).thenReturn(NfcAdapter.ACTION_NDEF_DISCOVERED);
        Mockito.when(mockIntent.getParcelableArrayExtra(NfcAdapter.EXTRA_NDEF_MESSAGES)).thenReturn(messages);
        Mockito.when(mockMessage.getRecords()).thenReturn(records);
        Mockito.when(mockRecord.getPayload()).thenReturn(test.getBytes());
        NFCHandler nfc = new NFCHandler(mockActivity, mockAdapter);

        assertEquals("[test]", nfc.handleNfcIntent(mockIntent).toString());

        Mockito.verify(mockIntent).getParcelableArrayExtra(NfcAdapter.EXTRA_NDEF_MESSAGES);
        Mockito.verify(mockMessage).getRecords();
        Mockito.verify(mockRecord).getPayload();
    }

    @Test
    public void handleNfcIntentNull() {
        Activity mockActivity = mock(Activity.class);
        NfcAdapter mockAdapter = mock(NfcAdapter.class);
        Intent mockIntent = mock(Intent.class);
        Mockito.when(mockIntent.getAction()).thenReturn(NfcAdapter.ACTION_NDEF_DISCOVERED);
        Mockito.when(mockIntent.getParcelableArrayExtra(NfcAdapter.EXTRA_NDEF_MESSAGES)).thenReturn(null);
        NFCHandler nfc = new NFCHandler(mockActivity, mockAdapter);

        assertEquals("[]", nfc.handleNfcIntent(mockIntent).toString());

        Mockito.verify(mockIntent).getParcelableArrayExtra(NfcAdapter.EXTRA_NDEF_MESSAGES);
    }

    @Test
    public void createNdefMessage() {
        Activity mockActivity = mock(Activity.class);
        NfcAdapter mockAdapter = mock(NfcAdapter.class);
        NfcEvent mockEvent = mock(NfcEvent.class);
        //NdefRecord mockRecord = mock(NdefRecord.class);
        Mockito.when(mockActivity.getPackageName()).thenReturn("");

        NFCHandler nfc = new NFCHandler(mockActivity, mockAdapter);
        nfc.addMessageToSend("test");

        System.out.println(nfc.createNdefMessage(mockEvent).toString());
    }

    @Test
    public void createNdefMessageNull() {
        Activity mockActivity = mock(Activity.class);
        NfcAdapter mockAdapter = mock(NfcAdapter.class);
        NfcEvent mockEvent = mock(NfcEvent.class);

        NFCHandler nfc = new NFCHandler(mockActivity, mockAdapter);

        assertEquals(null, nfc.createNdefMessage(mockEvent));
    }
}