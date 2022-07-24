package com.cihatpala.capstoneproject.database.local;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.cihatpala.capstoneproject.database.modelDB.ProductOnDB;

@Database(entities = {ProductOnDB.class}, version = 1)
public abstract class ProductDatabase extends RoomDatabase {
    public abstract ProductDAO productDAO();

    private static ProductDatabase instance;

    public static ProductDatabase getInstance(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(context, ProductDatabase.class, "db_product")
                    .allowMainThreadQueries()
                    .build();
        }
        return instance;
    }
}
