package com.cihatpala.capstoneproject.holder;

import static com.cihatpala.capstoneproject.helper.Helper.doubleFormat;
import static java.lang.Double.parseDouble;

import android.annotation.SuppressLint;
import android.graphics.Paint;
import android.view.View;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.cihatpala.capstoneproject.R;
import com.cihatpala.capstoneproject.databinding.ItemSaleBinding;
import com.cihatpala.capstoneproject.helper.Helper;
import com.cihatpala.capstoneproject.model.Product;

public class ProductCardHolder extends RecyclerView.ViewHolder {

    ItemSaleBinding binding;

    public ProductCardHolder(ItemSaleBinding binding) {
        super(binding.getRoot());
        this.binding = binding;
    }

    @SuppressLint("SetTextI18n")
    public void bind(Product product) {
        Glide.with(binding.productImage)
                .load(product.image)
                .into(binding.productImage);
//        binding.amountOld.setText(product.description);
        binding.productBrandName.setText(product.title);
        binding.productItemType.setText(product.category);
        System.out.println("product.title bind -> " + product.title);
        System.out.println("product.price bind -> " + product.price);
        double richPrice = parseDouble(product.price) + 10;
        String formattedPrice = doubleFormat(richPrice);
        binding.amountOld.setText(formattedPrice + "$")
        ;
        productStateSeperator(product);

    }

    @SuppressLint("ResourceAsColor")
    public void productStateSeperator(Product product) { //First 10 item (new item), other item randomly reduced (no new amount in not reduced)
        if (product.id <= 10) {
            binding.newProduct.setVisibility(View.VISIBLE);
            binding.reducedProduct.setVisibility(View.GONE);
            binding.amountOld.setTextColor(R.color.app_gray);
            binding.amountOut.setVisibility(View.GONE);
        } else {
            if (product.id % 2 == 0) {
                binding.reducedProduct.setVisibility(View.VISIBLE);
                binding.newProduct.setVisibility(View.GONE);
                binding.amountOut.setText(product.price + "$");
                binding.amountOut.setVisibility(View.VISIBLE);
                binding.amountOld.setPaintFlags(Paint.STRIKE_THRU_TEXT_FLAG);
            } else {
                binding.reducedProduct.setVisibility(View.GONE);
                binding.newProduct.setVisibility(View.GONE);
                binding.amountOld.setPaintFlags(Paint.HINTING_OFF);
            }
        }
    }

}