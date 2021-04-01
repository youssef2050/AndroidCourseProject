package com.menu.androidcourseproject.database.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.menu.androidcourseproject.model.Meal;

import java.util.List;

@Dao
public interface MealDao {
    @Insert
    void insert(Meal meal);

    @Update
    void update(Meal meal);

    @Delete
    void delete(Meal meal);

    @Query("select * from meal")
    LiveData<List<Meal>> getAllMeal();
}
