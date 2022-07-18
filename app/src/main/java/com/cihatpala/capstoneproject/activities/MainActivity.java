package com.cihatpala.capstoneproject.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;

import com.cihatpala.capstoneproject.R;
import com.cihatpala.capstoneproject.databinding.ActivityMainBinding;
import com.cihatpala.capstoneproject.model.Product;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    ActivityMainBinding binding;
    Handler handler;
    public static List<Product> productList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        View decorView = getWindow().getDecorView();
        int uiOptions = View.SYSTEM_UI_FLAG_HIDE_NAVIGATION;
        decorView.setSystemUiVisibility(uiOptions);
        binding.sphashScreen.setClickable(true);
        handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent goToEntry = new Intent(MainActivity.this, MarketActivity.class);
                startActivity(goToEntry);
                finish();
            }
        }, 1000);
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
}