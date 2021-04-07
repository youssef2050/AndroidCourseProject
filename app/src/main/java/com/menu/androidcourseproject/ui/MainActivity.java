package com.menu.androidcourseproject.ui;

import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;

import com.menu.androidcourseproject.R;
import com.menu.androidcourseproject.databinding.ActivityMainBinding;
import com.menu.androidcourseproject.general.AddImages;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding activityMainBinding;
    private NavController navController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityMainBinding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(activityMainBinding.getRoot());
        NavHostFragment navHostFragment = (NavHostFragment) getSupportFragmentManager()
                .findFragmentById(R.id.nav_host_fragment);
        navController = navHostFragment.getNavController();
        SharedPreferences sharedPreferences = getSharedPreferences(getString(R.string.shared_key), MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        if (sharedPreferences.getString(getString(R.string.url_default), "").equals("")) {
            Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.beef);
            String s = new AddImages(this).saveImageInStorage(bitmap, "mealImages", "default");
            editor.putString(getString(R.string.url_default), s);
            editor.apply();
        }
    }

    private void statusBarStyle(Window w) {
        w.setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
    }

    @Override
    public boolean onSupportNavigateUp() {
        return navController.navigateUp() || super.onSupportNavigateUp();
    }
}
