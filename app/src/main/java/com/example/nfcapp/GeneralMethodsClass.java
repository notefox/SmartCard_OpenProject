package com.example.nfcapp;

import android.app.Application;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.example.nfcapp.BusinessCardDir.BusinessCardItem;
import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class GeneralMethodsClass extends Application {

    FileInputStream fis;
    FileOutputStream fos;

    public void save(BusinessCardItem item, String fileName, Context context) throws IOException {
        try {
            fos = context.openFileOutput(fileName, MODE_PRIVATE);

            fos.write(new Gson().toJson(item).getBytes());
        } finally {
            if (fos != null) {
                fos.close();
            }
        }
    }

    public BusinessCardItem load(String fileName, Context context) throws IOException {
        BusinessCardItem loaded = null;
        try {
            fis = context.openFileInput(fileName);
            InputStreamReader isr = new InputStreamReader(fis);
            BufferedReader br = new BufferedReader(isr);

            StringBuilder sb = new StringBuilder();

            while (fis.available() > 0) {
                sb.append(br.readLine());
            }

            loaded = new Gson().fromJson(sb.toString(), BusinessCardItem.class);

            if (loaded == null) {
                throw new FileNotFoundException();
            }
        } finally {
            if (fis != null) {
                fis.close();
            }
            return loaded;
        }
    }

    @Override
    public boolean deleteFile(String name) {
        return super.deleteFile(name);
    }

    public String bitmapToString(Bitmap bitmap) {

        if (bitmap == null)
            return null;

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, baos);
        byte[] b = baos.toByteArray();
        String temp = new Gson().toJson(b);
        return temp;
    }

    public Bitmap stringToBitMap(String encodedString) {

        if (encodedString == null)
            return null;

        try {
            byte[] encodeByte = new Gson().fromJson(encodedString, byte[].class);
            Bitmap bitmap = BitmapFactory.decodeByteArray(encodeByte, 0, encodeByte.length);
            return bitmap;
        } catch (Exception e) {
            e.getMessage();
            return null;
        }
    }

    public Bitmap createWhiteBitmap(int x, int y) {
        Bitmap.Config conf = Bitmap.Config.ARGB_8888; // see other conf types
        Bitmap bmp = Bitmap.createBitmap(x, y, conf); // this creates a MUTABLE bitmap

        return bmp;
    }
}
