package com.cihatpala.capstoneproject.database.local;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.cihatpala.capstoneproject.database.modelDB.UserOnDB;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Flowable;

@Dao
public interface UserDao {
    @Query("SELECT * FROM UserOnDB")
    Flowable<List<UserOnDB>> getAllUser();

    @Query("SELECT COUNT(user_name) FROM UserOnDB WHERE user_name=:name")
    Flowable<Integer> getNameIsExists(String name);

    @Insert
    Completable insert(UserOnDB user);
}
