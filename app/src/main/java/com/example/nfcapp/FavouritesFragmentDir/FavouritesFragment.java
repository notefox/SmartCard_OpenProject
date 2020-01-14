package com.example.nfcapp.FavouritesFragmentDir;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.nfcapp.BCExtendedFragmentDir.BCExtendedFragment;
import com.example.nfcapp.BusinessCardDir.BusinessCardItem;
import com.example.nfcapp.Database;
import com.example.nfcapp.HomeFragmentDir.BCAdapterRetracted;
import com.example.nfcapp.R;

public class FavouritesFragment extends Fragment {

    private RecyclerView favRecyclerView;
    private RecyclerView.LayoutManager favLayoutManager;

    private View view;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_favourites, container, false);

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        buildRecyclerView();

    }

    private void buildRecyclerView() {
        favRecyclerView = view.findViewById(R.id.fav_recycler_view);
        favRecyclerView.setHasFixedSize(true);

        favLayoutManager = new LinearLayoutManager(this.getActivity());
        Database.favAdapter = new BCAdapterRetracted(this.getActivity(), Database.getFavList());

        favRecyclerView.setLayoutManager(favLayoutManager);
        favRecyclerView.setAdapter(Database.favAdapter);

        Database.favAdapter.setOnItemClickListener(new BCAdapterRetracted.OnItemClickListener() {
            @Override
            public void OnItemClick(int pos) {
                BusinessCardItem temp = Database.getItemList().get(pos);

                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new BCExtendedFragment(temp)).commit();
            }

            @Override
            public void OnDeleteClick(int pos) {
                Database.removeItem(Database.getFavList().get(pos));
            }

            @Override
            public void OnFavFlick(int pos) {
                Database.getItemList().get(pos).removeFromFavourite();
            }
        });
    }
}
