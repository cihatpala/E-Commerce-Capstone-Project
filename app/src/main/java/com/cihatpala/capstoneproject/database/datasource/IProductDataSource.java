package com.cihatpala.capstoneproject.database.datasource;

import com.cihatpala.capstoneproject.database.modelDB.ProductOnDB;

import java.util.List;

import io.reactivex.Flowable;

public interface IProductDataSource {
    Flowable<List<ProductOnDB>> getProductItems();

    Flowable<List<ProductOnDB>> getProductItemById(int productItem);

    int countProductItem();

    void emptyProduct();

    void insertToProduct(ProductOnDB... productOnDBS);

    void updateToProduct(ProductOnDB... productOnDBS);

    void deleteToProduct(ProductOnDB productOnDB);
}
