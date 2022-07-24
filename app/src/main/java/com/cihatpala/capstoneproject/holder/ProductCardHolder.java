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

import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import com.bumptech.glide.Glide;
import com.cihatpala.capstoneproject.R;
import com.cihatpala.capstoneproject.activities.EntryActivity;
import com.cihatpala.capstoneproject.activities.MainActivity;
import com.cihatpala.capstoneproject.activities.ProductDetailActivity;
import com.cihatpala.capstoneproject.databinding.ItemSaleBinding;
import com.cihatpala.capstoneproject.model.Product;
import com.cihatpala.capstoneproject.room.dao.FavoritesDao;
import com.cihatpala.capstoneproject.room.db.FavoritesDatabase;
import com.cihatpala.capstoneproject.room.entity.Favorites;

import java.util.ArrayList;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.exceptions.OnErrorNotImplementedException;
import io.reactivex.schedulers.Schedulers;

public class ProductCardHolder extends RecyclerView.ViewHolder {

    ItemSaleBinding binding;
    FavoritesDao favoritesDao;
    FavoritesDatabase db;
    public int globalProductId;
    public int currentPosition;
    public Favorites globalFavorites = new Favorites();
    private final CompositeDisposable compositeDisposable = new CompositeDisposable();

    public ProductCardHolder(ItemSaleBinding binding, Context context) {
        super(binding.getRoot());
        this.binding = binding;
        db = Room.databaseBuilder(context, FavoritesDatabase.class, "favorites").allowMainThreadQueries().build();
        favoritesDao = db.favoritesDao();
    }

    @SuppressLint("SetTextI18n")
    public void bind(Product product, Context context, int position) {
        currentPosition = position;
        Glide.with(binding.productImage)
                .load(product.image)
                .into(binding.productImage);
//        binding.amountOld.setText(product.description);
        binding.productBrandName.setText(product.title);
        binding.productItemType.setText(product.category);
        System.out.println("product.title bind -> " + product.title);
        System.out.println("product.price bind -> " + product.price);
        double richPrice = parseDouble(product.price) + 10;
        String formattedPrice = doubleFormat(richPrice);
        binding.amountOld.setText(formattedPrice + "$")
        ;
        productStateSeperator(product);
        binding.productCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent detailIntent = new Intent(context, ProductDetailActivity.class);
                ArrayList<String> images = imagesList(product.image);
                detailIntent.putStringArrayListExtra("img", images);
                Pair[] pairs = new Pair[1];
                pairs[0] = Pair.create(binding.productImage, "image");
                ActivityOptions activityOptions = ActivityOptions.makeSceneTransitionAnimation((Activity) context, pairs);
                context.startActivity(detailIntent, activityOptions.toBundle());
            }
        });

        binding.btnFavoriteCircle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                System.out.println("binding.btnFavoriteCircle clicked");
                isFavorites(product);
            }
        });

        binding.btnFavoriteHeart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                System.out.println("binding.btnFavoriteHeart clicked");
                isFavorites(product);

            }
        });
    }

    private void isFavorites(Product product) {
        globalProductId = product.id;
        try {
            compositeDisposable.add(favoritesDao.getFavoritesIsExists(globalProductId)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(this::isExistsFavorite));
        } catch (OnErrorNotImplementedException e) {
            System.out.println("OnErrorNotImplementedException e ->" + e.getLocalizedMessage());
        }
    }

    private void isExistsFavorite(Integer result) {
        globalFavorites.favoriteItemId = globalProductId;
        if (result != 0) {
            System.out.println("isExistsFavorite is exists");
            deleteFavorite();
        } else {
            System.out.println("isExistsFavorite is not exists");
            addFavorite();
        }
    }

    private void addFavorite() {
        try {
            compositeDisposable.add(favoritesDao.insert(globalFavorites)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(this::addedFavorite));
        } catch (OnErrorNotImplementedException e) {
            System.out.println("OnErrorNotImplementedException e ->" + e.getLocalizedMessage());
        }
    }

    private void deleteFavorite() {
        try {
            compositeDisposable.add(favoritesDao.delete(globalFavorites)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(this::deletedFavorite));
        } catch (OnErrorNotImplementedException e) {
            System.out.println("OnErrorNotImplementedException e ->" + e.getLocalizedMessage());
        }
    }

    @SuppressLint("ResourceAsColor")
    private void addedFavorite() {
        System.out.println("added favorite item");
        binding.btnFavoriteHeart.setBackgroundResource(R.drawable.ic_favorite_heart_full);
        compositeDisposable.clear();
    }

    private void deletedFavorite() {
        System.out.println("deleted favorite item");
        binding.btnFavoriteHeart.setBackgroundResource(R.drawable.ic_favorite_heart_empty);
        compositeDisposable.clear();
    }

    @SuppressLint("ResourceAsColor")
    public void productStateSeperator(Product product) { //First 10 item (new item), other item randomly reduced (no new amount in not reduced)
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