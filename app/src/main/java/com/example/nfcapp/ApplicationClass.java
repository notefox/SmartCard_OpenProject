package com.example.nfcapp;

import android.app.Application;
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
        Database.addItem(new BusinessCardItem(R.drawable.ic_wallpaper_black_24dp, "person 1", "company 1", CorporateTitle.Chief_executive_officer));
        Database.addItem(new BusinessCardItem(R.drawable.ic_email_black_24dp, "person 2", "company 2", CorporateTitle.Chief_financial_officer));
        Database.addItem(new BusinessCardItem(R.drawable.ic_favorite_black_24dp, "person 3", "company 3", CorporateTitle.Chief_operating_officer));
    }

    void fetchLocalID() {
        try {
            readLocalID();
            Database.addItem(Database.getLocalID());
        } catch (Exception e) {
            Log.e(TAG, "onCreate: ", e);
            try {
                createNewLocalFile();
                Database.addItem(Database.getLocalID());
            } catch (Exception ex) {
                Log.e(TAG, "onCreate: ", ex);
                ex.printStackTrace();
            }
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
            Toast.makeText(this, Database.getLocalID().toString(), Toast.LENGTH_LONG).show();
        } finally {
            if (fos != null)
            fis.close();
        }
    }

    private void createNewLocalFile() throws IOException {
        Database.setLocalID(new BusinessCardItem(R.drawable.ic_favorite_black_24dp, "local", "company", CorporateTitle.Chief_product_officer));
        try {
            fos = openFileOutput(Database.getLocalBcFileName(), MODE_PRIVATE);
            fos.write(gson.toJson(Database.getLocalID()).getBytes());
            Toast.makeText(this, "Saved to " + getFilesDir() + "/" + Database.getLocalBcFileName(), Toast.LENGTH_LONG).show();
        } finally {
            if (fos != null) {
                fos.close();
            }
        }

    }
}
