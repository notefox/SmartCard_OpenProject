package com.example.nfcapp;

import com.example.nfcapp.BusinessCardDir.BusinessCardItem;

import java.util.Vector;

public class Database {
    private static final String BC_FILE_SUFFIX = ".bcf";
    private static String LOCAL_BC_FILE_NAME = "local" + BC_FILE_SUFFIX;
    private static Vector<BusinessCardItem> itemList;
    private static Vector<BusinessCardItem> itemList_fav;

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

    public static BusinessCardItem[] getItemList() {
        return itemList.toArray(new BusinessCardItem[itemList.size()-1]);
    }

    public static String getLocalBcFileName() {
        return LOCAL_BC_FILE_NAME;
    }

    public static String getBcFileSuffix() {
        return BC_FILE_SUFFIX;
    }
}
