package com.menu.androidcourseproject.model;

import android.text.TextUtils;
import android.util.Patterns;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class User {
    @PrimaryKey(autoGenerate = true)
    private int id;
    private String fullName;
    private String email;
    private String username;
    private String password;
    private String country;
    private String birthDate;
    private String phone;
    private boolean Administrator;
    private boolean Male;
    private String URLImage;

    public User(String fullName, String email, String username, String password, String country, String birthDate, String phone, boolean Administrator, boolean Male, String URLImage) {
        this.fullName = fullName;
        this.email = email;
        this.username = username;
        this.password = password;
        this.country = country;
        this.birthDate = birthDate;
        this.phone = phone;
        this.Administrator = Administrator;
        this.Male = Male;
        this.URLImage = URLImage;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public String getEmail() {
        return email;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getCountry() {
        return country;
    }

    public String getBirthDate() {
        return birthDate;
    }

    public String getPhone() {
        return phone;
    }

    public boolean isAdministrator() {
        return Administrator;
    }

    public boolean isMale() {
        return Male;
    }

    public String getURLImage() {
        return URLImage;
    }

    private boolean isValidEmail() {
        System.out.println("is email");
        return email != null && !TextUtils.isEmpty(email) && Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }

    private boolean isValidPassword() {
        System.out.println("is password");
        return password != null && !TextUtils.isEmpty(password) && password.length() >= 8;
    }

    private boolean isValidPhone() {
        System.out.println("is phone");
        return phone != null && !TextUtils.isEmpty(phone) && Patterns.PHONE.matcher(phone).matches();
    }

    public boolean isOk() {
        System.out.println(String.valueOf(isValidEmail()) + " " + isValidPassword() + " " + isValidPhone());
        return isValidEmail() && isValidPassword() && isValidPhone();
    }
}
