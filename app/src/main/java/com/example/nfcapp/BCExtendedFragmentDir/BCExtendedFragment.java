package com.example.nfcapp.BCExtendedFragmentDir;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.nfcapp.BusinessCardDir.BusinessCardItem;
import com.example.nfcapp.Database;
import com.example.nfcapp.R;

public class BCExtendedFragment extends Fragment {

    private BusinessCardItem showingItem;

    private TextView header;

    private ImageButton favouriteButton;

    private ImageView imageView;

    private TextView userName;
    private TextView companyName;
    private TextView companyEmail;
    private TextView companyAddress;
    private TextView corporateTitle;
    private TextView companyNumber;

    public BCExtendedFragment(BusinessCardItem showingItem) {
        this.showingItem = showingItem;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_businesscard_extended, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        defineClassObjectsFromFragment();
        fetchShowingItem();

        this.favouriteButton.setOnClickListener(new FavChangeOnClickListener());
    }

    private void defineClassObjectsFromFragment(){
        this.header = getActivity().findViewById(R.id.BC_Extended_Header);
        this.favouriteButton = getActivity().findViewById(R.id.BusinessCard_extended_Fav_Button);
        this.imageView = getActivity().findViewById(R.id.BusinessCard_extended_image);
        this.userName = getActivity().findViewById(R.id.BusinessCard_extended_UserName);
        this.companyName = getActivity().findViewById(R.id.BusinessCard_extended_CompanyName);
        this.companyEmail = getActivity().findViewById(R.id.BusinessCard_extended_Email);
        this.companyAddress = getActivity().findViewById(R.id.BusinessCard_extended_CompanyAdress);
        this.corporateTitle = getActivity().findViewById(R.id.BusinessCard_extended_CorporateTitle);
        this.companyNumber = getActivity().findViewById(R.id.BusinessCard_extended_PhoneNumber);
    }

    private void fetchShowingItem() {

        if (showingItem.isFavourite())
            favouriteButton.setImageResource(R.drawable.ic_star_black_24dp);
        else
            favouriteButton.setImageResource(R.drawable.ic_star_border_black_24dp);

        this.header.setText(showingItem.getCompanyName());
        this.imageView.setImageBitmap(showingItem.getBitmapImage());
        this.userName.setText(showingItem.getName());
        this.companyName.setText(showingItem.getCompanyName());
        this.companyEmail.setText(showingItem.getEmail());
        this.companyAddress.setText(showingItem.getAddress());
        this.corporateTitle.setText(showingItem.getPosition());
        this.companyNumber.setText(showingItem.getPhoneNumber());
    }

    private class FavChangeOnClickListener implements View.OnClickListener {

        @Override
        public void onClick(View v) {
            //adding and removing from the Database List happens in the Object itself since there always only one Database class present
            if (showingItem.isFavourite()) {
                favouriteButton.setImageResource(R.drawable.ic_star_border_black_24dp);
                showingItem.removeFromFavourite();
            } else {
                favouriteButton.setImageResource(R.drawable.ic_star_black_24dp);
                showingItem.setAsFavourite();
            }
        }
    }
}
