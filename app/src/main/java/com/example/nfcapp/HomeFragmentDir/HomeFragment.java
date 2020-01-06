package com.example.nfcapp.HomeFragmentDir;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.nfcapp.BusinessCardDir.BusinessCardItem;
import com.example.nfcapp.BusinessCardDir.CorporateTitle;
import com.example.nfcapp.Database;
import com.example.nfcapp.R;

import java.util.Arrays;
import java.util.Vector;

public class HomeFragment extends Fragment {

    private RecyclerView homeRecyclerView;
    private RecyclerView.Adapter<BCAdapterRetracted.BCViewHolder> homeAdapter;
    private RecyclerView.LayoutManager homeLayoutManager;

    private Button buttonInsert;
    private Button buttonRemove;

    private View view;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_home, container, false);

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        buildRecyclerView();

        buttonInsert = view.findViewById(R.id.testAddButton);
        buttonRemove = view.findViewById(R.id.testRemoveButton);

        buttonInsert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

    }

    private void buildRecyclerView() {
        homeRecyclerView = view.findViewById(R.id.home_recycler_view);
        homeRecyclerView.setHasFixedSize(true);

        homeLayoutManager = new LinearLayoutManager(this.getActivity());
        homeAdapter = new BCAdapterRetracted(this.getActivity(), Database.getItemList());

        homeRecyclerView.setLayoutManager(homeLayoutManager);
        homeRecyclerView.setAdapter(homeAdapter);
    }
}
