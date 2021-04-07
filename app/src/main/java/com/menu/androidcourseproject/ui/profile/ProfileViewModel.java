package com.menu.androidcourseproject.ui.profile;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.menu.androidcourseproject.database.room.repository.UserRepository;
import com.menu.androidcourseproject.model.User;

public class ProfileViewModel extends AndroidViewModel {
    private UserRepository userRepository;

    public ProfileViewModel(@NonNull Application application) {
        super(application);
        userRepository = new UserRepository(application);
    }

    public LiveData<User> getUser(int id) {
        return userRepository.getUser(id);
    }
}