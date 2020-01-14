package com.example.nfcapp.GeneralMethodsTests;

import android.app.Activity;
import android.nfc.NfcAdapter;
import android.nfc.NfcEvent;

import com.example.nfcapp.BusinessCardDir.BusinessCardItem;
import com.example.nfcapp.BusinessCardDir.CorporateTitle;
import com.example.nfcapp.GeneralMethods;
import com.example.nfcapp.GeneralMethodsImpl;
import com.example.nfcapp.NFC.NFCHandler;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;

public class FileTest {

    Activity mockActivity;
    GeneralMethods mockMethods;
    BusinessCardItem item;
    File file;

    @Before
    public void setUp() throws Exception {
        mockActivity = mock(Activity.class);
        mockMethods = new GeneralMethodsImpl();
        item = new BusinessCardItem("test.bcf",mockMethods.createWhiteBitmap(1,1) ,"name", "company", CorporateTitle.Chief_executive_officer);
        file = new File("test.bcf");
    }

    @Test
    public void saveAndLoadTest() {
        try {
            mockMethods.save(item, file.getName(), mockActivity);
        } catch (IOException e) {
            fail();
        }
        BusinessCardItem compare = null;
        try {
            compare = mockMethods.load(file.getName(), mockActivity);
        } catch (IOException e) {
            fail();
        }
        assertEquals(item.toTerminalString(), compare.toTerminalString());
    }

    @Test
    public void deleteFile() {
        try {
            mockMethods.deleteFile(file.getName(), mockActivity);
        } catch (FileNotFoundException e) {
            fail();
        }
    }
}