package com.example.nfcapp;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.nfc.NfcAdapter;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import com.example.nfcapp.BusinessCardDir.BusinessCardItem;
import com.example.nfcapp.FavouritesFragmentDir.FavouritesFragment;
import com.example.nfcapp.HomeFragmentDir.HomeFragment;
import com.example.nfcapp.NFC.NFCHandler;
import com.example.nfcapp.SettingsFragmentDir.SettingsFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.gson.Gson;

public class MainActivity extends AppCompatActivity {

    NfcAdapter mNfcAdapter;
    NFCHandler nfc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        BottomNavigationView bottomNav = findViewById(R.id.bottom_nav_view);
        bottomNav.setOnNavigationItemSelectedListener(navListener);


        if (!(ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) || !(ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED)) {
            requestStoragePermissions();
        }

        if (!(ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.NFC) == PackageManager.PERMISSION_GRANTED) || !((ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.NFC_TRANSACTION_EVENT) == PackageManager.PERMISSION_GRANTED))) {
            requestNFCPermission();
        }

        /*
        //Check if NFC is available on device
        if (!nfc.checkNFC()) {
            Toast.makeText(this, "NFC not available on this device",
                    Toast.LENGTH_SHORT).show();
        }
        */
        mNfcAdapter = NfcAdapter.getDefaultAdapter(this);
        nfc = new NFCHandler(this, mNfcAdapter);
    }

    private void requestNFCPermission() {
        ActivityCompat.requestPermissions(this, new String[] {Manifest.permission.NFC, Manifest.permission.NFC_TRANSACTION_EVENT},2);
    }

    private void requestStoragePermissions() {
        if (!(ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.READ_EXTERNAL_STORAGE))) {
            ActivityCompat.requestPermissions(this, new String[] {Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);
        }

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == 1){
            if((grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) && (grantResults.length > 0 && grantResults[1] == PackageManager.PERMISSION_GRANTED));
                Toast.makeText(MainActivity.this, "thx", Toast.LENGTH_SHORT).show();
        } else if(requestCode == 2) {
            if((grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) && (grantResults.length > 0 && grantResults[1] == PackageManager.PERMISSION_GRANTED));
                Toast.makeText(MainActivity.this, "thx", Toast.LENGTH_SHORT).show();
        }
    }

    private BottomNavigationView.OnNavigationItemSelectedListener navListener = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            Fragment selectedFragment = null;

            switch (item.getItemId()){
                case R.id.nav_home:
                    selectedFragment = new HomeFragment();
                    break;

                case R.id.nav_fav:
                    selectedFragment = new FavouritesFragment();
                    break;

                case R.id.nav_settings:
                    selectedFragment = new SettingsFragment();
                    break;
            }

            if (selectedFragment == null) {
                return false;
            }

            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, selectedFragment).commit();

            return true;
        }
    };

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.head_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.  head_item_send:
                    nfc.addMessageToSend(new Gson().toJson(Database.getLocalID()));
                break;
        }
        return super.onOptionsItemSelected(item);
    }
/*
    private void updateRecyclerViews() {
        Database.add(Database.read());
    }
    */

    @Override
    public void onNewIntent(Intent intent) {
        /*Database.setIntentIncomingList(nfc.handleNfcIntent(intent));
        convertNewIntentToUsableItem();
        updateRecyclerViews();
         */

        Toast.makeText(this, nfc.handleNfcIntent(intent).get(0), Toast.LENGTH_SHORT).show();
    }
/*
    void convertNewIntentToUsableItem() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < Database.getIntentIncomingList().size(); i++) {
            sb.append(Database.getIntentIncomingList().get(i));
        }
        Database.add(new Gson().fromJson(sb.toString(), BusinessCardItem.class));
    }

 */
}
