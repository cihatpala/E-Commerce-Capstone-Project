package com.cihatpala.capstoneproject.room.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.cihatpala.capstoneproject.room.entity.Favorites;
import com.cihatpala.capstoneproject.room.entity.User;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Flowable;

@Dao
public interface FavoritesDao {

    @Query("SELECT COUNT(favoriteItemId) FROM Favorites WHERE favoriteItemId=:favoriteItemId")
    Flowable<Integer> getFavoritesIsExists(int favoriteItemId);

    @Delete
    Completable delete(Favorites favorites);

    @Insert
    Completable insert(Favorites favorites);

}
