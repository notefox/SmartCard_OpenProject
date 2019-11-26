package com.example.nfcapp.Persist_Save;

import com.example.nfcapp.BCardObject.BCardObject;
import com.example.nfcapp.BCardObject.UserObject;

import org.junit.Before;
import org.junit.Test;
import org.junit.runners.Parameterized;

import java.util.Collection;

import static org.junit.Assert.*;

public class PersistentFileCreatorTestMainClass {

    PersistentFileCreator fileCreator = new PersistentFileCreatorMock();
    BCardObject bcard;

    @Before
    public void setUp() throws Exception {
        bcard = new UserObject(0,"testName", "CEO");
    }

    @Test
    public void createDesktopFile() {

    }

    @Test
    public void getDesktopFile() {

    }

    @Test
    public void getAllExistingFiles() {

    }
}