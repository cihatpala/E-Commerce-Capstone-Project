package com.cihatpala.capstoneproject.viewmodel;

import static com.cihatpala.capstoneproject.helper.Helper.generateRandomToken;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.cihatpala.capstoneproject.activities.MainActivity;
import com.cihatpala.capstoneproject.model.Product;
import com.cihatpala.capstoneproject.model.response.GetTokenResponse;
import com.cihatpala.capstoneproject.network.BaseNetwork;
import com.cihatpala.capstoneproject.network.Services;
import com.cihatpala.capstoneproject.room.entity.User;

import java.util.List;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CommerceViewModel extends ViewModel {
    Services apiService;
    private MutableLiveData<List<Product>> productList = new MutableLiveData<>();
    private MutableLiveData<User> user = new MutableLiveData<>();

    public MutableLiveData<List<Product>> getProductList() {
        return productList;
    }

    public MutableLiveData<User> getUserToken() {
        return user;
    }

    public void setUser(MutableLiveData<User> user) {
        this.user = user;
    }

    public void getProducts(String limit, String sort) {
        apiService = BaseNetwork.getClient().create(Services.class);
        apiService.getAllProducts(limit, sort).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<List<Product>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(List<Product> products) {
//                        System.out.println("getProducts onComplete products -> " + products);
                        for (int i = 0; i < products.size(); i++) {
//                            System.out.println(coinsResponseModels.get(i).toString());
//                            System.out.println("product d->" + products.get(i).toString());
//                            System.out.println("fixProductData d->" + fixProductData(products.get(i)));


                            MainActivity.productList.add(fixProductData(products.get(i)));
                            productList.postValue(MainActivity.productList);
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        System.out.println("getProducts onError");

                    }

                    @Override
                    public void onComplete() {
                        System.out.println("getProducts onComplete");
                    }
                });
    }

    public void getToken(String request, String name) {
        System.out.println("request -> " + request.toString());
        apiService = BaseNetwork.getClient().create(Services.class);
        Call<GetTokenResponse> call = apiService.postLogin(request);
        call.enqueue(new Callback<GetTokenResponse>() {
            @Override
            public void onResponse(Call<GetTokenResponse> call, Response<GetTokenResponse> response) {
                System.out.println("getToken onComplete response -> " + response);
                System.out.println("getToken onComplete request.body -> " + response.body());
                User user = new User();
                if (response.body() != null) {
                    user.setToken(response.body().token);
                } else {
                    user.setToken(generateRandomToken(5));
                }
                user.setName(name);
                getUserToken().postValue(user);
            }

            @Override
            public void onFailure(Call<GetTokenResponse> call, Throwable t) {
                System.out.println("getToken onFailure getLocalizedMessage -> " + t.getLocalizedMessage());
                System.out.println("getToken onFailure getMessage-> " + t.getMessage());
//                System.out.println("getToken onFailure request.body.username -> " + request.body.username);
            }
        });
//        apiService = BaseNetwork.getClient().create(Services.class);
//        apiService.postLogin(request).subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(new Observer<String>() {
//                    @Override
//                    public void onSubscribe(Disposable d) {
//
//                    }
//
//                    @Override
//                    public void onNext(String userToken) {
//                        System.out.println("getToken onComplete products -> " + user);
//                        User user = new User(userToken, request.body.username);
//                        getUserToken().postValue(user);
//                    }
//
//                    @Override
//                    public void onError(Throwable e) {
//                        System.out.println("getToken onError");
//
//                    }
//
//                    @Override
//                    public void onComplete() {
//                        System.out.println("getToken onComplete");
//                    }
//                });
    }

    public Product fixProductData(Product badProduct) {
        Product fixedProduct;
        fixedProduct = badProduct;
        if (badProduct.title.length() > 20) {
            fixedProduct.title = badProduct.title.substring(0, 19) + "...";
        } else {
            fixedProduct.title = badProduct.title;
        }
        return fixedProduct;
    }
}
