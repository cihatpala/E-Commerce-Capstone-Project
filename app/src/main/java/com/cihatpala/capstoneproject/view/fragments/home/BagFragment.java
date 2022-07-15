package com.cihatpala.capstoneproject.view.fragments.home;

import android.os.Bundle;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.cihatpala.capstoneproject.R;
import com.cihatpala.capstoneproject.adapter.BagAdapter;
import com.cihatpala.capstoneproject.databinding.FragmentBagBinding;
import com.cihatpala.capstoneproject.model.Bag;

import java.util.ArrayList;
import java.util.List;


public class BagFragment extends Fragment {

    FragmentBagBinding binding;
    BagAdapter adapter;
    LinearLayoutManager linearLayoutManager;
    List<Bag> bagList;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //mock bag item list
        bagList = new ArrayList<>();
        for (int i = 0; i < 31; i++) {
            Bag bag = new Bag("bagItem-" + i);
            bagList.add(bag);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_bag, null, false);

        adapter = new BagAdapter(bagList);
        linearLayoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        binding.rvBag.setLayoutManager(linearLayoutManager);
        binding.rvBag.setAdapter(adapter);

        return binding.getRoot();
    }
}