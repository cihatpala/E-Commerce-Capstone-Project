package com.cihatpala.capstoneproject.network;

import com.cihatpala.capstoneproject.model.Product;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface Services {
    @GET("products")
    Observable<List<Product>> getAllProducts(@Query("limit") String query, @Query("sort") String sort); //returnProductList
}
