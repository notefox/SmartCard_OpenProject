package com.example.nfcapp;

import android.app.Application;
import android.content.Context;
import android.widget.Toast;

import com.example.nfcapp.BusinessCardDir.BusinessCardItem;
import com.example.nfcapp.HomeFragmentDir.BCAdapterRetracted;

import java.io.IOException;
import java.util.AbstractList;
import java.util.Vector;

public class Database extends Application {
    public static final String BC_FILE_SUFFIX = ".bcf";
    private static String LOCAL_BC_FILE_NAME = "local" + BC_FILE_SUFFIX;
    public static String FILE_PREFIX_NORMAL_BC = "receivedItem_";

    private static Context mainContext;

    public static void setMainContext(Context mainContext) {
        Database.mainContext = mainContext;
    }

    private static AbstractList<BusinessCardItem> itemList = new Vector<>();
    private static AbstractList<BusinessCardItem> itemList_fav = new Vector<>();

    private static BusinessCardItem localID;

    public static BCAdapterRetracted homeAdapter;
    public static BCAdapterRetracted favAdapted;

    public static BusinessCardItem getLocalID() {
        return localID;
    }

    public static void setLocalID(BusinessCardItem localID) {
        Database.localID = localID;
    }

    public static void addItem(BusinessCardItem item) {
        Database.itemList.add(item);
        if (homeAdapter != null)
            Database.homeAdapter.notifyItemInserted(homeAdapter.getItemCount());
    }

    public static void removeItem(int pos) {
        new GeneralMethodsClass().deleteFile(Database.FILE_PREFIX_NORMAL_BC + pos + Database.BC_FILE_SUFFIX);
    }

    public static void addFav(BusinessCardItem item) {
        Database.itemList_fav.add(item);
        if (favAdapted != null)
            Database.favAdapted.notifyItemInserted(favAdapted.getItemCount());
    }

    public void saveItem(BusinessCardItem item) {
        try {
            new GeneralMethodsClass().save(item, (FILE_PREFIX_NORMAL_BC + itemList.size() + FILE_PREFIX_NORMAL_BC), mainContext);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static AbstractList<BusinessCardItem> getFavList() {
        return Database.itemList_fav;
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
        try {
            Database.itemList_fav.remove(findFavByNameAndCompany(item));
        } catch (NullPointerException e) {
            Toast.makeText(mainContext, e.getMessage(), Toast.LENGTH_SHORT).show();
        }
        if (favAdapted != null)
            favAdapted.notifyDataSetChanged();
    }

    private static int findFavByNameAndCompany(BusinessCardItem item) throws NullPointerException {
        for (int i = 0; i < itemList_fav.size(); i++) {
            if (Database.itemList_fav.get(i).getName().equals(item.getName()) && Database.itemList_fav.get(i).getCompanyName().equals(item.getCompanyName()));
            return i;
        }
        throw new NullPointerException("user not found");
    }
}
