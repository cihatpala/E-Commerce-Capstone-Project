package com.cihatpala.capstoneproject.room.entity;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class User {
    @PrimaryKey
    @NonNull
    public String token;

    @ColumnInfo(name = "user_name")
    public String name;

    public User(String token, String name) {
        this.token = token;
        this.name = name;
    }

    @Override
    public String toString() {
        return "User{" +
                "token='" + token + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
