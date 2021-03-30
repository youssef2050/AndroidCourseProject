package com.menu.androidcourseproject.Model;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Meal {
    @PrimaryKey(autoGenerate = true)
    private int id;

    private String mealTitle;
    private String description;
    private double prise;
    private String URLImage;
    private boolean Cash;

    public Meal() {
    }

    public Meal(String mealTitle, String description, double prise, String URLImage, boolean cash) {
        this.mealTitle = mealTitle;
        this.description = description;
        this.prise = prise;
        this.URLImage = URLImage;
        Cash = cash;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMealTitle() {
        return mealTitle;
    }

    public void setMealTitle(String mealTitle) {
        this.mealTitle = mealTitle;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPrise() {
        return prise;
    }

    public void setPrise(double prise) {
        this.prise = prise;
    }

    public String getURLImage() {
        return URLImage;
    }

    public void setURLImage(String URLImage) {
        this.URLImage = URLImage;
    }

    public boolean isCash() {
        return Cash;
    }

    public void setCash(boolean cash) {
        Cash = cash;
    }
}
