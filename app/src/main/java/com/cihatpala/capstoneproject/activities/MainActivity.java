package com.cihatpala.capstoneproject.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;

import com.cihatpala.capstoneproject.R;
import com.cihatpala.capstoneproject.database.datasource.FavoriteRepository;
import com.cihatpala.capstoneproject.database.datasource.ProductRepository;
import com.cihatpala.capstoneproject.database.datasource.UserRepository;
import com.cihatpala.capstoneproject.database.local.FavoriteDataSource;
import com.cihatpala.capstoneproject.database.local.ProductDataSource;
import com.cihatpala.capstoneproject.database.local.RoomDatabase;
import com.cihatpala.capstoneproject.database.local.UserDataSource;
import com.cihatpala.capstoneproject.database.modelDB.ProductOnDB;
import com.cihatpala.capstoneproject.databinding.ActivityMainBinding;
import com.cihatpala.capstoneproject.utils.Common;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    ActivityMainBinding binding;
    Handler handler;
    public static List<ProductOnDB> productList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        initDB();
        setContentView(binding.getRoot());
        View decorView = getWindow().getDecorView();
        int uiOptions = View.SYSTEM_UI_FLAG_HIDE_NAVIGATION;
        decorView.setSystemUiVisibility(uiOptions);
//        binding.sphashScreen.setOnClickListener(this);
        handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent goToEntry = new Intent(MainActivity.this, EntryActivity.class);
                startActivity(goToEntry);
                finish();
            }
        }, 500);
    }


    @Override
    public void onClick(View view) { //Kullanıcı ekrana tıklandığında direkt geçsin diye.
        System.out.println("onClick");
        if (view.getId() == R.id.sphashScreen) {
            Intent goToEntry = new Intent(MainActivity.this, EntryActivity.class);
            startActivity(goToEntry);
            finish();
        }
    }

    private void initDB() {
        Common.roomDatabase = RoomDatabase.getInstance(this);
        Common.productRepository = ProductRepository.getInstance(ProductDataSource.getInstance(Common.roomDatabase.productDAO()));
        Common.favoriteRepository = FavoriteRepository.getInstance(FavoriteDataSource.getInstance(Common.roomDatabase.favoriteDao()));
        Common.userRepository = UserRepository.getInstance(UserDataSource.getInstance(Common.roomDatabase.userDao()));
    }
}