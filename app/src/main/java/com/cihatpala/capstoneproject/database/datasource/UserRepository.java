package com.cihatpala.capstoneproject.database.datasource;

import com.cihatpala.capstoneproject.database.modelDB.UserOnDB;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Flowable;

public class UserRepository implements IUserDataSource {
    private IUserDataSource iUserDataSource;

    public UserRepository(IUserDataSource iUserDataSource) {
        this.iUserDataSource = iUserDataSource;
    }

    private static UserRepository instance;

    public static UserRepository getInstance(IUserDataSource iUserDataSource) {
        if (instance == null) {
            instance = new UserRepository(iUserDataSource);
        }
        return instance;
    }

    @Override
    public Flowable<List<UserOnDB>> getAllUser() {
        return iUserDataSource.getAllUser();
    }

    @Override
    public Flowable<Integer> getNameIsExists(String name) {
        return iUserDataSource.getNameIsExists(name);
    }

    @Override
    public Completable insert(UserOnDB user) {
        return iUserDataSource.insert(user);
    }
}
