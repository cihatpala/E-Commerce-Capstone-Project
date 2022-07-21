package com.cihatpala.capstoneproject.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.cihatpala.capstoneproject.R;
import com.cihatpala.capstoneproject.adapter.ProductDetailAdapter;
import com.cihatpala.capstoneproject.databinding.ActivityProductDetailBinding;
import com.cihatpala.capstoneproject.databinding.ItemDetailProductBinding;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ProductDetailActivity extends AppCompatActivity {
    ActivityProductDetailBinding binding;
    ProductDetailAdapter adapter;
    LinearLayoutManager linearLayoutManager;
    ArrayList<String> imgList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityProductDetailBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        imgList = new ArrayList<>();
        imgList = getIntent().getStringArrayListExtra("img");
        System.out.println("imgList -> " + imgList);
        initGallery();

    }

    private void initGallery() {
        adapter = new ProductDetailAdapter(imgList, this);
        linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        binding.productItem.rvProductImages.setLayoutManager(linearLayoutManager);
        binding.productItem.rvProductImages.setAdapter(adapter);
    }
}