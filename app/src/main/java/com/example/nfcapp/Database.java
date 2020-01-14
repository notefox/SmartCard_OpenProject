package com.example.nfcapp;

import android.app.Application;
import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.example.nfcapp.BusinessCardDir.BusinessCardItem;
import com.example.nfcapp.HomeFragmentDir.BCAdapterRetracted;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.AbstractList;
import java.util.Vector;

import static androidx.constraintlayout.widget.Constraints.TAG;

public class Database extends Application {
    public static final String BC_FILE_SUFFIX = ".bcf";
    private static String LOCAL_BC_FILE_NAME = "local" + BC_FILE_SUFFIX;
    public static String FILE_PREFIX_NORMAL_BC = "receivedBC_";

    private static Context mainContext;

    public static void setMainContext(Context mainContext) {
        Database.mainContext = mainContext;
    }

    private static AbstractList<BusinessCardItem> itemList = new Vector<>();
    private static AbstractList<BusinessCardItem> itemList_fav = new Vector<>();

    private static BusinessCardItem localID;

    public static BCAdapterRetracted homeAdapter;
    public static BCAdapterRetracted favAdapter;

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

    public static void removeItem(BusinessCardItem item) {
        try {
            new GeneralMethodsImpl().deleteFile(item.getOriginalFileName(), mainContext);
        } catch (FileNotFoundException e) {
            Log.e(TAG, "removeItem: ", e);
        }

        Database.itemList.remove(item);
        Database.itemList_fav.remove(item);

        if (favAdapter != null)
            favAdapter.notifyDataSetChanged();
        if (homeAdapter != null)
            homeAdapter.notifyDataSetChanged();
    }

    public static void addFav(BusinessCardItem item) {
        Database.itemList_fav.add(item);
        if (favAdapter != null)
            Database.favAdapter.notifyDataSetChanged();
        Database.homeAdapter.notifyDataSetChanged();
    }

    public void saveItem(BusinessCardItem item) {
        try {
            new GeneralMethodsImpl().save(item, (FILE_PREFIX_NORMAL_BC + itemList.size() + FILE_PREFIX_NORMAL_BC), mainContext);
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

        if (favAdapter != null)
            favAdapter.notifyDataSetChanged();
        if (homeAdapter != null)
            homeAdapter.notifyDataSetChanged();
    }

    private static int findFavByNameAndCompany(BusinessCardItem item) throws NullPointerException {
        for (int i = 0; i < itemList_fav.size(); i++) {
            if (Database.itemList_fav.get(i).getName().equals(item.getName()) && Database.itemList_fav.get(i).getCompanyName().equals(item.getCompanyName()));
            return i;
        }
        throw new NullPointerException("user not found");
    }
}
