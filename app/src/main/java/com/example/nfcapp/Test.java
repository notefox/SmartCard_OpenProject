package com.example.nfcapp;

import android.util.Log;

import com.google.gson.Gson;

public class Test {

    public String test = "dass";
    public int testInt = 4234;

    public static void main(String[] args) {

        Gson bla = new Gson();

        String a = bla.toJson(new Test());

        System.out.println(a);

        Test b = bla.fromJson(a, Test.class);

        System.out.println(b.testInt + 100000);

    }
}
