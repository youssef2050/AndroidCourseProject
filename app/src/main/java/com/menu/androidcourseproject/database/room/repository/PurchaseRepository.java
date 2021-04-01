package com.menu.androidcourseproject.database.room.repository;

import android.app.Application;
import android.util.Log;

import androidx.lifecycle.LiveData;

import com.menu.androidcourseproject.database.dao.PurchaseDao;
import com.menu.androidcourseproject.database.room.MyDatabase;
import com.menu.androidcourseproject.model.Purchase;
import com.menu.androidcourseproject.model.PurchaseListAdapter;

import java.util.List;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class PurchaseRepository {
    private static final String TAG = "PurchaseRepository";
    private PurchaseDao purchaseDao;
    private LiveData<List<PurchaseListAdapter>> allPurchases;

    public PurchaseRepository(Application application) {
        MyDatabase myDatabase = MyDatabase.getInstance(application.getApplicationContext());
        purchaseDao = myDatabase.purchaseDao();
        allPurchases = purchaseDao.getPurchase();
    }

    public void deleteAll() {
        Completable.fromAction(() -> purchaseDao.deleteAllPurchase())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(() -> Log.d(TAG, "deleteAll: "),
                        throwable -> Log.e(TAG, "deleteAll: error", throwable));
    }

    public void insert(Purchase purchase) {
        Completable.fromAction(() -> purchaseDao.insert(purchase))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(() -> Log.d(TAG, "insert: "),
                        throwable -> Log.e(TAG, "insert: error", throwable));
    }
}
