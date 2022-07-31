package com.cihatpala.capstoneproject.database.datasource;

import com.cihatpala.capstoneproject.database.modelDB.FavoriteOnDB;

import java.util.List;

import io.reactivex.Flowable;

public class FavoriteRepository implements IFavoriteDataSource {

    IFavoriteDataSource iFavoriteDataSource;

    public FavoriteRepository(IFavoriteDataSource iFavoriteDataSource) {
        this.iFavoriteDataSource = iFavoriteDataSource;
    }

    private static FavoriteRepository instance;

    public static FavoriteRepository getInstance(IFavoriteDataSource iFavoriteDataSource) {
        if (instance == null) {
            instance = new FavoriteRepository(iFavoriteDataSource);
        }
        return instance;
    }

    @Override
    public Flowable<List<FavoriteOnDB>> getFavItems() {
        return iFavoriteDataSource.getFavItems();
    }

    @Override
    public int isFavorite(int itemId) {
        return iFavoriteDataSource.isFavorite(itemId +1 );
    }

    @Override
    public void insert(FavoriteOnDB... favoriteOnDB) {
        iFavoriteDataSource.insert(favoriteOnDB);
    }

    @Override
    public void delete(FavoriteOnDB favoriteOnDB) {
        iFavoriteDataSource.delete(favoriteOnDB);
    }
}
