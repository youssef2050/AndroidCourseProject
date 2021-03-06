package com.menu.androidcourseproject.database.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.menu.androidcourseproject.model.Purchase;
import com.menu.androidcourseproject.model.PurchaseListAdapter;

import java.util.List;

@Dao
public interface PurchaseDao {
    @Insert
    void insert(Purchase purchase);

    @Query("Delete  from purchase")
    void deleteAllPurchase();

    @Query("select m.id,p.date,m.mealTitle,p.numberMeal,m.prise,u.fullName,p.timeInMillis from purchase p, meal m,user u where m.id = p.mealId and p.userId = u.id and u.id =:id")
    LiveData<List<PurchaseListAdapter>> getPurchase(int id);
}
