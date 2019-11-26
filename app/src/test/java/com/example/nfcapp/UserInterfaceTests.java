package com.example.nfcapp;

import com.example.nfcapp.BCardObject.BCardObject;
import com.example.nfcapp.BCardObject.UserObject;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.File;

import static org.junit.Assert.*;

public class UserInterfaceTests {

    BCardObject uo = new UserObject();
    UserInterface ui = new UIMock();


    @Before
    public void setUp() throws Exception {

    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void addNewBCard() {
        uo.addMail("test.mail@something.com");
        uo.addNumber("03056698323");
    }

    @Test
    public void addToFav() {

    }

    @Test
    public void shareNFC() {

    }

    @Test
    public void addToContact() {

    }
}