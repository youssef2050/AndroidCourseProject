package com.menu.androidcourseproject.database.room;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.menu.androidcourseproject.database.dao.MealDao;
import com.menu.androidcourseproject.database.dao.PurchaseDao;
import com.menu.androidcourseproject.database.dao.UserDao;
import com.menu.androidcourseproject.model.Meal;
import com.menu.androidcourseproject.model.Purchase;
import com.menu.androidcourseproject.model.User;

@Database(entities = {User.class, Meal.class, Purchase.class}, version = 2, exportSchema = false)
public abstract class MyDatabase extends RoomDatabase {
    public static final String DATABASE_NAME = "project_database.db";
    private static MyDatabase instance;

    public static synchronized MyDatabase getInstance(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext(), MyDatabase.class, DATABASE_NAME)
                    .fallbackToDestructiveMigration()
                    .build();
        }
        return instance;
    }

    public abstract UserDao userDao();

    public abstract MealDao mealDao();

    public abstract PurchaseDao purchaseDao();
}
