package com.cihatpala.capstoneproject.view.fragments.home;

import android.os.Bundle;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.cihatpala.capstoneproject.R;
import com.cihatpala.capstoneproject.adapter.CategoryAdapter;
import com.cihatpala.capstoneproject.databinding.FragmentShopBinding;
import com.cihatpala.capstoneproject.model.Category;

import java.util.ArrayList;
import java.util.List;


public class ShopFragment extends Fragment {

    FragmentShopBinding binding;
    List<Category> categoryList;
    CategoryAdapter adapter;
    LinearLayoutManager linearLayoutManager;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //mock category list
        categoryList = new ArrayList<>();
        for (int i = 0; i < 31; i++) {
            Category category = new Category("Category-" + i);
            categoryList.add(category);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_shop, null, false);

        adapter = new CategoryAdapter(categoryList);
        linearLayoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        binding.rvCategories.setLayoutManager(linearLayoutManager);
        binding.rvCategories.setAdapter(adapter);

        return binding.getRoot();
    }
}