package com.menu.androidcourseproject.ui;

import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.menu.androidcourseproject.databinding.ActivityMainBinding;
import com.menu.androidcourseproject.model.Meal;
import com.menu.androidcourseproject.ui.home.HomeViewModel;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding activityMainBinding;
    private HomeViewModel homeViewModel;
//    private NavController navController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityMainBinding = ActivityMainBinding.inflate(getLayoutInflater());
        homeViewModel = new ViewModelProvider(this).get(HomeViewModel.class);
//        navController = Navigation.findNavController(this, R.id.fragment_item);
//        NavigationUI.setupActionBarWithNavController(this, navController);
        for (int i = 0; i < 8; i++) {
            Meal meal = new Meal("test" + i, "ymym" + i, 15.4, "https://picsum.photos/id/" + (237 + i) + "/200", true, 3.5);
            homeViewModel.insert(meal);
        }
        statusBarStyle(getWindow());
        homeViewModel.meals.observe(this, new Observer<List<Meal>>() {
            @Override
            public void onChanged(List<Meal> meals) {
                System.out.println(meals.size());
            }
        });
    }

    private void statusBarStyle(Window w) {
        w.setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
    }

//    @Override
//    public boolean onSupportNavigateUp() {
////        return navController.navigateUp();
//
//    }
}
