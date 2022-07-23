package com.cihatpala.capstoneproject.model.servicemodel;

import com.google.gson.annotations.SerializedName;

public class UserModel {
    @SerializedName("username")
    public String username;
    @SerializedName("password")
    public String password;

    @Override
    public String toString() {
        return "UserModel{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
