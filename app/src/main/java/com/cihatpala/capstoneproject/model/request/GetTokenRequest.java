package com.cihatpala.capstoneproject.model.request;

import com.cihatpala.capstoneproject.model.servicemodel.UserModel;
import com.google.gson.annotations.SerializedName;

public class GetTokenRequest {

    @SerializedName("body")
    public UserModel body;

    @Override
    public String toString() {
        return "GetTokenRequest{" +
                "body=" + body +
                '}';
    }
}
