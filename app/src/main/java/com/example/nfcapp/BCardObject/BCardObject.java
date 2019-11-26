package com.example.nfcapp.BCardObject;

import android.content.ContentValues;
import android.location.Location;
import android.provider.ContactsContract.CommonDataKinds.*;

import com.google.gson.Gson;

import java.util.Random;

public class BCardObject {

    private Random rand;

    private ContentValues user_values;
    private ContentValues job_values;

    private ContentValues mobile_values;
    private ContentValues landlines_values;

    public BCardObject(ContentValues user_values, ContentValues job_values, ContentValues mobile_values, ContentValues landline_values) {
        this.user_values = user_values;
        this.job_values = job_values;
        this.mobile_values = mobile_values;
        this.landlines_values = landline_values;
    }

    public BCardObject() {

        user_values = new ContentValues(6);
        job_values = new ContentValues(4);
        mobile_values = new ContentValues(1);
        landlines_values = new ContentValues(1);

            addUserValues(rand.nextInt(), String.valueOf(rand.nextInt()), String.valueOf(rand.nextInt()), String.valueOf(rand.nextInt()), String.valueOf(rand.nextInt()), String.valueOf(rand.nextInt()));
            addJobValues(String.valueOf(rand.nextInt()),String.valueOf(rand.nextInt()) ,null, String.valueOf(rand.nextInt()));
            addLandlinesValues(String.valueOf(rand.nextInt()));
            addMobileValues(String.valueOf(rand.nextInt()));
    }

    void addUserValues(int id, String prefix, String firstName, String middleName, String lastName, String suffix) {

        user_values.put(StructuredName.CONTACT_ID, id);

        user_values.put(StructuredName.PREFIX, prefix);
        user_values.put(StructuredName.GIVEN_NAME, firstName);
        user_values.put(StructuredName.MIDDLE_NAME, middleName);
        user_values.put(StructuredName.FAMILY_NAME, lastName);
        user_values.put(StructuredName.SUFFIX, suffix);
    }

    void addJobValues(String name, String title, Location location, String jobDescription) {

        job_values.put(Organization.COMPANY, name);
        job_values.put(Organization.TITLE, title);
        job_values.put(Organization.OFFICE_LOCATION, location.toString());
        job_values.put(Organization.JOB_DESCRIPTION, jobDescription);
    }

    void addMobileValues(String number) {
        mobile_values.put(Phone.NUMBER,number);
        mobile_values.put(Phone.TYPE,Phone.TYPE_MOBILE);
    }

    void addLandlinesValues(String number) {
        mobile_values.put(Phone.NUMBER, number);
        mobile_values.put(Phone.TYPE, Phone.TYPE_MAIN);
    }

    @Override
    public String toString() {
        return "BCardObject{" +
                "user_values=" + user_values +
                ", job_values=" + job_values +
                ", mobile_values=" + mobile_values +
                ", landlines_values=" + landlines_values +
                '}';
    }

    public String toGsonString() {
        return new Gson().toJson(this.toString());
    }

}
