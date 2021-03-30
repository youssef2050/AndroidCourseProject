package com.menu.androidcourseproject.Database.ViewModel;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.menu.androidcourseproject.Database.RoomController.Repository.UserRepository;
import com.menu.androidcourseproject.Model.User;

import java.util.List;

public class UserViewModel extends AndroidViewModel {
    private static final String TAG = "UserViewModel";
    private UserRepository userRepository;


    public UserViewModel(@NonNull Application application) {
        super(application);
        userRepository = new UserRepository(application);
    }

    public LiveData<List<User>> login(String username, String password) {
        return userRepository.login(username, password);
    }

    public LiveData<List<User>> getUser(int id) {
        return userRepository.getUser(id);
    }

    public void insert(User user) {
        userRepository.insert(user);
        Log.d(TAG, "insert: user");
    }
}
