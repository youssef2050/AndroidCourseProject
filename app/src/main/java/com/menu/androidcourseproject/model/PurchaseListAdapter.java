package com.menu.androidcourseproject.model;

public class PurchaseListAdapter {
    private int id;
    private String date;
    private String mealTitle;
    private double prise;

    public PurchaseListAdapter(int id, String date, String mealTitle, double prise) {
        this.id = id;
        this.date = date;
        this.mealTitle = mealTitle;
        this.prise = prise;
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

    public double getPrise() {
        return prise;
    }

    public void setPrise(double prise) {
        this.prise = prise;
    }
}
