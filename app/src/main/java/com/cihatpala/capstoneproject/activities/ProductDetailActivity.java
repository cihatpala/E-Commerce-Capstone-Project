package com.cihatpala.capstoneproject.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.os.Bundle;
import android.view.View;

import com.cihatpala.capstoneproject.R;
import com.cihatpala.capstoneproject.adapter.ProductDetailAdapter;
import com.cihatpala.capstoneproject.database.modelDB.FavoriteOnDB;
import com.cihatpala.capstoneproject.database.modelDB.ProductOnDB;
import com.cihatpala.capstoneproject.databinding.ActivityProductDetailBinding;
import com.cihatpala.capstoneproject.utils.Common;

import java.util.ArrayList;

public class ProductDetailActivity extends AppCompatActivity implements View.OnClickListener {
    ActivityProductDetailBinding binding;
    ProductDetailAdapter adapter;
    LinearLayoutManager linearLayoutManager;
    ArrayList<String> imgList;
    ProductOnDB productOnDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityProductDetailBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        imgList = new ArrayList<>();
        imgList = getIntent().getStringArrayListExtra("img");
        productOnDB = (ProductOnDB) getIntent().getSerializableExtra("detailProduct");
        System.out.println("imgList -> " + imgList);
        initGallery();
        drawFavoriteItems();
        chevronPressed();
    }

    private void initGallery() {
        adapter = new ProductDetailAdapter(imgList, this);
        linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        binding.productItem.rvProductImages.setLayoutManager(linearLayoutManager);
        binding.productItem.rvProductImages.setAdapter(adapter);
    }

    private void drawFavoriteItems() {
        if (Common.favoriteRepository.isFavorite(productOnDB.id - 1) == 1) {
            binding.productItem.btnFavoriteHeart.setBackgroundResource(R.drawable.ic_favorite_heart_full);
        } else {
            binding.productItem.btnFavoriteHeart.setBackgroundResource(R.drawable.ic_favorite_heart_empty);
        }
        binding.productItem.btnFavoriteHeart.setOnClickListener(this);
        binding.productItem.btnFavoriteCircle.setOnClickListener(this);
    }

    private void addOrRemoveFavorite(ProductOnDB product, int position) {
        if (Common.favoriteRepository.isFavorite(position) != 1) {
            addToFavorite(product);
        } else {
            deleteToFavorite(product);
        }
    }

    private void addToFavorite(ProductOnDB product) {
        binding.productItem.btnFavoriteHeart.setBackgroundResource(R.drawable.ic_favorite_heart_full);
        FavoriteOnDB favoriteOnDB = initializeFavoriteOnDb(product);
        Common.favoriteRepository.insert(favoriteOnDB);
    }

    private void deleteToFavorite(ProductOnDB product) {
        binding.productItem.btnFavoriteHeart.setBackgroundResource(R.drawable.ic_favorite_heart_empty);
        FavoriteOnDB favoriteOnDB = initializeFavoriteOnDb(product);
        Common.favoriteRepository.delete(favoriteOnDB);
    }

    private FavoriteOnDB initializeFavoriteOnDb(ProductOnDB product) {
        FavoriteOnDB favoriteOnDB = new FavoriteOnDB();
        favoriteOnDB.id = product.id;
        favoriteOnDB.price = product.price;
        favoriteOnDB.title = product.title;
        favoriteOnDB.category = product.category;
        favoriteOnDB.image = product.image;
        favoriteOnDB.description = product.description;
        return favoriteOnDB;
    }

    private void chevronPressed() {
        binding.productItem.chevron.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBack();
            }
        });
    }

    public void onBack() {
        onBackPressed();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.btn_favorite_heart || view.getId() == R.id.btn_favorite_circle) {
            addOrRemoveFavorite(productOnDB, productOnDB.id - 1);
        }
    }
}