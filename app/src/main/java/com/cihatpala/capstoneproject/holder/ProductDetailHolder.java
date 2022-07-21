package com.cihatpala.capstoneproject.holder;

import android.content.Context;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.cihatpala.capstoneproject.databinding.ItemProductImageGalleryBinding;

public class ProductDetailHolder extends RecyclerView.ViewHolder {

    ItemProductImageGalleryBinding binding;

    public ProductDetailHolder(ItemProductImageGalleryBinding binding) {
        super(binding.getRoot());
        this.binding = binding;
    }

    public void bind(String img, Context context) {
        Glide.with(binding.imageItem)
                .load(img)
                .into(binding.imageItem);
    }

}
