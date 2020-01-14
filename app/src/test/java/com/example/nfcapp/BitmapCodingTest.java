package com.example.nfcapp;

import android.graphics.Bitmap;

import org.junit.Before;
import org.junit.Test;

import java.math.BigInteger;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;

public class BitmapCodingTest {

    Bitmap bitmap;
    GeneralMethods mockMethods;

    @Before
    public void setUp() throws Exception {
        mockMethods = new GeneralMethodsImpl();
        bitmap = mockMethods.createWhiteBitmap(1,1);
    }

    @Test
    public void conversionTest() {
        String encodedString = mockMethods.bitmapToString(bitmap);
        Bitmap compare = null;
        try {
            compare = mockMethods.stringToBitMap(encodedString);
        } catch (Exception e) {
            fail();
        }

        assertEquals(bitmap,compare);
    }

    @Test
    public void NullTest() {
        bitmap = null;
        String encodedString = mockMethods.bitmapToString(bitmap);
        Bitmap compare = null;
        try {
            compare = mockMethods.stringToBitMap(encodedString);
        } catch (Exception e) {
            fail();
        }

        assertEquals(bitmap, compare);
    }

    @Test
    public void wrongStringTest() {
        String wrongString = "not a bitmap";

        try {
            mockMethods.stringToBitMap(wrongString);
        } catch (Exception e) {
            return;
        }

        fail();
    }
}