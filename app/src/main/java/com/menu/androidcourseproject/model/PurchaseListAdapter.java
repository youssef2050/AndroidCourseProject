package com.menu.androidcourseproject.model;

public class PurchaseListAdapter {
    private int id;
    private String date;
    private String mealTitle;
    private int numberMeal;
    private double prise;
    private String fullName;
    private long timeInMillis;

    public PurchaseListAdapter(int id, String date, String mealTitle, int numberMeal, double prise, String fullName, long timeInMillis) {
        this.id = id;
        this.date = date;
        this.mealTitle = mealTitle;
        this.numberMeal = numberMeal;
        this.prise = prise;
        this.fullName = fullName;
        this.timeInMillis = timeInMillis;
    }

    public long getTimeInMillis() {
        return timeInMillis;
    }

    public void setTimeInMillis(long timeInMillis) {
        this.timeInMillis = timeInMillis;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getMealTitle() {
        return mealTitle;
    }

    public void setMealTitle(String mealTitle) {
        this.mealTitle = mealTitle;
    }

    public int getNumberMeal() {
        return numberMeal;
    }

    public void setNumberMeal(int numberMeal) {
        this.numberMeal = numberMeal;
    }

    public double getPrise() {
        return prise;
    }

    public void setPrise(double prise) {
        this.prise = prise;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }
}
