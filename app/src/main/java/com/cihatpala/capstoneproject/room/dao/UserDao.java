package com.cihatpala.capstoneproject.room.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.cihatpala.capstoneproject.room.entity.User;

import java.util.List;

import io.reactivex.Completable;

@Dao
public interface UserDao {
    @Query("SELECT * FROM User")
    List<User> getAllUser();

    @Insert
    Completable insert(User user);
}
