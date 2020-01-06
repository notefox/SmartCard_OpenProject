package com.example.nfcapp.SettingsFragmentDir;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.nfcapp.ApplicationClass;
import com.example.nfcapp.BusinessCardDir.BusinessCardItem;
import com.example.nfcapp.BusinessCardDir.CorporateTitle;
import com.example.nfcapp.DataFetchFunctions;
import com.example.nfcapp.Database;
import com.example.nfcapp.R;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.NoSuchElementException;

import static android.content.ContentValues.TAG;

public class SettingsFragment extends Fragment {

    ImageView imageView;
    Button selectPicture;
    Button apply_settings;

    EditText editName;
    EditText editCompanyName;
    EditText editEmail;
    EditText editAddress;
    EditText editNumber;
    TextView cooperateTitleDisplay;

    Bitmap userBitmap;
    CorporateTitle corporateTitle;

    String[] listItems;
    int checkedItem;
    String selectedItem;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_settings, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        imageView = getActivity().findViewById(R.id.localID_picture);
        selectPicture = getActivity().findViewById(R.id.settings_add_picture_button);
        apply_settings = getActivity().findViewById(R.id.settings_apply_button);

        editName = getActivity().findViewById(R.id.user_name_text_field);
        editCompanyName = getActivity().findViewById(R.id.company_name_text_field);
        editEmail = getActivity().findViewById(R.id.company_email_text_field);
        editNumber = getActivity().findViewById(R.id.company_phone_text_field);
        editAddress = getActivity().findViewById(R.id.company_address_text_field);
        cooperateTitleDisplay = getActivity().findViewById(R.id.user_ct_display);

        try {
            imageView.setImageBitmap(Database.getLocalID().getBitmapImage());
            editName.setText(Database.getLocalID().getName());
            editCompanyName.setText(Database.getLocalID().getCompanyName());
            editEmail.setText(Database.getLocalID().getEmail());
            editNumber.setText(Database.getLocalID().getPhoneNumber());
            editAddress.setText(Database.getLocalID().getAddress());
            cooperateTitleDisplay.setText(Database.getLocalID().getPosition());
        } catch (Exception e) {
            Log.e(TAG, "onActivityCreated: Exception thrown", e);
        }

        cooperateTitleDisplay.setOnClickListener(new chooseCTOnClickListener());

        selectPicture.setOnClickListener(new selectPictureOnClickListener());

        listItems = fetchListItems();

        apply_settings.setOnClickListener(new ApplySettingsOnClickListener());


    }

    private class selectPictureOnClickListener implements View.OnClickListener {

        @Override
        public void onClick(View view) {
            Intent intent = new Intent(Intent.ACTION_PICK,android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
            startActivityForResult(intent, 0);
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        Uri selectedImage = data.getData();
        String[] filePath = { MediaStore.Images.Media.DATA };
        Cursor c = getActivity().getContentResolver().query(selectedImage,filePath, null, null, null);
        c.moveToFirst();

        int columnIndex = c.getColumnIndex(filePath[0]);
        String picturePath = c.getString(columnIndex);
        c.close();

        userBitmap = (BitmapFactory.decodeFile(picturePath));
        imageView.setImageBitmap(userBitmap);
    }

    private class chooseCTOnClickListener implements View.OnClickListener {
        @Override
        public void onClick(View view) {
            AlertDialog.Builder mBuilder = new AlertDialog.Builder(getContext());
            mBuilder.setTitle("Corporate Titles");
            mBuilder.setSingleChoiceItems(listItems, checkedItem, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int position) {
                    corporateTitle = getByShort(listItems[position]);
                    cooperateTitleDisplay.setText(corporateTitle.getShorts());
                }
            }).setPositiveButton("select", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    dialogInterface.dismiss();
                }
            }).show();
        }
    }

    private class ApplySettingsOnClickListener implements View.OnClickListener {

        @Override
        public void onClick(View view) {
            if (userBitmap == null)
                Toast.makeText(getActivity(),"no Picture Selected!", Toast.LENGTH_LONG).show();
            else {

            }
        }
    }

    private String[] fetchListItems() {
        ArrayList<String> stringHolder = new ArrayList<>();
        for (int i = 0; i < CorporateTitle.values().length; i++) {
            stringHolder.add(CorporateTitle.values()[i].getShorts());
        }
        return stringHolder.toArray(new String[stringHolder.size()]);
    }

    private static CorporateTitle getByShort(String shorts) {
        for (int i = 0; i < CorporateTitle.values().length; i++) {
            if (CorporateTitle.values()[i].getShorts().equals(shorts))
                return CorporateTitle.values()[i];
        }
        throw new NoSuchElementException("no element found by the name of " + shorts);
    }
}
