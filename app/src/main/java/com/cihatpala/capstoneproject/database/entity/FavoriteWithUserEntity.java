package com.cihatpala.capstoneproject.database.entity;

import androidx.room.Embedded;
import androidx.room.Relation;

import com.cihatpala.capstoneproject.database.modelDB.FavoriteOnDB;
import com.cihatpala.capstoneproject.database.modelDB.UserOnDB;

public class FavoriteWithUserEntity {
    @Embedded
    public FavoriteOnDB favoriteEntity;
    @Relation(
            parentColumn = "user_token",
            entityColumn = "id"
    )
    public UserOnDB userEntity;

}
