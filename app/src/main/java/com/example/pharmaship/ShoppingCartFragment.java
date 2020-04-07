package com.example.pharmaship;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class ShoppingCartFragment extends Fragment {

    View view;
    private RecyclerView mRecyclerView;
    private  List<Prescription> prescriptionList;
    public static PrescriptionRecyclerAdapter prescriptionRecyclerAdapter;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_shopping, null, false);
        mRecyclerView = view.findViewById(R.id.shopping_cart_recycler_view);
        prescriptionRecyclerAdapter = new PrescriptionRecyclerAdapter(getContext(), prescriptionList);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRecyclerView.setAdapter(prescriptionRecyclerAdapter);
        Log.i(LoginActivity.TAG, Integer.toString(prescriptionRecyclerAdapter.mData.size()));

        return view;
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        prescriptionList = LocalDatabase.prescriptionList;
    }
}
