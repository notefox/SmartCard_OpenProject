package com.example.nfcapp;

import com.example.nfcapp.BusinessCardDir.BusinessCardItem;

import java.util.AbstractList;
import java.util.Vector;

public class Database {
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

    public static AbstractList<BusinessCardItem> getItemList() {
        return itemList;
    }

    public static String getLocalBcFileName() {
        return LOCAL_BC_FILE_NAME;
    }

    public static String getBcFileSuffix() {
        return BC_FILE_SUFFIX;
    }
}
