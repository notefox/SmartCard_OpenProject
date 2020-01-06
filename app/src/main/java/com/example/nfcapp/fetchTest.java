package com.example.nfcapp;


import com.example.nfcapp.BusinessCardDir.BusinessCardItem;

import java.util.AbstractList;
import java.util.Vector;

public class fetchTest {
    public static void main(String[] args) {
        AbstractList<BusinessCardItem> itemList = new Vector<>();

        itemList.add(null);

        System.out.println(itemList.toArray(new BusinessCardItem[itemList.size()]).length);
    }
}


