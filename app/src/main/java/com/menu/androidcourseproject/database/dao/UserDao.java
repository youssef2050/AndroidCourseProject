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

    @Query("update user  set password = :password where id = :id")
    void update(String password, int id);

    @Query("select * from user where phone = :phone and codePhone = :codePhone")
    LiveData<User> checkPhoneNumber(String phone, String codePhone);

    @Query("select * from user where password = :password and id = :id")
    LiveData<User> checkOldPassword(String password, int id);
}
