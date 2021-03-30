package com.menu.androidcourseproject.Database.RoomController;

import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

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
                    .addCallback(callback)
                    .build();
        }
        return instance;
    }

    private static final RoomDatabase.Callback callback = new Callback() {
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
            new pop(instance).execute();
        }
    };

    private static class pop extends AsyncTask<Void, Void, Void> {
        private UserDao userDao;

        private pop(MyDatabase myDatabase) {
            this.userDao = myDatabase.userDao();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            User user = new User("youssef ezzeldeen",
                    "yousf2050@homtail.com",
                    "yousf2050",
                    "123456",
                    "ps",
                    "18/8/1998",
                    "0592280825",
                    true,
                    true,
                    "test");
            userDao.insert(user);
            userDao.insert(user);
            userDao.insert(user);
            return null;
        }
    }
}
