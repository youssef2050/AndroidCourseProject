package com.menu.androidcourseproject.ui.new_meal;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import com.menu.androidcourseproject.database.room.repository.MealRepository;
import com.menu.androidcourseproject.model.Meal;

public class NewItemViewModel extends AndroidViewModel {
    private MealRepository mealRepository;

    public NewItemViewModel(@NonNull Application application) {
        super(application);
        mealRepository = new MealRepository(application);
    }

    public void insert(Meal meal) {
        mealRepository.insert(meal);
    }
    // TODO: Implement the ViewModel
}