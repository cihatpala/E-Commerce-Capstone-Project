package com.cihatpala.capstoneproject.database.local;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.cihatpala.capstoneproject.database.modelDB.FavoriteOnDB;

import java.util.List;

import io.reactivex.Flowable;

@Dao
public interface FavoriteDao {
    @Query("SELECT * FROM FavoriteOnDB")
    Flowable<List<FavoriteOnDB>> getFavItems();

    @Query("SELECT EXISTS(SELECT * FROM FavoriteOnDB WHERE id=:itemId)")
    int isFavorite(int itemId);

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(FavoriteOnDB... favoriteOnDB);

    @Delete
    void delete(FavoriteOnDB favoriteOnDB);
}
