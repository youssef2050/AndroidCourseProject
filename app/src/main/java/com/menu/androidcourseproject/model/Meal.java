package com.menu.androidcourseproject.model;

import android.widget.ImageView;

import androidx.databinding.BindingAdapter;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.bumptech.glide.Glide;

import org.jetbrains.annotations.NotNull;

import java.util.List;

@Entity
public class Meal {
    @PrimaryKey(autoGenerate = true)
    private int id;
    private String mealTitle;
    private String description;
    private double prise;
    private String URLImage;
    private boolean Cash;
    private double rate;
    public static List<Meal> MEALS;

    public Meal(String mealTitle, String description, double prise, String URLImage, boolean cash, double rate) {
        this.mealTitle = mealTitle;
        this.description = description;
        this.prise = prise;
        this.URLImage = URLImage;
        Cash = cash;
        this.rate = rate;
    }

    public Meal() {
    }

    public Meal(int id, String mealTitle, String description, double prise, String URLImage, boolean cash, double rate) {
        this.id = id;
        this.mealTitle = mealTitle;
        this.description = description;
        this.prise = prise;
        this.URLImage = URLImage;
        Cash = cash;
        this.rate = rate;
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

    public double getRate() {
        return rate;
    }

    public void setRate(double rate) {
        this.rate = rate;
    }

    @NotNull
    @Override
    public String toString() {
        return "Meal{" +
                "id=" + id +
                ", mealTitle='" + mealTitle + '\'' +
                ", description='" + description + '\'' +
                ", prise=" + prise +
                ", URLImage='" + URLImage + '\'' +
                ", Cash=" + Cash +
                ", reat=" + rate +
                '}';
    }


    @BindingAdapter("android:loadImage")
    public static void loadImage(ImageView imageView, String imageUrl) {
        Glide.with(imageView)
                .load(imageUrl)
                .into(imageView);
    }
}
