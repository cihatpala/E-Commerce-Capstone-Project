package com.cihatpala.capstoneproject.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.cihatpala.capstoneproject.databinding.ItemBagBinding;
import com.cihatpala.capstoneproject.holder.BagHolder;
import com.cihatpala.capstoneproject.model.Bag;

import java.util.List;

public class BagAdapter extends RecyclerView.Adapter<BagHolder> {

    List<Bag> bagList;

    public BagAdapter(List<Bag> bagList) {
        this.bagList = bagList;
    }

    @NonNull
    @Override
    public BagHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemBagBinding binding = ItemBagBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new BagHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull BagHolder holder, int position) {
        holder.bind(bagList.get(position));
    }

    @Override
    public int getItemCount() {
        return bagList.size();
    }
}
