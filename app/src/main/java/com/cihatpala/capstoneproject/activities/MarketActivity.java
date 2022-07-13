package com.cihatpala.capstoneproject.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import androidx.navigation.NavBackStackEntry;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.cihatpala.capstoneproject.R;
import com.cihatpala.capstoneproject.databinding.ActivityMarketBinding;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

import java.util.ArrayList;


public class MarketActivity extends AppCompatActivity {

    BottomNavigationView bottomNavigationView;
    NavController navController;
    ActivityMarketBinding binding;
    ArrayList<Integer> childFragmentMenuList = new ArrayList<>();
    ArrayList<Integer> childFragmentList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMarketBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        setSupportActionBar(binding.toolbar);

        childFragmentMenuList.add(0);
        childFragmentMenuList.add(1);
        childFragmentMenuList.add(1);
        childFragmentMenuList.add(1);
        childFragmentMenuList.add(1);

        childFragmentList.add(R.id.mainPageFragment);
        childFragmentList.add(R.id.shopFragment);
        childFragmentList.add(R.id.bagFragment);
        childFragmentList.add(R.id.favoritesFragment);
        childFragmentList.add(R.id.profileFragment);

        View decorView = getWindow().getDecorView();
        int uiOptions = View.SYSTEM_UI_FLAG_HIDE_NAVIGATION | View.SYSTEM_UI_FLAG_FULLSCREEN;
        decorView.setSystemUiVisibility(uiOptions);

        bottomNavigationView = binding.nav;
        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                System.out.println("android.R.id.home -> " + android.R.id.home);
                System.out.println("item.getItemId() -> " + item.getItemId());
                if (item.getItemId() == android.R.id.home) {
                    navController.navigateUp();
                    return true;
                }
                return onOptionsItemSelected(item);
            }
        });
        navController = Navigation.findNavController(this, R.id.market_container);

        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.mainPageFragment, R.id.shopFragment, R.id.bagFragment, R.id.favoritesFragment, R.id.profileFragment)
                .build();
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);

        NavigationUI.setupWithNavController(bottomNavigationView, navController);


    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        System.out.println("android.R.id.home -> " + android.R.id.home);
        System.out.println("item.getItemId() -> " + item.getItemId());
        if (item.getItemId() == android.R.id.home) {
            navController.navigateUp();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }


    @Override
    public void onBackPressed() {
        NavBackStackEntry navBackStackEntry = navController.getPreviousBackStackEntry();
        if (navBackStackEntry != null) {
            int destinationFragment = navBackStackEntry.getDestination().getId();
            if (childFragmentList.contains(destinationFragment)) {
                bottomNavigationView.getMenu().getItem(childFragmentMenuList.get(childFragmentList.indexOf(destinationFragment))).setChecked(true);
            }
        }

        super.onBackPressed();
    }
}