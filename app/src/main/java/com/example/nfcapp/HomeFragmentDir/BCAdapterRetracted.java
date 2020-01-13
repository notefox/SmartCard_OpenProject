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
        void OnItemClick(int position);
    }

    public void setOnItemClickListener(OnItemClickListener listener){
        mListener = listener;
    }

    public static class BCViewHolder extends RecyclerView.ViewHolder {

        public ImageView imageView;
        public TextView TextView1;
        public TextView TextView2;

        public ImageButton favButton;
        public ImageButton deleteButton;

        public BCViewHolder(@NonNull View itemView, OnItemClickListener listener) {
            super(itemView);
            final OnItemClickListener mlistener = listener;
            imageView = itemView.findViewById(R.id.bc_item_companyImage);
            TextView1 = itemView.findViewById(R.id.bc_item_Name);
            TextView2 = itemView.findViewById(R.id.bc_item_companyName);

            itemView.setOnClickListener(v -> {
                if (mlistener != null) {
                    int position = getAdapterPosition();
                    if (position != RecyclerView.NO_POSITION)
                        mlistener.OnItemClick(position);
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
        holder.TextView1.setText(currentItem.getName());
        holder.TextView2.setText(currentItem.getCompanyName());
    }

    @Override
    public int getItemCount() {
        return itemList.size();
    }
}
