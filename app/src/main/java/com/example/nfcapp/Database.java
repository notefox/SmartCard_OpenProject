package com.example.nfcapp;

import android.app.Application;
import android.provider.ContactsContract;
import android.util.Log;
import android.widget.Toast;

import com.example.nfcapp.BusinessCardDir.BusinessCardItem;
import com.google.gson.Gson;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.AbstractList;
import java.util.Vector;

import static android.content.ContentValues.TAG;

public class Database extends Application {
    private static final String BC_FILE_SUFFIX = ".bcf";
    private static String LOCAL_BC_FILE_NAME = "local" + BC_FILE_SUFFIX;

    private static AbstractList<BusinessCardItem> itemList = new Vector<>();
    private static AbstractList<BusinessCardItem> itemList_fav = new Vector<>();

    private static BusinessCardItem localID;

    public static BusinessCardItem getLocalID() {
        return localID;
    }

    public static void setLocalID(BusinessCardItem localID) {
        Database.localID = localID;
    }

    public static void addItem(BusinessCardItem item) {
        Database.itemList.add(item);
    }
    public static void addFav(BusinessCardItem item) {
        Database.itemList_fav.add(item);
    }

    public static AbstractList<BusinessCardItem> getItemList() {
        return itemList;
    }

    public static String getLocalBcFileName() {
        return LOCAL_BC_FILE_NAME;
    }

    public static String getBcFileSuffix() {
        return BC_FILE_SUFFIX;
    }

    public static void remFav(BusinessCardItem item) {
        if (Database.itemList_fav.remove(item))
            Log.e(TAG, "remFav: could not remove favourite " + item.getName());
    }
}
