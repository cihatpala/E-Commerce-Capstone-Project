package com.cihatpala.capstoneproject.database.datasource;

import com.cihatpala.capstoneproject.database.modelDB.ProductOnDB;

import java.util.List;

import io.reactivex.Flowable;

public class ProductRepository implements IProductDataSource {

    private IProductDataSource iProductDataSource;

    public ProductRepository(IProductDataSource iProductDataSource) {
        this.iProductDataSource = iProductDataSource;
    }

    private static ProductRepository instance;

    public static ProductRepository getInstance(IProductDataSource iProductDataSource) {
        if (instance == null) {
            instance = new ProductRepository(iProductDataSource);
        }
        return instance;
    }


    @Override
    public Flowable<List<ProductOnDB>> getProductItems() {
        return iProductDataSource.getProductItems();
    }

    @Override
    public Flowable<List<ProductOnDB>> getProductItemById(int productItem) {
        return iProductDataSource.getProductItemById(productItem);
    }

    @Override
    public int countProductItem() {
        return iProductDataSource.countProductItem();
    }

    @Override
    public void emptyProduct() {
        iProductDataSource.emptyProduct();
    }

    @Override
    public void insertToProduct(ProductOnDB... productOnDBS) {
        iProductDataSource.insertToProduct(productOnDBS);
    }

    @Override
    public void updateToProduct(ProductOnDB... productOnDBS) {
        iProductDataSource.updateToProduct(productOnDBS);
    }

    @Override
    public void deleteToProduct(ProductOnDB products) {
        iProductDataSource.deleteToProduct(products);
    }
}
