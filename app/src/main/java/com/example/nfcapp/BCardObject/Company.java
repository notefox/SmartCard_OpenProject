package com.example.nfcapp.BCardObject;

import android.location.Location;

public class Company {

    String name;
    String title;
    Location location;
    String jobDescibtion;

    public Company(String name, String title, Location location, String jobDescibtion) {
        this.name = name;
        this.title = title;
        this.location = location;
        this.jobDescibtion = jobDescibtion;
    }

    public Company() {
    }

    void put(String TYPE, int input) {

    }

    void put(String TYPE, String input) {

    }
}
