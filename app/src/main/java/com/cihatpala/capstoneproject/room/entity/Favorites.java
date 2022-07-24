package com.cihatpala.capstoneproject.room.entity;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Favorites {
    @NonNull
    @PrimaryKey
    public int favoriteItemId;
}
