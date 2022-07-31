package com.cihatpala.capstoneproject.database.local;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;

import com.cihatpala.capstoneproject.database.modelDB.FavoriteOnDB;
import com.cihatpala.capstoneproject.database.modelDB.ProductOnDB;
import com.cihatpala.capstoneproject.database.modelDB.UserOnDB;

@Database(entities = {ProductOnDB.class, FavoriteOnDB.class, UserOnDB.class}, version = 1, exportSchema = false)
public abstract class RoomDatabase extends androidx.room.RoomDatabase {
    public abstract ProductDAO productDAO();

    public abstract FavoriteDao favoriteDao();

    public abstract UserDao userDao();

    private static RoomDatabase instance;

    public static RoomDatabase getInstance(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(context, RoomDatabase.class, "db_product")
                    .allowMainThreadQueries()
                    .fallbackToDestructiveMigration()
                    .build();
        }
        return instance;
    }
}
