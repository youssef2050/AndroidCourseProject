package com.menu.androidcourseproject.database.room.repository;

import android.app.Application;
import android.util.Log;

import androidx.lifecycle.LiveData;

import com.menu.androidcourseproject.database.dao.UserDao;
import com.menu.androidcourseproject.database.room.MyDatabase;
import com.menu.androidcourseproject.model.User;

import java.util.List;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class UserRepository {
    private static final String TAG = "UserRepository";
    private UserDao userDao;
    private LiveData<List<User>> getAllUsers;

    public UserRepository(Application application) {
        MyDatabase myDatabase = MyDatabase.getInstance(application);
        this.userDao = myDatabase.userDao();
        getAllUsers = userDao.getAllUsers();

    }

    public void insert(final User user) {
        Completable.fromAction(() -> userDao.insert(user))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(() -> Log.d(TAG, "insert: "),
                        throwable -> System.out.println(throwable.getMessage()));
    }

    public void update(final User user) {
        Completable.fromAction(() -> userDao.update(user))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(() -> Log.d(TAG, "update: "),
                        throwable -> Log.d(TAG, "update: error"));
    }

    public LiveData<User> login(String username, String password) {
        return userDao.login(username, password);
    }

    public LiveData<User> getUser(int id) {
        return userDao.getUser(id);
    }


    public LiveData<List<User>> getGetAllUsers() {
        return getAllUsers;
    }
}
