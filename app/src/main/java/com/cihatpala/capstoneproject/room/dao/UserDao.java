package com.cihatpala.capstoneproject.room.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.cihatpala.capstoneproject.room.entity.User;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Flowable;

@Dao
public interface UserDao {
    @Query("SELECT * FROM User")
    Flowable<List<User>> getAllUser();

    @Query("SELECT COUNT(user_name) FROM User WHERE user_name=:name")
    Flowable<Integer> getNameIsExists(String name);

    @Insert
    Completable insert(User user);
}
