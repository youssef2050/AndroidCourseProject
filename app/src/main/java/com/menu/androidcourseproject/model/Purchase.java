package com.menu.androidcourseproject.model;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.ForeignKey;

@Entity(primaryKeys = {"mealId", "userId", "date", "numberMeal", "timeInMillis"})
public class Purchase {

    @ForeignKey(entity = Meal.class, parentColumns = {"id"}, childColumns = {"mealId"})
    private int mealId;
    @ForeignKey(entity = User.class, parentColumns = {"id"}, childColumns = {"userId"})
    private int userId;
    @NonNull
    private String date;
    private int numberMeal;
    private long timeInMillis;

    public Purchase(int mealId, int userId, @NonNull String date, int numberMeal, long timeInMillis) {
        this.mealId = mealId;
        this.userId = userId;
        this.date = date;
        this.numberMeal = numberMeal;
        this.timeInMillis = timeInMillis;
    }

    public int getMealId() {
        return mealId;
    }

    public void setMealId(int mealId) {
        this.mealId = mealId;
    }


    public int getNumberMeal() {
        return numberMeal;
    }

    public void setNumberMeal(int numberMeal) {
        this.numberMeal = numberMeal;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public long getTimeInMillis() {
        return timeInMillis;
    }

    public void setTimeInMillis(long timeInMillis) {
        this.timeInMillis = timeInMillis;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }
}
