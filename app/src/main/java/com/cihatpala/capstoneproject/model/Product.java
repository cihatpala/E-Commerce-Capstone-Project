package com.cihatpala.capstoneproject.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Product implements Serializable {
    @SerializedName("id")
    public int id;
    @SerializedName("title")
    public String title;
    @SerializedName("price")
    public String price;
    @SerializedName("category")
    public String category;
    @SerializedName("description")
    public String description;
    @SerializedName("image")
    public String image;

    public Product() {

    }

    public Product(int id, String title, String price, String category, String description, String image) {
        this.id = id;
        this.title = title;
        this.price = price;
        this.category = category;
        this.description = description;
        this.image = image;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", price='" + price + '\'' +
                ", category='" + category + '\'' +
                ", description='" + description + '\'' +
                ", image='" + image + '\'' +
                '}';
    }
}
