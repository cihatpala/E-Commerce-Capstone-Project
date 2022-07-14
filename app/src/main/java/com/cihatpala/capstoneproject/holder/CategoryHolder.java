package com.cihatpala.capstoneproject.holder;

import androidx.recyclerview.widget.RecyclerView;

import com.cihatpala.capstoneproject.databinding.ItemCategoryBinding;
import com.cihatpala.capstoneproject.model.Category;

public class CategoryHolder extends RecyclerView.ViewHolder {

    ItemCategoryBinding binding;

    public CategoryHolder(ItemCategoryBinding binding) {
        super(binding.getRoot());
        this.binding = binding;
    }

    public void bind(Category category) {
        System.out.println("category name -> " + category.name);
        binding.tvCategoryName.setText(category.name);
    }
}
