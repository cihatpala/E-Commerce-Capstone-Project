package com.cihatpala.capstoneproject.database.modelDB;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "ProductOnDB")
public class ProductOnDB {
    @NonNull
    @PrimaryKey
    @ColumnInfo(name = "id")
    public int id;
    @ColumnInfo(name = "title")
    public String title;
    @ColumnInfo(name = "price")
    public String price;
    @ColumnInfo(name = "category")
    public String  category;
    @ColumnInfo(name = "description")
    public String description;
    @ColumnInfo(name = "image")
    public String image;

    @Override
    public String toString() {
        return "ProductOnDB{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", price='" + price + '\'' +
                ", category='" + category + '\'' +
                ", description='" + description + '\'' +
                ", image='" + image + '\'' +
                '}';
    }
}
