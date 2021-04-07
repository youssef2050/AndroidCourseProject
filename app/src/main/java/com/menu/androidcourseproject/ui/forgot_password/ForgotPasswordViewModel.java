package com.menu.androidcourseproject.ui.forgot_password;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.menu.androidcourseproject.database.room.repository.UserRepository;
import com.menu.androidcourseproject.model.User;

public class ForgotPasswordViewModel extends AndroidViewModel {
    private UserRepository userRepository;

    public ForgotPasswordViewModel(@NonNull Application application) {
        super(application);
        userRepository = new UserRepository(application);
    }

    public LiveData<User> checkPhoneNumber(String phone, String codePhone) {
        return userRepository.checkPhoneNumber(phone, codePhone);
    }
}