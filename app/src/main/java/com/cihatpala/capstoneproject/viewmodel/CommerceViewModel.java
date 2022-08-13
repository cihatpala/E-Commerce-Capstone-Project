package com.cihatpala.capstoneproject.viewmodel;

import static com.cihatpala.capstoneproject.helper.Helper.generateRandomToken;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.cihatpala.capstoneproject.activities.MainActivity;
import com.cihatpala.capstoneproject.database.modelDB.FavoriteOnDB;
import com.cihatpala.capstoneproject.database.modelDB.ProductOnDB;
import com.cihatpala.capstoneproject.database.modelDB.UserOnDB;
import com.cihatpala.capstoneproject.model.response.GetTokenResponse;
import com.cihatpala.capstoneproject.network.BaseNetwork;
import com.cihatpala.capstoneproject.network.Services;
import com.cihatpala.capstoneproject.utils.Common;

import java.util.List;
import java.util.logging.Logger;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CommerceViewModel extends ViewModel {
    Services apiService;
    private final CompositeDisposable compositeDisposable = new CompositeDisposable();

    public void setProductList(MutableLiveData<List<ProductOnDB>> productList) {
        this.productList = productList;
    }

    private MutableLiveData<List<ProductOnDB>> productList = new MutableLiveData<>();
    private MutableLiveData<UserOnDB> user = new MutableLiveData<>();

    public MutableLiveData<List<ProductOnDB>> getProductList() {
        return productList;
    }

    public MutableLiveData<UserOnDB> getUserToken() {
        return user;
    }

    public void setUser(MutableLiveData<UserOnDB> user) {
        this.user = user;
    }

    public void getProducts(String limit, String sort) {
        apiService = BaseNetwork.getClient().create(Services.class);
        apiService.getAllProducts(limit, sort).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<List<ProductOnDB>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(List<ProductOnDB> products) {
                        System.out.println("getProducts products -> " + products);
                        addProductOnDb(products);
                        compositeDisposable.add(Common.productRepository.getProductItems()
                                .subscribeOn(Schedulers.io())
                                .observeOn(AndroidSchedulers.mainThread())
                                .subscribe(new Consumer<List<ProductOnDB>>() {
                                    @Override
                                    public void accept(List<ProductOnDB> productOnDBS) throws Exception {
                                        System.out.println("accept productOnDBS size -> " + productOnDBS.size());
                                        if (MainActivity.productList.size() != productOnDBS.size()) {
                                            for (int i = 0; i < products.size(); i++) {
                                                MainActivity.productList.add(fixProductData(products.get(i)));
                                                productList.postValue(MainActivity.productList);
                                            }
                                        }
                                    }
                                }));
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
        apiService = BaseNetwork.getClient().create(Services.class);
        Call<GetTokenResponse> call = apiService.postLogin(request);
        call.enqueue(new Callback<GetTokenResponse>() {
            @Override
            public void onResponse(Call<GetTokenResponse> call, Response<GetTokenResponse> response) {
                System.out.println("getToken onComplete response -> " + response);
                UserOnDB user = new UserOnDB();
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
                System.out.println("getToken onFailure getMessage-> " + t.getMessage());
            }
        });
    }

    public ProductOnDB fixProductData(ProductOnDB badProduct) {
        ProductOnDB fixedProduct;
        fixedProduct = badProduct;
        if (badProduct.title.length() > 20) {
            fixedProduct.title = badProduct.title.substring(0, 19) + "...";
        } else {
            fixedProduct.title = badProduct.title;
        }
        return fixedProduct;
    }

    private void addProductOnDb(List<ProductOnDB> products) {
        for (int i = 0; i < products.size(); i++) {
            ProductOnDB product = products.get(i);
            ProductOnDB productOnDB = new ProductOnDB();
            productOnDB.id = product.id;
            productOnDB.title = product.title;
            productOnDB.price = product.price;
            productOnDB.category = product.category;
            productOnDB.description = product.description;
            productOnDB.image = product.image;
            Common.productRepository.insertToProduct(productOnDB);
        }
    }
}
