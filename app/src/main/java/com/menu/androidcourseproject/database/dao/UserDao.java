package com.menu.androidcourseproject.database.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.menu.androidcourseproject.model.User;

import java.util.List;

@Dao
public interface UserDao {
    @Insert
    void insert(User user);

    @Update
    void update(User user);


    @Query("Select * from User where id = :id")
    LiveData<User> getUser(int id);

    @Query("select * from User where username =:username and password =:password")
    LiveData<User> login(String username, String password);

    @Query("select * from user")
    LiveData<List<User>> getAllUsers();

    @Query("select * from User where email=:email or username = :username")
    LiveData<User> checkUsername(String email, String username);
}
