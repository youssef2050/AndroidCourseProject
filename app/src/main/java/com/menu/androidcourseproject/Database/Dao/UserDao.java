package com.menu.androidcourseproject.Database.Dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.menu.androidcourseproject.Model.User;

import java.util.List;

@Dao
public interface UserDao {
    @Insert
    void insert(User user);

    @Update
    void update(User user);


    @Query("Select * from User where id = :id")
    LiveData<List<User>> getUser(int id);

    @Query("select * from user where username =:username and password =:password")
    LiveData<List<User>> login(String username, String password);
}
