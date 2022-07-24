package com.cihatpala.capstoneproject.room.db;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.cihatpala.capstoneproject.room.dao.FavoritesDao;
import com.cihatpala.capstoneproject.room.entity.Favorites;

@Database(entities = {Favorites.class}, version = 1)
public abstract class FavoritesDatabase extends RoomDatabase {
    public abstract FavoritesDao favoritesDao();
}
