package com.example.nfcapp.BCardObject;

import android.content.ContentValues;
import android.provider.ContactsContract;
import android.provider.ContactsContract.CommonDataKinds.*;
import android.provider.MediaStore;

public class BCardObject {

    private int id;

    private ContentValues values;

    private StructuredName user;
    private Organization job;
    private StructuredPostal address;
    private Phone landlineNumber;
    private Phone mobileNumber;


    public BCardObject(int id, StructuredName user, Organization job, StructuredPostal address,
                       Phone landlineNumber, Phone mobileNumber) {

        this.user = user;
        this.job = job;
        this.address = address;
        this.landlineNumber = landlineNumber;
        this.mobileNumber = mobileNumber;

    }

    void addID() {
        values.put(StructuredName.CONTACT_ID, "ID");

        values.put(StructuredName.PREFIX, "Prefix(Dr., Ing., Prof., ...)");
        values.put(StructuredName.GIVEN_NAME, "First Name");
        values.put(StructuredName.MIDDLE_NAME, "Middle Name");
        values.put(StructuredName.FAMILY_NAME, "Last Name");
        values.put(StructuredName.SUFFIX, "Suffix");
    }
}
