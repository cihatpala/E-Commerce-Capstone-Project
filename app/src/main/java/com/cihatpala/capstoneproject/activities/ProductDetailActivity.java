package com.cihatpala.capstoneproject.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.cihatpala.capstoneproject.R;
import com.cihatpala.capstoneproject.databinding.ActivityProductDetailBinding;

public class ProductDetailActivity extends AppCompatActivity {
    ActivityProductDetailBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityProductDetailBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
    }
}