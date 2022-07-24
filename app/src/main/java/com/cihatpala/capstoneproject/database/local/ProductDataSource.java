package com.cihatpala.capstoneproject.database.local;

import com.cihatpala.capstoneproject.database.datasource.IProductDataSource;
import com.cihatpala.capstoneproject.database.modelDB.ProductOnDB;

import java.util.List;

import io.reactivex.Flowable;

public class ProductDataSource implements IProductDataSource {

    private ProductDAO productDAO;
    private static ProductDataSource instance;

    public ProductDataSource(ProductDAO productDAO) {
        this.productDAO = productDAO;
    }

    public static ProductDataSource getInstance(ProductDAO productDAO) {
        if (instance == null) {
            instance = new ProductDataSource(productDAO);
        }
        return instance;
    }

    @Override
    public Flowable<List<ProductOnDB>> getProductItems() {
        return productDAO.getProductItems();
    }

    @Override
    public Flowable<List<ProductOnDB>> getProductItemById(int productItem) {
        return productDAO.getProductItemById(productItem);
    }

    @Override
    public int countProductItem() {
        return productDAO.countProductItem();
    }

    @Override
    public void emptyProduct() {
        productDAO.emptyProduct();
    }

    @Override
    public void insertToProduct(ProductOnDB... productOnDBS) {
        productDAO.insertToProduct(productOnDBS);
    }

    @Override
    public void updateToProduct(ProductOnDB... productOnDBS) {
        productDAO.updateToProduct(productOnDBS);
    }

    @Override
    public void deleteToProduct(ProductOnDB products) {
        productDAO.deleteToProduct(products);
    }
}
