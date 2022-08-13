package com.cihatpala.capstoneproject.holder;

import static com.cihatpala.capstoneproject.helper.Helper.doubleFormat;
import static java.lang.Double.parseDouble;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.ActivityOptions;
import android.content.Context;
import android.content.Intent;
import android.graphics.Paint;
import android.util.Pair;
import android.view.View;

import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStoreOwner;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.cihatpala.capstoneproject.R;
import com.cihatpala.capstoneproject.activities.ProductDetailActivity;
import com.cihatpala.capstoneproject.database.modelDB.FavoriteOnDB;
import com.cihatpala.capstoneproject.database.modelDB.ProductOnDB;
import com.cihatpala.capstoneproject.databinding.ItemSaleBinding;
import com.cihatpala.capstoneproject.utils.Common;
import com.cihatpala.capstoneproject.viewmodel.CommerceViewModel;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class ProductCardHolder extends RecyclerView.ViewHolder {

    ItemSaleBinding binding;
    public int currentPosition;
    private final CompositeDisposable compositeDisposable = new CompositeDisposable();
    CommerceViewModel viewModel;

    public ProductCardHolder(ItemSaleBinding binding, Context context) {
        super(binding.getRoot());
        this.binding = binding;
        viewModel = new ViewModelProvider((ViewModelStoreOwner) context).get(CommerceViewModel.class);
    }

    @SuppressLint("SetTextI18n")
    public void bind(ProductOnDB product, Context context, int position) {
        currentPosition = position;
        loadFavoriteItem(position);
        Glide.with(binding.productImage)
                .load(product.image)
                .into(binding.productImage);
        binding.productBrandName.setText(product.title);
        binding.productItemType.setText(product.category);
        double richPrice = parseDouble(product.price) + 10;
        String formattedPrice = doubleFormat(richPrice);
        binding.amountOld.setText(formattedPrice + "$");
        productStateSeperator(product);
        binding.productCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent detailIntent = new Intent(context, ProductDetailActivity.class);
                ArrayList<String> images = imagesList(product.image);
                detailIntent.putStringArrayListExtra("img", images);
                detailIntent.putExtra("detailProduct", product);
                Pair[] pairs = new Pair[1];
                pairs[0] = Pair.create(binding.productImage, "image");
                ActivityOptions activityOptions = ActivityOptions.makeSceneTransitionAnimation((Activity) context, pairs);
                context.startActivity(detailIntent, activityOptions.toBundle());
            }
        });

        binding.btnFavoriteCircle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addOrRemoveFavorite(product, position);
            }
        });

        binding.btnFavoriteHeart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addOrRemoveFavorite(product, position);

            }
        });
    }

    private void loadFavoriteItem(int currentPosition) {
        compositeDisposable.add(Common.favoriteRepository.getFavItems()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<List<FavoriteOnDB>>() {
                    @Override
                    public void accept(List<FavoriteOnDB> favoriteOnDBS) throws Exception {
                        drawFavoriteItems(currentPosition);
                        compositeDisposable.clear();
                    }
                }));

    }


    private void drawFavoriteItems(int position) {
        if (Common.favoriteRepository.isFavorite(position) == 1) {
            binding.btnFavoriteHeart.setBackgroundResource(R.drawable.ic_favorite_heart_full);
        } else {
            binding.btnFavoriteHeart.setBackgroundResource(R.drawable.ic_favorite_heart_empty);
        }
    }

    private void addOrRemoveFavorite(ProductOnDB product, int position) {
        if (Common.favoriteRepository.isFavorite(position) != 1) {
            addToFavorite(product);
        } else {
            deleteToFavorite(product);
        }
    }

    private void addToFavorite(ProductOnDB product) {
        binding.btnFavoriteHeart.setBackgroundResource(R.drawable.ic_favorite_heart_full);
        FavoriteOnDB favoriteOnDB = initializeFavoriteOnDb(product);
        Common.favoriteRepository.insert(favoriteOnDB);
    }

    private void deleteToFavorite(ProductOnDB product) {
        binding.btnFavoriteHeart.setBackgroundResource(R.drawable.ic_favorite_heart_empty);
        FavoriteOnDB favoriteOnDB = initializeFavoriteOnDb(product);
        Common.favoriteRepository.delete(favoriteOnDB);
    }


    private FavoriteOnDB initializeFavoriteOnDb(ProductOnDB product) {
        FavoriteOnDB favoriteOnDB = new FavoriteOnDB();
        favoriteOnDB.id = product.id;
        favoriteOnDB.price = product.price;
        favoriteOnDB.title = product.title;
        favoriteOnDB.category = product.category;
        favoriteOnDB.image = product.image;
        favoriteOnDB.description = product.description;
        return favoriteOnDB;
    }

    @SuppressLint("ResourceAsColor")
    public void productStateSeperator(ProductOnDB product) { //First 10 item (new item), other item randomly reduced (no new amount in not reduced)
        if (product.id <= 10) {
            binding.newProduct.setVisibility(View.VISIBLE);
            binding.reducedProduct.setVisibility(View.GONE);
            binding.amountOld.setTextColor(R.color.app_gray);
            binding.amountOut.setVisibility(View.GONE);
        } else {
            if (product.id % 2 == 0) {
                binding.reducedProduct.setVisibility(View.VISIBLE);
                binding.newProduct.setVisibility(View.GONE);
                binding.amountOut.setText(product.price + "$");
                binding.amountOut.setVisibility(View.VISIBLE);
                binding.amountOld.setPaintFlags(Paint.STRIKE_THRU_TEXT_FLAG);
            } else {
                binding.reducedProduct.setVisibility(View.GONE);
                binding.newProduct.setVisibility(View.GONE);
                binding.amountOld.setPaintFlags(Paint.HINTING_OFF);
            }
        }
    }

    public ArrayList<String> imagesList(String image) {
        ArrayList<String> galleryImages = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            galleryImages.add(image);
        }
        return galleryImages;
    }
}