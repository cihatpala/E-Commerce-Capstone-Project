package com.cihatpala.capstoneproject.holder;

import static com.cihatpala.capstoneproject.helper.Helper.getScreenHeight;
import static com.cihatpala.capstoneproject.helper.Helper.getScreenWidth;

import android.content.Context;
import android.view.ViewGroup;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.cihatpala.capstoneproject.databinding.ItemProductImageGalleryBinding;

public class ProductDetailHolder extends RecyclerView.ViewHolder {

    ItemProductImageGalleryBinding binding;

    public ProductDetailHolder(ItemProductImageGalleryBinding binding) {
        super(binding.getRoot());
        this.binding = binding;
    }

    public void bind(String img, Context context) {
        setImagesByScreenSize(img);
    }

    private void setImagesByScreenSize(String img) { //TODO: the size of the incoming image can also be included in the resizing
        int height = (getScreenHeight() / 100) * 53;
        int width = (getScreenWidth() / 100) * 75;
        ViewGroup.LayoutParams layoutParams = binding.imageItem.getLayoutParams();
        layoutParams.height = height;
        layoutParams.width = width;
        binding.clImageArea.setLayoutParams(layoutParams);
        binding.imageItem.setLayoutParams(layoutParams);
        Glide.with(binding.imageItem)
                .load(img)
                .apply(new RequestOptions().override(width, height))
                .into(binding.imageItem);
    }

}
