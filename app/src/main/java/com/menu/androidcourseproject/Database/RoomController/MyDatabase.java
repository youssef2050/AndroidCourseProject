package com.menu.androidcourseproject.Database.RoomController;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.menu.androidcourseproject.Database.Dao.MealDao;
import com.menu.androidcourseproject.Database.Dao.PurchaseDao;
import com.menu.androidcourseproject.Database.Dao.UserDao;
import com.menu.androidcourseproject.Model.Meal;
import com.menu.androidcourseproject.Model.Purchase;
import com.menu.androidcourseproject.Model.User;

@Database(entities = {User.class, Meal.class, Purchase.class}, version = 1)
public abstract class MyDatabase extends RoomDatabase {
    private static MyDatabase instance;
    public static final String DATABASE_NAME = "project_database.db";

    public abstract UserDao userDao();

    public abstract MealDao mealDao();

    public abstract PurchaseDao purchaseDao();

    public static synchronized MyDatabase getInstance(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext(), MyDatabase.class, DATABASE_NAME)
                    .fallbackToDestructiveMigration()
                    .build();
        }
        return instance;
    }
}
