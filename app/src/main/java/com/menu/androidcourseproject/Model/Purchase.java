package com.menu.androidcourseproject.Model;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.ForeignKey;

@Entity(primaryKeys = {"mealId", "date"})
public class Purchase {

    @ForeignKey(entity = Meal.class, parentColumns = {"id"}, childColumns = {"mealId"})
    private int mealId;
    @NonNull
    private String date;

    public Purchase(int mealId, String date) {
        this.mealId = mealId;
        this.date = date;
    }

    public int getMealId() {
        return mealId;
    }

    public String getDate() {
        return date;
    }
}
