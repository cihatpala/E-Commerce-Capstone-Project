package com.cihatpala.capstoneproject.database.datasource;

import com.cihatpala.capstoneproject.database.modelDB.FavoriteOnDB;

import java.util.List;

import io.reactivex.Flowable;

public interface IFavoriteDataSource {
    Flowable<List<FavoriteOnDB>> getFavItems();

    int isFavorite(int itemId);

    void insert(FavoriteOnDB... favoriteOnDB);

    void delete(FavoriteOnDB favoriteOnDB);
}
