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
}
