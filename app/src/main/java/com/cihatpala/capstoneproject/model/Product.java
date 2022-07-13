package com.cihatpala.capstoneproject.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Product implements Serializable {
    public int id;
    public String brandName;
    public String amount;

    public Product(int id, String brandName, String amount) {
        this.id = id;
        this.brandName = brandName;
        this.amount = amount;
    }
}
