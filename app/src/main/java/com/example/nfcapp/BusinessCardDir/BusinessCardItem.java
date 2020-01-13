package com.example.nfcapp.BusinessCardDir;

import android.graphics.Bitmap;

import com.example.nfcapp.Database;
import com.example.nfcapp.GeneralMethodsClass;

public class BusinessCardItem {

    private int id;

    private String bitmapImage;

    private String name;
    private CorporateTitle position;
    private String companyName;

    private String address;
    private String phoneNumber;
    private String email;

    private boolean favourite = false;

    public BusinessCardItem(int id, Bitmap bitmapImage, String name, String companyName, CorporateTitle position) {
        this.id = id;
        this.bitmapImage = new GeneralMethodsClass().bitmapToString(bitmapImage);
        this.companyName = companyName;
        this.name = name;
        this.position = position;
    }

    public BusinessCardItem(int id, Bitmap bitmapImage, String name, CorporateTitle position, String companyName, String address, String phoneNumber, String email) {
        this.id = id;
        this.bitmapImage = new GeneralMethodsClass().bitmapToString(bitmapImage);
        this.name = name;
        this.position = position;
        this.companyName = companyName;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.email = email;
    }

    public Bitmap getBitmapImage() {
        return new GeneralMethodsClass().stringToBitMap(this.bitmapImage);
    }
    public void setBitmapImage(Bitmap bitmapImage) {
        this.bitmapImage = new GeneralMethodsClass().bitmapToString(bitmapImage);;
    }

    public void setId(int id) {
        this.id = id;
    }
    public int getId() {
        return id;
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

    public String getPosition() { return position.getShorts(); }
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
        return address;
    }
    public void setAddress(String address) {
        this.address = address;
    }

    boolean hasPicture() {
        if (bitmapImage == null)
            return false;

        return true;
    }

    public void setAsFavourite() {
        this.favourite = true;
        Database.addFav(this);
    }
    public void removeFromFavourite() {
        this.favourite = false;
        Database.addFav(this);
    }

    public boolean isFavourite() {
        return favourite;
    }

    @Override
    public String toString() {
        return "BCardObject{" +
                ", picture=" + hasPicture() +
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
