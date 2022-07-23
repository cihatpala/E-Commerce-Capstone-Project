package com.cihatpala.capstoneproject.room.db;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.cihatpala.capstoneproject.room.dao.UserDao;
import com.cihatpala.capstoneproject.room.entity.User;

@Database(entities = {User.class}, version = 1)
public abstract class UserDatabase extends RoomDatabase {
    public abstract UserDao userDao();
}
