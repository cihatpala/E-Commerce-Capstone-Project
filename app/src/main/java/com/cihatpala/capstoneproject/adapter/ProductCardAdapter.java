package com.cihatpala.capstoneproject.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.cihatpala.capstoneproject.database.modelDB.ProductOnDB;
import com.cihatpala.capstoneproject.databinding.ItemSaleBinding;
import com.cihatpala.capstoneproject.holder.ProductCardHolder;

import java.util.List;

public class ProductCardAdapter extends RecyclerView.Adapter<ProductCardHolder> {

    List<ProductOnDB> productList;
    Context context;

    public ProductCardAdapter(List<ProductOnDB> productList, Context context) {
        this.productList = productList;
        this.context = context;
    }

    @NonNull
    @Override
    public ProductCardHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemSaleBinding recyclerRowBinding = ItemSaleBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new ProductCardHolder(recyclerRowBinding, context);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductCardHolder holder, int position) {
        holder.bind(productList.get(position), context, position);
    }

    @Override
    public int getItemCount() {
        return productList.size();
    }

}
