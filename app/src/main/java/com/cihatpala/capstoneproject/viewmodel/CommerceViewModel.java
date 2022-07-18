package com.cihatpala.capstoneproject.viewmodel;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.cihatpala.capstoneproject.activities.MainActivity;
import com.cihatpala.capstoneproject.model.Product;
import com.cihatpala.capstoneproject.network.BaseNetwork;
import com.cihatpala.capstoneproject.network.Services;

import java.util.List;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class CommerceViewModel extends ViewModel {
    Services apiService;
    private MutableLiveData<List<Product>> productList = new MutableLiveData<>();

    public MutableLiveData<List<Product>> getProductList() {
        return productList;
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
                        System.out.println("getProducts onComplete products -> " + products);
                        for (int i = 0; i < products.size(); i++) {
//                            System.out.println(coinsResponseModels.get(i).toString());
                            System.out.println("product d->" + products.get(i).toString());
                            System.out.println("fixProductData d->" + fixProductData(products.get(i)));


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
