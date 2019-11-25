package com.example.nfcapp.Persist_Save;

import java.util.ArrayList;
import java.util.List;

public class UserObject {
    private int id;
    private String name;
    private List<String> phoneNumber = new ArrayList<>();
    private List<String> email = new ArrayList<>();
    private UserObject userObject;

    UserObject(int id, String name) {
        this.id = id;
        this.name = name;
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

    public void addUO(UserObject userObject) {
        this.userObject = userObject;
    }

    @Override
    public String toString() {
        return "UserObject{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", phoneNumber=" + phoneNumber +
                ", email=" + email +
                ", userObject=" + userObject +
                '}';
    }

    public String toReadableString() {
        StringBuilder sb = new StringBuilder();
        sb.append("ID => " + id + "\n");
        sb.append("Name => " + name + "\n");

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
