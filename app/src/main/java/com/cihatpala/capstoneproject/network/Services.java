package com.cihatpala.capstoneproject.network;

import com.cihatpala.capstoneproject.database.modelDB.ProductOnDB;
import com.cihatpala.capstoneproject.model.response.GetTokenResponse;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface Services {
    @GET("products")
    Observable<List<ProductOnDB>> getAllProducts(@Query("limit") String query, @Query("sort") String sort); //returnProductList

    @POST("auth/login")
    Call<GetTokenResponse> postLogin(@Body String request); //returnProductList
}
