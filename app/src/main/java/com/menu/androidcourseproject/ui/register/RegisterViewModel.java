package com.menu.androidcourseproject.ui.register;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.menu.androidcourseproject.database.room.repository.UserRepository;
import com.menu.androidcourseproject.model.User;

import java.util.List;

public class RegisterViewModel extends AndroidViewModel {
    private UserRepository userRepository;
    private LiveData<List<User>> allUser;

    public RegisterViewModel(@NonNull Application application) {
        super(application);
        userRepository = new UserRepository(application);
        allUser = userRepository.getGetAllUsers();
    }

    public LiveData<User> checkUsernameAndEmail(String email, String username) {
        return userRepository.checkUsernameAndEmail(email, username);
    }

    public void insert(User user) {
        userRepository.insert(user);
    }

    public LiveData<List<User>> getAllUser() {
        return allUser;
    }
}