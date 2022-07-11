package com.cihatpala.capstoneproject.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;

import com.cihatpala.capstoneproject.R;
import com.cihatpala.capstoneproject.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    ActivityMainBinding binding;
    Handler handler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent goToEntry = new Intent(MainActivity.this, EntryActivity.class);
                startActivity(goToEntry);
                finish();
            }
        }, 2000);
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