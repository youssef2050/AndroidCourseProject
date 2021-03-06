package com.menu.androidcourseproject.ui.verified;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.menu.androidcourseproject.database.room.repository.UserRepository;
import com.menu.androidcourseproject.model.User;

public class VerifiedViewModel extends AndroidViewModel {
    private UserRepository userRepository;

    public VerifiedViewModel(@NonNull Application application) {
        super(application);
        userRepository = new UserRepository(application);
    }

    public void update(User user) {
        userRepository.update(user);
    }

    public LiveData<User> getUser(int id) {
        return userRepository.getUser(id);
    }
}