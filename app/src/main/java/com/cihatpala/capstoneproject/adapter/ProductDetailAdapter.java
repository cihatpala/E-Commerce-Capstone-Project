package com.cihatpala.capstoneproject.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.cihatpala.capstoneproject.databinding.ItemProductImageGalleryBinding;
import com.cihatpala.capstoneproject.holder.ProductDetailHolder;

import java.util.List;

public class ProductDetailAdapter extends RecyclerView.Adapter<ProductDetailHolder> {
    List<String> images;
    Context context;


    public ProductDetailAdapter(List<String> images, Context context) {
        this.images = images;
        this.context = context;
    }

    @NonNull
    @Override
    public ProductDetailHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemProductImageGalleryBinding binding = ItemProductImageGalleryBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new ProductDetailHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductDetailHolder holder, int position) {
        System.out.println("position -> " + position);
        holder.bind(images.get(position), context);
    }

    @Override
    public int getItemCount() {
        return images.size();
    }
}
