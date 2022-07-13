package com.cihatpala.capstoneproject.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.cihatpala.capstoneproject.databinding.ItemSaleBinding;
import com.cihatpala.capstoneproject.holder.ProductCardHolder;
import com.cihatpala.capstoneproject.model.Product;

import java.util.List;

public class ProductCardAdapter extends RecyclerView.Adapter<ProductCardHolder> {

    List<Product> productList;

    public ProductCardAdapter(List<Product> productList) {
        this.productList = productList;
    }

    @NonNull
    @Override
    public ProductCardHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemSaleBinding recyclerRowBinding = ItemSaleBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new ProductCardHolder(recyclerRowBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductCardHolder holder, int position) {
        holder.bind(productList.get(position));
    }

    @Override
    public int getItemCount() {
        return productList.size();
    }

}
