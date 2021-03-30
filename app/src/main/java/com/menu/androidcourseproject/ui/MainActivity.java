package com.menu.androidcourseproject.ui;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.menu.androidcourseproject.Database.ViewModel.UserViewModel;
import com.menu.androidcourseproject.Model.User;
import com.menu.androidcourseproject.R;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    private UserViewModel userViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        User user = new User("youssef ezzeldeen",
                "yousf2050@homtail.com",
                "yousf2050",
                "123456",
                "ps",
                "18/8/1998",
                "0592280825",
                true,
                true,
                "test");
        userViewModel = new ViewModelProvider(this).get(UserViewModel.class);
        userViewModel.insert(user);
        userViewModel.login("youssef2050", "123456")
                .observe(this, new Observer<List<User>>() {
                    @Override
                    public void onChanged(List<User> users) {
                        System.out.println(users.size());
                    }
                });
    }
}
