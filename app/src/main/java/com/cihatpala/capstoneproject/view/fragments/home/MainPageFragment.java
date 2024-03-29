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
import com.cihatpala.capstoneproject.activities.MainActivity;
import com.cihatpala.capstoneproject.adapter.ProductCardAdapter;
import com.cihatpala.capstoneproject.database.modelDB.ProductOnDB;
import com.cihatpala.capstoneproject.databinding.FragmentMainPageBinding;
import com.cihatpala.capstoneproject.viewmodel.CommerceViewModel;

import java.util.List;

public class MainPageFragment extends Fragment {

    FragmentMainPageBinding binding;
    ProductCardAdapter adapter;
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
        if (MainActivity.productList.size() == 0) {
            viewModel.getProducts("", "");
        }
        viewModel.getProductList().observe(getViewLifecycleOwner(), this::processLiveData);
        return binding.getRoot();
    }

    private void processLiveData(List<ProductOnDB> products) {
        System.out.println("processLiveData products size ->" + products.size());
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