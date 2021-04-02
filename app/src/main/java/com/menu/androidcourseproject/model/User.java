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
    private boolean verified;


    public User(String fullName, String email, String username, String password, String country, String birthDate, String phone, boolean administrator, boolean male, String URLImage, boolean verified) {
        this.fullName = fullName;
        this.email = email;
        this.username = username;
        this.password = password;
        this.country = country;
        this.birthDate = birthDate;
        this.phone = phone;
        Administrator = administrator;
        Male = male;
        this.URLImage = URLImage;
        this.verified = verified;
    }

    public User() {
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

    public boolean isVerified() {
        return verified;
    }

    private boolean isValidEmail() {
        System.out.println("is email");
        return email != null && !TextUtils.isEmpty(email) && Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }

    public boolean isValidPassword() {
        System.out.println("is password");
        return password != null && !TextUtils.isEmpty(password) && password.length() >= 8;
    }

    private boolean isValidPhone() {
        System.out.println("is phone");
        return phone != null && !TextUtils.isEmpty(phone) && Patterns.PHONE.matcher(phone).matches();
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setAdministrator(boolean administrator) {
        Administrator = administrator;
    }

    public void setMale(boolean male) {
        Male = male;
    }

    public void setURLImage(String URLImage) {
        this.URLImage = URLImage;
    }

    public void setVerified(boolean verified) {
        this.verified = verified;
    }

    public boolean isOk() {
        System.out.println(String.valueOf(isValidEmail()) + " " + isValidPassword() + " " + isValidPhone());
        return isValidEmail() && isValidPassword() && isValidPhone();
    }
}
