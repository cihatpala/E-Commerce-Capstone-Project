package com.cihatpala.capstoneproject.database.local;

import com.cihatpala.capstoneproject.database.datasource.IUserDataSource;
import com.cihatpala.capstoneproject.database.modelDB.UserOnDB;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Flowable;

public class UserDataSource implements IUserDataSource {
    private UserDao userDao;
    private static UserDataSource instance;

    public UserDataSource(UserDao userDao) {
        this.userDao = userDao;
    }

    public static UserDataSource getInstance(UserDao userDao) {
        if (instance == null) {
            instance = new UserDataSource(userDao);
        }
        return instance;
    }

    @Override
    public Flowable<List<UserOnDB>> getAllUser() {
        return userDao.getAllUser();
    }

    @Override
    public Flowable<Integer> getNameIsExists(String name) {
        return userDao.getNameIsExists(name);
    }

    @Override
    public Completable insert(UserOnDB user) {
        return userDao.insert(user);
    }
}
