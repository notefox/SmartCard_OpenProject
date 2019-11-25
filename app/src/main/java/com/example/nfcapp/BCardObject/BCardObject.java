package com.example.nfcapp.BCardObject;

import android.graphics.Bitmap;
import android.graphics.Picture;

import java.util.ArrayList;
import java.util.List;

public abstract class BCardObject {

    private int id;

    private Bitmap bitmapImage;
    private String name;
    private String position;

    private List<String> phoneNumber = new ArrayList<>();
    private List<String> email = new ArrayList<>();

    BCardObject (int id, String name, String position) {
        this.id = id;
        this.name = name;
        this.position = position;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void editName(String name) {
        this.name = name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String[] getmail() {
        return email.toArray(new String[email.size()]);
    }

    public String[] getPhoneNumber() {
        return phoneNumber.toArray(new String[phoneNumber.size()]);
    }

    public void addMail(String email) {
        this.email.add(email);
    }

    public void addNumber(String phoneNumber) {
        this.phoneNumber.add(phoneNumber);
    }

    public void addPicture(Picture bitmap) {

    }

    @Override
    public String toString() {
        return "BCardObject{" +
                  "id=" + id +
                ", picture=" + bitmapImage +
                ", name='" + name + '\'' +
                ", position='" + position + '\'' +
                ", phoneNumber=" + phoneNumber +
                ", email=" + email +
                '}';
    }

    public String toTerminalString() {

        StringBuilder sb = new StringBuilder();

        sb.append("ID => " + id + "\n");
        sb.append("Name => " + name + "\n");
        sb.append("Position => "+ position + "\n");

        //PhoneNumber
        sb.append("phoneNumbers: \n");
        for (int i = 0; i < phoneNumber.size(); i++) {
            sb.append(" " + (i+1) + " | " + phoneNumber.get(i) + "\n");
        }

        //email
        sb.append("email: " + "\n");
        for (int i = 0; i < email.size(); i++) {
            sb.append(" " + (i+1) + " | " + email.get(i) + "\n");
        }
        return sb.toString();
    }
}
