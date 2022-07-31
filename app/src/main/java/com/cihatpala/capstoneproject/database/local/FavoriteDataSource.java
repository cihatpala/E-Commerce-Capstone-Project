package com.cihatpala.capstoneproject.database.local;

import com.cihatpala.capstoneproject.database.datasource.IFavoriteDataSource;
import com.cihatpala.capstoneproject.database.modelDB.FavoriteOnDB;

import java.util.List;

import io.reactivex.Flowable;

public class FavoriteDataSource implements IFavoriteDataSource {

    private FavoriteDao favoriteDao;
    private static FavoriteDataSource instance;

    public FavoriteDataSource(FavoriteDao favoriteDao) {
        this.favoriteDao = favoriteDao;
    }

    public static FavoriteDataSource getInstance(FavoriteDao favoriteDao) {
        if (instance == null) {
            instance = new FavoriteDataSource(favoriteDao);
        }
        return instance;
    }

    @Override
    public Flowable<List<FavoriteOnDB>> getFavItems() {
        return favoriteDao.getFavItems();
    }

    @Override
    public int isFavorite(int itemId) {
        return favoriteDao.isFavorite(itemId);
    }

    @Override
    public void insert(FavoriteOnDB... favoriteOnDB) {
        favoriteDao.insert(favoriteOnDB);
    }

    @Override
    public void delete(FavoriteOnDB favoriteOnDB) {
        favoriteDao.delete(favoriteOnDB);
    }
}
