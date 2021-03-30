package com.menu.androidcourseproject.ui;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.menu.androidcourseproject.Model.User;
import com.menu.androidcourseproject.R;

public class MainActivity extends AppCompatActivity {
//    private UserViewModel userViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        User user = new User("youssef ezzeldeen",
                "yousf2050@homtail.com",
                "yousf2050",
                "1234567",
                "ps",
                "18/8/1998",
                "0592280825",
                true,
                true,
                "test");
        user.setId(1);
//        userViewModel = new ViewModelProvider(this).get(UserViewModel.class);
//        userViewModel.update(user);
    }

//    public void login(View view) {
//        userViewModel.login("yousf2050", "1234567").observe(this, user -> {
//            if (user != null)
//                System.out.println(user.getFullName());
//        });
//    }
}
