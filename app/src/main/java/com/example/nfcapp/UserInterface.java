package com.example.nfcapp;

public interface UserInterface <BCard> {

    void addNewBCard(BCard bCard) throws Exception;
    void addToFav(BCard bCard) throws Exception;
    void shareNFC(BCard bCard) throws Exception;

    void addToContact(BCard bCard) throws Exception;
}
