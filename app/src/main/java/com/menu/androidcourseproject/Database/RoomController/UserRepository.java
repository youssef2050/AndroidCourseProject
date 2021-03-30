package com.menu.androidcourseproject.Database.RoomController;

import android.app.Application;
import android.os.AsyncTask;
import android.util.Log;

import androidx.lifecycle.LiveData;

import com.menu.androidcourseproject.Database.Dao.UserDao;
import com.menu.androidcourseproject.Model.User;

import java.util.List;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class UserRepository {
    private static final String TAG = "UserRepository";
    private UserDao userDao;
    private MyDatabase myDatabase;

    public UserRepository(Application application) {
        myDatabase = MyDatabase.getInstance(application);
        this.userDao = myDatabase.userDao();
    }

    public void insert(final User user) {
        new InsertUserAsyncTask(userDao).execute(user);
    }

    public Disposable update(final User user) {
        return Completable.fromAction(() -> userDao.update(user))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(() -> Log.d(TAG, "update: "),
                        throwable -> Log.d(TAG, "update: error"));
    }

    public LiveData<List<User>> login(String username, String password) {
        return userDao.login(username, password);
    }

    public LiveData<List<User>> getUser(int id) {
        return userDao.getUser(id);
    }

    private static class InsertUserAsyncTask extends AsyncTask<User, Void, Void> {
        private UserDao userDao;

        private InsertUserAsyncTask(UserDao userDao) {
            this.userDao = userDao;
        }

        @Override
        protected Void doInBackground(User... users) {
            userDao.insert(users[0]);
            return null;
        }
    }
}
