package com.menu.androidcourseproject.ui.login;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.menu.androidcourseproject.database.room.repository.UserRepository;
import com.menu.androidcourseproject.model.User;

public class LoginViewModel extends AndroidViewModel {
    private UserRepository userRepository;

    public LoginViewModel(@NonNull Application application) {
        super(application);
        userRepository = new UserRepository(application);
    }

    public LiveData<User> login(String username, String password) {
        return userRepository.login(username, password);
    }

    public void update(User user) {
        userRepository.update(user);
    }

}