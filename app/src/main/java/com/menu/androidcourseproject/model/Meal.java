package com.menu.androidcourseproject.model;

import android.os.Parcel;
import android.os.Parcelable;
import android.widget.ImageView;

import androidx.databinding.BindingAdapter;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import com.bumptech.glide.Glide;

import org.jetbrains.annotations.NotNull;

import java.util.List;

@Entity
public class Meal implements Parcelable {
    @PrimaryKey(autoGenerate = true)
    private int id;
    private String mealTitle;
    private String description;
    private double prise;
    private String URLImage;
    private boolean Cash;
    private double rate;
    private boolean favorite;
    @Ignore
    public static List<Meal> MEALS;

    public Meal(String mealTitle, String description, double prise, String URLImage, boolean cash, double rate, boolean favorite) {
        this.mealTitle = mealTitle;
        this.description = description;
        this.prise = prise;
        this.URLImage = URLImage;
        Cash = cash;
        this.rate = rate;
        this.favorite = favorite;
    }

    public Meal() {
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

    public boolean isFavorite() {
        return favorite;
    }

    public void setFavorite(boolean favorite) {
        this.favorite = favorite;
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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {

    }

}
