package com.cihatpala.capstoneproject.view.fragments.home;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavDirections;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.cihatpala.capstoneproject.R;
import com.cihatpala.capstoneproject.databinding.FragmentFavoritesBinding;
import com.cihatpala.capstoneproject.view.fragments.entry.LoginFragmentDirections;


public class FavoritesFragment extends Fragment {

    FragmentFavoritesBinding binding;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_favorites, null, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        binding.gotoshop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavDirections action = FavoritesFragmentDirections.actionFavoritesFragmentToBagFragment();
                Navigation.findNavController(view).navigate(action);
//                FavoritesFragment.this.onStop();
            }
        });
    }
}