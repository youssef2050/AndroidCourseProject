package com.menu.androidcourseproject.database.room.repository;

import android.app.Application;
import android.util.Log;

import androidx.lifecycle.LiveData;

import com.menu.androidcourseproject.database.dao.MealDao;
import com.menu.androidcourseproject.database.room.MyDatabase;
import com.menu.androidcourseproject.model.Meal;

import java.util.List;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class MealRepository {
    private static final String TAG = "MealRepository";
    private MealDao mealDao;
    private LiveData<List<Meal>> getAllMeal;

    public MealRepository(Application application) {
        MyDatabase myDatabase = MyDatabase.getInstance(application.getApplicationContext());
        mealDao = myDatabase.mealDao();
        getAllMeal = mealDao.getAllMeal();
        System.out.println(TAG + " meal size :" + getAllMeal);
    }

    public void insert(Meal meal) {
        Completable.fromAction(() -> mealDao.insert(meal))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(() -> Log.d(TAG, "insert: "),
                        throwable -> Log.e(TAG, "insert: error", throwable));
    }

    public void update(Meal meal) {
        Completable.fromAction(() -> mealDao.update(meal))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(() -> Log.d(TAG, "update: "),
                        throwable -> Log.e(TAG, "update: error", throwable));
    }

    public void delete(Meal meal) {
        Completable.fromAction(() -> mealDao.delete(meal))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(() -> Log.d(TAG, "delete: "),
                        throwable -> Log.e(TAG, "delete: error", throwable));
    }

    public LiveData<List<Meal>> getGetAllMeal() {
        return getAllMeal;
    }
}
