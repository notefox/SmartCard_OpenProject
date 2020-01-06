package com.example.nfcapp.BusinessCardDir;

import android.location.Address;

public class BusinessCardItem {

    private int bitmapImage;

    private String name;
    private CorporateTitle position;
    private String companyName;

    private String Address;
    private String phoneNumber;
    private String email;

    public BusinessCardItem(int bitmapImage, String name, String companyName, CorporateTitle position) {
        this.bitmapImage = bitmapImage;
        this.companyName = companyName;
        this.name = name;
        this.position = position;
    }

    public int getBitmapImage() {
        return bitmapImage;
    }
    public void setBitmapImage(int bitmapImage) {
        this.bitmapImage = bitmapImage;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public String getCompanyName() {
        return companyName;
    }
    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getPosition() {
        return position.getShorts();
    }
    public void setPosition(CorporateTitle position) {
        this.position = position;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return Address;
    }
    public void setAddress(String address) {
        Address = address;
    }

    boolean hasPicture() {
        if (bitmapImage == 0)
            return false;

        return true;
    }


    @Override
    public String toString() {
        return "BCardObject{" +
                ", picture=" + bitmapImage +
                ", name='" + name + '\'' +
                ", position='" + position.getShorts() + '\'' +
                ", phoneNumber=" + phoneNumber +
                ", email=" + email +
                '}';
    }

    public String toTerminalString() {

        StringBuilder sb = new StringBuilder();

        sb.append("Name : " + name + "\n");
        sb.append("Company : " + companyName + "\n");
        sb.append("Position : "+ position.getShorts() + "\n");
        sb.append("hasPicture : " + hasPicture() + "\n");
        sb.append("phoneNumbers : " + phoneNumber + "\n");
        sb.append("email : " + email + "\n");

        return sb.toString();
    }

}
