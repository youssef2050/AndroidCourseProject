package com.menu.androidcourseproject.Database;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.menu.androidcourseproject.Model.Purchase;

import java.util.List;

@Dao
public interface PurchaseDao {
    @Insert
    void insert(Purchase purchase);

    @Query("Delete  from purchase")
    void deleteAllPurchase();

    @Query("select * from purchase")
    LiveData<List<Purchase>> getPurchase();
}
