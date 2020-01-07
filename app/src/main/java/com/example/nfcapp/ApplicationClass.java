package com.example.nfcapp;

import android.app.Application;
import android.provider.ContactsContract;
import android.util.Log;
import android.widget.Toast;

import com.example.nfcapp.BusinessCardDir.BusinessCardItem;
import com.example.nfcapp.BusinessCardDir.CorporateTitle;
import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;

import static androidx.constraintlayout.widget.Constraints.TAG;


public class ApplicationClass extends Application {
    static Gson gson = new Gson();

    FileInputStream fis;
    FileOutputStream fos;

    @Override
    public void onCreate() {
        super.onCreate();

        fetchItemList();
        fetchLocalID();

    }

    void fetchItemList() {

    }

    void fetchLocalID() {
        try {
            readLocalID();
            Database.addItem(Database.getLocalID());
        } catch (Exception e) {
            Log.e(TAG, "no pre-used File found", e);
        }
    }

    private void readLocalID() throws IOException {
        try {
            fis = openFileInput(Database.getLocalBcFileName());
            InputStreamReader isr = new InputStreamReader(fis);
            BufferedReader br = new BufferedReader(isr);

            StringBuilder sb = new StringBuilder();

            String tempText;
            while ((tempText = br.readLine()) != null) {
                sb.append(tempText);
            }

            Database.setLocalID(gson.fromJson(sb.toString(), BusinessCardItem.class));
            Toast.makeText(this, "localID loaded from " + getFilesDir() + "/" + Database.getLocalBcFileName(), Toast.LENGTH_LONG).show();
            Toast.makeText(this, sb.toString(), Toast.LENGTH_LONG).show();
        } finally {
            if (fos != null)
            fis.close();
        }
    }
}
