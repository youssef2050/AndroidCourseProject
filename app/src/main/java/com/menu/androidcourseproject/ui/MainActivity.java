package com.menu.androidcourseproject.ui;

import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;

import com.menu.androidcourseproject.R;
import com.menu.androidcourseproject.databinding.ActivityMainBinding;
import com.menu.androidcourseproject.model.User;
import com.menu.androidcourseproject.ui.register.RegisterViewModel;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding activityMainBinding;
    private NavController navController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityMainBinding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(activityMainBinding.getRoot());
//        statusBarStyle(getWindow());
        NavHostFragment navHostFragment = (NavHostFragment) getSupportFragmentManager()
                .findFragmentById(R.id.nav_host_fragment);
        NavController navCo = navHostFragment.getNavController();
        final User user = new User("youssef",
                "youssef@test.com",
                "yousf2050",
                "12345679",
                "ps",
                "18/8/1998",
                "0592280825",
                true,
                true,
                "https://www.picsum.photos/id/244/200", true);
        RegisterViewModel registerViewModel = new ViewModelProvider(this).get(RegisterViewModel.class);
        if (user.isOk()) {
            System.out.println("is ok");
            registerViewModel.checkUsernameAndEmail(user.getEmail(), user.getUsername()).observe(this, new Observer<User>() {
                @Override
                public void onChanged(User users) {
                    if (users == null) {
                        System.out.println("user is null");
                        registerViewModel.insert(user);
                    }
                }
            });

        }
        registerViewModel.getAllUser().observe(this, users -> {
            System.out.println(users.size());
        });
    }

    private void statusBarStyle(Window w) {
        w.setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
    }

    @Override
    public boolean onSupportNavigateUp() {
        return navController.navigateUp() || super.onSupportNavigateUp();
    }
}
