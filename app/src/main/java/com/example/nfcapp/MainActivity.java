package com.example.nfcapp;

import android.Manifest;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.nfc.NfcAdapter;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import com.example.nfcapp.BusinessCardDir.BusinessCardItem;
import com.example.nfcapp.BusinessCardDir.CorporateTitle;
import com.example.nfcapp.FavouritesFragmentDir.FavouritesFragment;
import com.example.nfcapp.HomeFragmentDir.HomeFragment;
import com.example.nfcapp.NFC.NFCHandler;
import com.example.nfcapp.SettingsFragmentDir.SettingsFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.gson.Gson;

import java.io.IOException;

import static com.example.nfcapp.Database.BC_FILE_SUFFIX;
import static com.example.nfcapp.Database.FILE_PREFIX_NORMAL_BC;
import static com.example.nfcapp.Database.addItem;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "Tag";

    private NfcAdapter mNfcAdapter;
    private NFCHandler nfc;

    private HomeFragment homeFragment;
    private FavouritesFragment favouritesFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Database.setMainContext(this);

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

        homeFragment = new HomeFragment();
        favouritesFragment = new FavouritesFragment();

        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new HomeFragment()).commit();
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
                //Toast.makeText(MainActivity.this, "thx", Toast.LENGTH_SHORT).show();
        } else if(requestCode == 2) {
            if((grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) && (grantResults.length > 0 && grantResults[1] == PackageManager.PERMISSION_GRANTED));
               // Toast.makeText(MainActivity.this, "thx", Toast.LENGTH_SHORT).show();
        }
    }

    private BottomNavigationView.OnNavigationItemSelectedListener navListener = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            Fragment selectedFragment = null;

            switch (item.getItemId()){
                case R.id.nav_home:
                    selectedFragment = homeFragment;
                    break;

                case R.id.nav_fav:
                    selectedFragment = favouritesFragment;
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
            case R.id.head_item_send:
                nfc.addMessageToSend(new Gson().toJson(Database.getLocalID()));
                break;

            case R.id.addManualItem:
                String fileName = FILE_PREFIX_NORMAL_BC + Database.getItemList().size() + BC_FILE_SUFFIX;
                BusinessCardItem temp = new BusinessCardItem(fileName ,null, "Test", "company", CorporateTitle.Null);
                try {
                    new GeneralMethodsImpl().save(temp,fileName, this);
                    addItem(temp);
                } catch (IOException e) {
                    e.printStackTrace();
                }
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onNewIntent(Intent intent) {
        String fileName = FILE_PREFIX_NORMAL_BC + Database.getItemList().size() + BC_FILE_SUFFIX;
        BusinessCardItem gottenObject = new Gson().fromJson(nfc.handleNfcIntent(intent).get(0), BusinessCardItem.class);
        try {
            gottenObject.setOriginalFileName(fileName);
            new GeneralMethodsImpl().save(gottenObject, fileName, this);
            Database.addItem(gottenObject);
        } catch (IOException e) {
            Log.e(TAG, "onNewIntent: ", e);
            Toast.makeText(this, "saved gotten ", Toast.LENGTH_SHORT).show();
        }
    }
}
