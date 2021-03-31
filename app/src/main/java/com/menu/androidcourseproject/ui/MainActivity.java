package com.menu.androidcourseproject.ui;

import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.NavigationUI;

import com.menu.androidcourseproject.R;
import com.menu.androidcourseproject.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding activityMainBinding;
    private NavController navController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        navController = Navigation.findNavController(this, R.id.fragment);
        NavigationUI.setupActionBarWithNavController(this, navController);
        statusBarStyle(getWindow());
    }

    private void statusBarStyle(Window w) {
        w.setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
    }

    @Override
    public boolean onSupportNavigateUp() {
        return navController.navigateUp();

    }
}
