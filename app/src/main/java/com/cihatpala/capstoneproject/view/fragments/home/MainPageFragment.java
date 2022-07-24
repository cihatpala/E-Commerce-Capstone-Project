package com.cihatpala.capstoneproject.view.fragments.home;

import android.os.Bundle;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.cihatpala.capstoneproject.R;
import com.cihatpala.capstoneproject.adapter.ProductCardAdapter;
import com.cihatpala.capstoneproject.database.modelDB.ProductOnDB;
import com.cihatpala.capstoneproject.databinding.FragmentMainPageBinding;
import com.cihatpala.capstoneproject.model.Product;
import com.cihatpala.capstoneproject.room.dao.FavoritesDao;
import com.cihatpala.capstoneproject.room.db.FavoritesDatabase;
import com.cihatpala.capstoneproject.room.db.UserDatabase;
import com.cihatpala.capstoneproject.utils.Common;
import com.cihatpala.capstoneproject.viewmodel.CommerceViewModel;

import java.util.ArrayList;
import java.util.List;

public class MainPageFragment extends Fragment {

    FragmentMainPageBinding binding;
    ProductCardAdapter adapter;
    List<Product> productList;
    LinearLayoutManager linearLayoutManager;
    CommerceViewModel viewModel;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewModel = new ViewModelProvider(this).get(CommerceViewModel.class);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_main_page, null, false);

        linearLayoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
        viewModel.getProducts("", "");
        viewModel.getProductList().observe(getViewLifecycleOwner(), this::processLiveData);

        return binding.getRoot();
    }

    private void processLiveData(List<Product> products) {
        adapter = new ProductCardAdapter(products, getActivity());
        binding.rvMainPage.setLayoutManager(linearLayoutManager);
        binding.rvMainPage.setAdapter(adapter);
    }

    @Override
    public void onResume() {
        System.out.println("MainPageFragment onResume");
        super.onResume();
        if (adapter != null) {
            adapter.notifyDataSetChanged();
        }
    }
}