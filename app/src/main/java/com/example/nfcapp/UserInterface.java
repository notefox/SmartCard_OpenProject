package com.example.nfcapp;

public interface UserInterface <BCard> {

    void addNewBCard(BCard bCard);
    void addToFav(BCard bCard);
    void shareNFC(BCard bCard);

    void addToContact(BCard bCard);
}
