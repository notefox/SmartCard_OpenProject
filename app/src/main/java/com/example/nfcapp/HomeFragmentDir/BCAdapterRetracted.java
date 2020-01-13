package com.example.nfcapp.HomeFragmentDir;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.nfcapp.BusinessCardDir.BusinessCardItem;
import com.example.nfcapp.R;

import java.util.AbstractList;

public class BCAdapterRetracted extends RecyclerView.Adapter<BCAdapterRetracted.BCViewHolder> {

    private AbstractList<BusinessCardItem> itemList;
    private OnItemClickListener mListener;

    public interface OnItemClickListener {
        void OnItemClick(int pos);
        void OnDeleteClick(int pos);
        void OnFavFlick(int pos);
    }

    public void setOnItemClickListener(OnItemClickListener listener){
        mListener = listener;
    }

    public static class BCViewHolder extends RecyclerView.ViewHolder {

        public ImageView imageView;
        public TextView nameTextView;
        public TextView companyTextView;

        public ImageButton favButton;
        public ImageButton deleteButton;

        public BCViewHolder(@NonNull View itemView, OnItemClickListener listener) {
            super(itemView);

            final OnItemClickListener mListener = listener;
            imageView = itemView.findViewById(R.id.bc_item_companyImage);
            nameTextView = itemView.findViewById(R.id.bc_item_Name);
            companyTextView = itemView.findViewById(R.id.bc_item_companyName);

            favButton = itemView.findViewById(R.id.bc_item_fav);
            deleteButton = itemView.findViewById(R.id.bc_item_delete);

            itemView.setOnClickListener(view -> {
                if (mListener != null) {
                    int position = getAdapterPosition();
                    if (position != RecyclerView.NO_POSITION)
                        mListener.OnItemClick(position);
                }
            });

            favButton.setOnClickListener(view -> {
                if (mListener != null) {
                    int position = getAdapterPosition();
                    if (position != RecyclerView.NO_POSITION) {
                        mListener.OnFavFlick(position);
                    }
                }
            });

            deleteButton.setOnClickListener(view -> {
                if (mListener != null) {
                    int position = getAdapterPosition();
                    if (position != RecyclerView.NO_POSITION)
                        mListener.OnDeleteClick(position);
                }
            });
        }
    }

    public BCAdapterRetracted(Context context, AbstractList<BusinessCardItem> itemList) {
        this.itemList = itemList;
    }

    @NonNull
    @Override
    public BCViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_businesscard_retracted, parent, false);
        BCViewHolder viewHolder = new BCViewHolder(v, mListener);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull BCViewHolder holder, int position) {
        BusinessCardItem currentItem = itemList.get(position);
        holder.itemView.setTag(itemList.get(position));

        holder.imageView.setImageBitmap(currentItem.getBitmapImage());
        holder.nameTextView.setText(currentItem.getName());
        holder.companyTextView.setText(currentItem.getCompanyName());

        if (currentItem.isFavourite())
            holder.favButton.setImageResource(R.drawable.ic_star_black_24dp);
        else
            holder.favButton.setImageResource(R.drawable.ic_star_border_black_24dp);

    }

    @Override
    public int getItemCount() {
        return itemList.size();
    }
}
