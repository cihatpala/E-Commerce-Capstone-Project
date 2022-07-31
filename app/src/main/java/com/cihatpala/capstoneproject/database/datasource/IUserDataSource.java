package com.cihatpala.capstoneproject.database.datasource;

import com.cihatpala.capstoneproject.database.modelDB.UserOnDB;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Flowable;

public interface IUserDataSource {
    Flowable<List<UserOnDB>> getAllUser();
    Flowable<Integer> getNameIsExists(String name);
    Completable insert(UserOnDB user);

}
