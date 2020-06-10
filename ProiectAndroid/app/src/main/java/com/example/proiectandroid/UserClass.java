package com.example.proiectandroid;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity(tableName = "useri")
public class UserClass {


    @PrimaryKey
    @NonNull
    private String username;
    private String password;
    private String fullName;
    private String country;
    private String email;


    @NonNull
    @Override
    public String toString() {
        return "Username: "+username+"\nFull Name: "+fullName+"\nEmail: "+email+"\nCountry: "+country+"\n";
    }

    @Ignore
    public UserClass(){

    }

    @Ignore  //un constructor fara parola pt a putea fi fol in clasa FriendClass
    public UserClass(String username, String fullName, String country, String email){
        this.username=username;
        this.fullName = fullName;
        this.country = country;
        this.email = email;
    }

    public UserClass(String username, String password, String fullName, String country, String email) {
        this.username = username;
        this.password=password;
        this.fullName = fullName;
        this.country = country;
        this.email = email;
    }

    public String getUsername() {
        return username;
    }


    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
