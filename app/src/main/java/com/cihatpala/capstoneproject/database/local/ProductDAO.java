package com.cihatpala.capstoneproject.database.local;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.cihatpala.capstoneproject.database.modelDB.ProductOnDB;

import java.util.List;

import io.reactivex.Flowable;

@Dao
public interface ProductDAO {

    @Query("SELECT * FROM ProductOnDB")
    Flowable<List<ProductOnDB>> getProductItems();

    @Query("SELECT * FROM ProductOnDB WHERE id=:productItem")
    Flowable<List<ProductOnDB>> getProductItemById(int productItem);

    @Query("SELECT COUNT(*) FROM ProductOnDB")
    int countProductItem();

    @Query("DELETE FROM ProductOnDB")
    void emptyProduct();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertToProduct(ProductOnDB... productOnDBS);

    @Update
    void updateToProduct(ProductOnDB... productOnDBS);

    @Delete
    void deleteToProduct(ProductOnDB productOnDB);

}
