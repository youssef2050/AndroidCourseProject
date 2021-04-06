package com.menu.androidcourseproject.ui.details;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import com.menu.androidcourseproject.database.room.repository.MealRepository;
import com.menu.androidcourseproject.database.room.repository.PurchaseRepository;
import com.menu.androidcourseproject.model.Meal;
import com.menu.androidcourseproject.model.Purchase;

public class DetailsViewModel extends AndroidViewModel {
    private MealRepository mealRepository;
    private PurchaseRepository purchaseRepository;

    public DetailsViewModel(@NonNull Application application) {
        super(application);
        mealRepository = new MealRepository(application);
        purchaseRepository = new PurchaseRepository(application);
    }

    public void Update(Meal meal) {
        mealRepository.update(meal);
    }

    public void insert(Purchase purchase) {
        purchaseRepository.insert(purchase);
    }

}