package com.cihatpala.capstoneproject.database.modelDB;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity(tableName = "UserOnDB")
public class UserOnDB {
    @PrimaryKey
    @NonNull
    public String token;

    @ColumnInfo(name = "user_name")
    public String userName;

    @NonNull
    public String getToken() {
        return token;
    }

    public void setToken(@NonNull String token) {
        this.token = token;
    }

    public String getName() {
        return userName;
    }

    public void setName(String userName) {
        this.userName = userName;
    }

    @Ignore
    public UserOnDB(String token, String userName) {
        this.token = token;
        this.userName = userName;
    }

    public UserOnDB() {

    }

    @Override
    public String toString() {
        return "User{" +
                "token='" + token + '\'' +
                ", name='" + userName + '\'' +
                '}';
    }

}
