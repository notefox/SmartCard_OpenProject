package com.example.nfcapp;

import android.content.Context;
import android.graphics.Bitmap;

import com.example.nfcapp.BusinessCardDir.BusinessCardItem;

import java.io.FileNotFoundException;
import java.io.IOException;

public interface GeneralMethods {

    /**
     * Method to save a BusinessCardItem in it's own File
     * @param item item to save
     * @param fileName fileName
     * @param context main Context
     * @throws IOException is thrown if the File couldn't be accessed
     */
    void save(BusinessCardItem item, String fileName, Context context) throws IOException;

    /**
     * Method to load a BusinessCardItem from a File
     * @param fileName fileName
     * @param context main Context
     * @return the BusinessCardItem from the File
     * @throws IOException is thrown if the File couldn't be accessed
     */
    BusinessCardItem load(String fileName, Context context) throws IOException;

    /**
     * deletes a file in the Internal Storage
     * @param name name of the File
     * @param context main Context
     * @return true -> file was deleted, false -> it wasn't
     * @throws FileNotFoundException is thrown if the File couldn't be found
     */
    boolean deleteFile(String name, Context context) throws FileNotFoundException;

    /**
     * encodes a Bitmap to a String which can be decoded in the "stringToBitmap" Method
     * @param bitmap bitmap to encode
     * @return the encoded String
     * @throws NullPointerException is thrown if the bitmap was null
     */
    String bitmapToString(Bitmap bitmap) throws NullPointerException;

    /**
     * decodes the Bitmap String from "bitmapToString"
     * @param encodedString encoded String which holds the Bitmap
     * @return decoded Bitmap
     * @throws Exception is thrown if the string couldn't be decoded
     */
    Bitmap stringToBitMap(String encodedString) throws Exception;

    /**
     * creates a completelywhite Bitmap
     * @param x width
     * @param y height
     * @return the white Bitmap
     * @throws Exception is thrown if the x or y where negative
     */
    Bitmap createWhiteBitmap(int x, int y) throws Exception;
}
