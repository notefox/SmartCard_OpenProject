package com.example.nfcapp;

import com.google.gson.Gson;

public class TestMainClass {

    public String test = "dass";
    public int testInt = 4234;

    public static void main(String[] args) {

        Gson bla = new Gson();

        String a = bla.toJson(new TestMainClass());

        System.out.println(a);

        TestMainClass b = bla.fromJson(a, TestMainClass.class);

        System.out.println(b.testInt + 100000);

    }
}
