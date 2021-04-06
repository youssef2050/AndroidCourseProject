package com.menu.androidcourseproject.model;

import android.text.TextUtils;
import android.util.Patterns;

import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import java.util.HashMap;
import java.util.Map;

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
    @Ignore
    private Map<String, Boolean> errors;


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
        errors = new HashMap<>();
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

    private boolean isValidEmail() {
        errors.put("email", email != null && !TextUtils.isEmpty(email) && Patterns.EMAIL_ADDRESS.matcher(email).matches());
        return email != null && !TextUtils.isEmpty(email) && Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }

    public boolean isValidPassword() {
        errors.put("password", password != null && !TextUtils.isEmpty(password) && password.length() >= 8);
        return password != null && !TextUtils.isEmpty(password) && password.length() >= 8;
    }

    private boolean isValidPhone() {
        errors.put("phone", phone != null && !TextUtils.isEmpty(phone) && Patterns.PHONE.matcher(phone).matches());
        return phone != null && !TextUtils.isEmpty(phone) && Patterns.PHONE.matcher(phone).matches();
    }

    private boolean isEmptyUsername() {
        errors.put("username", username != null && !TextUtils.isEmpty(username));
        return username != null && !TextUtils.isEmpty(username);
    }

    private boolean isEmptyBirthDate() {
        errors.put("birthDate", birthDate != null && !TextUtils.isEmpty(birthDate));
        return birthDate != null && !TextUtils.isEmpty(birthDate);
    }

    private boolean isEmptyFullName() {
        errors.put("FullName", fullName != null && !TextUtils.isEmpty(fullName));
        return fullName != null && !TextUtils.isEmpty(fullName);
    }

    public boolean isOk() {
        return isEmptyFullName() && isValidEmail() && isEmptyUsername() && isValidPassword() && isValidPhone()
                && isEmptyBirthDate();
    }

    public Map<String, Boolean> getErrors() {
        return errors;
    }
}
