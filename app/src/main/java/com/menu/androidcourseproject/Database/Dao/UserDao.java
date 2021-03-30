package com.menu.androidcourseproject.Database.Dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.menu.androidcourseproject.Model.User;

@Dao
public interface UserDao {
    @Insert
    void insert(User user);

    @Update
    void update(User user);

    @Delete
    void delete(User user);

    @Query("Select * from User where id = :id")
    LiveData<User> getUser(int id);

    @Query("select * from user where username =:username and password =:password")
    LiveData<User> login(String username, String password);
}
