package com.cihatpala.capstoneproject.view.fragments.home;

import android.os.Bundle;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.cihatpala.capstoneproject.R;
import com.cihatpala.capstoneproject.activities.MarketActivity;
import com.cihatpala.capstoneproject.adapter.ProductCardAdapter;
import com.cihatpala.capstoneproject.databinding.FragmentMainPageBinding;
import com.cihatpala.capstoneproject.model.Product;

import java.util.ArrayList;
import java.util.List;

public class MainPageFragment extends Fragment {

    FragmentMainPageBinding binding;
    ProductCardAdapter adapter;
    List<Product> productList;
    LinearLayoutManager linearLayoutManager;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Mock productlist
        productList = new ArrayList<>();
        for (int i = 0; i < 15; i++) {
            Product product = new Product(i, "brand" + i, i * 10 + "");
            productList.add(product);
        }
        adapter = new ProductCardAdapter(productList);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_main_page, null, false);

        linearLayoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);

        binding.rvMainPage.setLayoutManager(linearLayoutManager);
        binding.rvMainPage.setAdapter(adapter);
        return binding.getRoot();
    }
}