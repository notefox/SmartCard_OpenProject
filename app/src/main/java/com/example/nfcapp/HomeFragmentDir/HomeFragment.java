package com.example.nfcapp.HomeFragmentDir;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.nfcapp.Database;
import com.example.nfcapp.R;

public class HomeFragment extends Fragment {

    private RecyclerView homeRecyclerView;
    private RecyclerView.Adapter<BCAdapterRetracted.BCViewHolder> homeAdapter;
    private RecyclerView.LayoutManager homeLayoutManager;

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
