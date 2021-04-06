package com.menu.androidcourseproject.ui.home;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.menu.androidcourseproject.database.room.repository.MealRepository;
import com.menu.androidcourseproject.model.Meal;

import java.util.List;

public class HomeViewModel extends AndroidViewModel {
    private static final String TAG = "HomeViewModel";
    private MealRepository mealRepository;
    public LiveData<List<Meal>> meals;
    public LiveData<String> search = new MutableLiveData<>();

    public HomeViewModel(@NonNull Application application) {
        super(application);
        mealRepository = new MealRepository(application);
        meals = mealRepository.getGetAllMeal();
    }

    public void insert(Meal meal) {
        mealRepository.insert(meal);
    }

    public void update(Meal meal) {
        mealRepository.update(meal);
    }


    public LiveData<List<Meal>> getMeals() {
        return meals;
    }
}