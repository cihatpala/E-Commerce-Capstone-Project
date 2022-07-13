package com.cihatpala.capstoneproject.holder;

import androidx.recyclerview.widget.RecyclerView;

import com.cihatpala.capstoneproject.databinding.ItemSaleBinding;
import com.cihatpala.capstoneproject.model.Product;

public class ProductCardHolder extends RecyclerView.ViewHolder {

    ItemSaleBinding binding;

    public ProductCardHolder(ItemSaleBinding binding) {
        super(binding.getRoot());
        this.binding = binding;
    }

    public void bind(Product product) {
        binding.amountOld.setText("0$");
    }

}