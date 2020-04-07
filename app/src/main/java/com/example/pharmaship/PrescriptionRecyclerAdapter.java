package com.example.pharmaship;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class PrescriptionRecyclerAdapter extends RecyclerView.Adapter<PrescriptionRecyclerAdapter.MyViewHolder> {

    Context mContext;
    List<Prescription> mData;

    public PrescriptionRecyclerAdapter(Context mContext, List<Prescription> mData) {
        this.mContext = mContext;
        this.mData = mData;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        view = LayoutInflater.from(mContext).inflate(R.layout.shopping_cart_item, parent, false);
        MyViewHolder viewHolder = new MyViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.prescriptionNameTextView.setText(mData.get(position).getName());
        holder.prescriptionStatusTextView.setText(mData.get(position).getStatus());
        holder.prescriptionDateOfArrivalTextView.setText(mData.get(position).getDateOfArrival());
        holder.prescriptionPhoto.setImageBitmap(mData.get(position).getPhoto());
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {

        private TextView prescriptionNameTextView;
        private TextView prescriptionStatusTextView;
        private TextView prescriptionDateOfArrivalTextView;
        private ImageView prescriptionPhoto;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            prescriptionNameTextView = itemView.findViewById(R.id.prescription_name);
            prescriptionStatusTextView = itemView.findViewById(R.id.prescription_status);
            prescriptionDateOfArrivalTextView = itemView.findViewById(R.id.prescription_date_of_arrival);
            prescriptionPhoto = itemView.findViewById(R.id.prescription_img);
        }
    }
}
