package com.example.nfcapp;

import android.app.Application;
import android.util.Log;
import android.widget.Toast;

import com.example.nfcapp.BusinessCardDir.BusinessCardItem;
import com.example.nfcapp.BusinessCardDir.CorporateTitle;
import com.google.gson.Gson;

import java.io.FileNotFoundException;
import java.io.IOException;

import static androidx.constraintlayout.widget.Constraints.TAG;


public class ApplicationClass extends Application {
    static Gson gson = new Gson();


    @Override
    public void onCreate() {
        super.onCreate();

        try {
            fetchItemList();
        } catch (IOException e) {
            e.printStackTrace();
        }
        fetchLocalID();

    }

    void fetchItemList() throws IOException {
        boolean stop = false;
        int i;
        for (i = 0; !stop ; i++) {
            try {

                BusinessCardItem temp = new GeneralMethodsImpl().load(Database.FILE_PREFIX_NORMAL_BC + i + Database.getBcFileSuffix(), this);

                if (temp == null)
                    stop = true;
                else
                    Database.addItem(temp);

            } catch (FileNotFoundException e) {
                stop = true;
            } catch (IOException e) {
                stop = true;
            }
        }

        //Toast.makeText(this, "found items: " + (i), Toast.LENGTH_LONG).show();
    }

    void fetchLocalID() {
        try {
            readLocalID();
            //Database.addItem(Database.getLocalID());
        } catch (Exception e) {
            Log.e(TAG, "no pre-used File found", e);
            Database.setLocalID(new BusinessCardItem(null, new GeneralMethodsImpl().createWhiteBitmap(10,10), "(no name)", "(no company)", CorporateTitle.Null));
            Database.addItem(Database.getLocalID());
        }
    }

    private void readLocalID() throws IOException {
            Database.setLocalID(new GeneralMethodsImpl().load(Database.getLocalBcFileName(), this));
            Toast.makeText(this, "localID loaded from " + getFilesDir() + "/" + Database.getLocalBcFileName(), Toast.LENGTH_LONG).show();
            //Toast.makeText(this, sb.toString(), Toast.LENGTH_LONG).show();
    }
}
