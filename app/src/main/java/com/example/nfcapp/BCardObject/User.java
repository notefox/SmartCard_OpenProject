package com.example.nfcapp.BCardObject;

import static android.provider.ContactsContract.CommonDataKinds.StructuredName.*;

import java.lang.reflect.Array;
import java.util.ArrayList;


public class User {

    private int id;
    private String prefix;
    private String firstName;
    private String middleName;
    private String lastName;
    private String suffix;
    private ArrayList<String> phoneNumber = new ArrayList<>();

    public User(int id, String prefix, String firstName, String middleName, String lastName, String suffix) {
        this.id = id;
        this.prefix = prefix;
        this.firstName = firstName;
        this.middleName = middleName;
        this.lastName = lastName;
        this.suffix = suffix;
    }

    public User() {

    }

    void put(String TYPE, int input) {
        switch(TYPE) {
            case CONTACT_ID :
                this.id = input;
            break;

            default:
                throw new TypeNotPresentException(TYPE, new Throwable("exception thrown in com.example.nfcapp.BCardObject.User"));
        }
    }

    void put(String TYPE, String input) {
        switch (TYPE) {
            case PREFIX:
                this.prefix = input;
                break;

            case GIVEN_NAME:
                this.firstName = input;
                break;

            case MIDDLE_NAME:
                this.middleName = input;
                break;

            case FAMILY_NAME:
                this.lastName = input;
                break;

            case SUFFIX:
                this.suffix = input;
                break;
        }
    }
}
