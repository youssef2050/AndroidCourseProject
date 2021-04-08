package com.menu.androidcourseproject.ui.change_password;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.menu.androidcourseproject.database.room.repository.UserRepository;
import com.menu.androidcourseproject.model.User;

public class ChangePasswordViewModel extends AndroidViewModel {
    private UserRepository userRepository;

    public ChangePasswordViewModel(@NonNull Application application) {
        super(application);
        userRepository = new UserRepository(application);
    }

    public LiveData<User> checkOldPassword(String password, int id) {
        return userRepository.checkOldPassword(password, id);
    }

    public void update(User user) {
        userRepository.update(user);
    }

    public LiveData<User> getUser(int id) {
        return userRepository.getUser(id);
    }
}