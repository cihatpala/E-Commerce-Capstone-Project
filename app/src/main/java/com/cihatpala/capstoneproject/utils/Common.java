package com.cihatpala.capstoneproject.utils;

import com.cihatpala.capstoneproject.database.datasource.FavoriteRepository;
import com.cihatpala.capstoneproject.database.datasource.ProductRepository;
import com.cihatpala.capstoneproject.database.datasource.UserRepository;
import com.cihatpala.capstoneproject.database.local.RoomDatabase;

public class Common {
    //Database
    public static RoomDatabase roomDatabase;
    public static ProductRepository productRepository;
    public static FavoriteRepository favoriteRepository;
    public static UserRepository userRepository;

}
