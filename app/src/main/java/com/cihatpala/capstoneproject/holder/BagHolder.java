package com.cihatpala.capstoneproject.holder;

import androidx.recyclerview.widget.RecyclerView;

import com.cihatpala.capstoneproject.databinding.ItemBagBinding;
import com.cihatpala.capstoneproject.model.Bag;

public class BagHolder extends RecyclerView.ViewHolder {

    ItemBagBinding binding;

    public BagHolder(ItemBagBinding binding) {
        super(binding.getRoot());
        this.binding = binding;
    }

    public void bind(Bag bag) {
        binding.bagProductName.setText(bag.productName);
    }
}
