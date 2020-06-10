package com.example.proiectandroid;

import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import static androidx.room.ForeignKey.CASCADE;

@Entity(tableName = "friends", foreignKeys = @ForeignKey(entity = UserClass.class,
        parentColumns = "username",
        childColumns = "usernameUser",
        onDelete = CASCADE))
//FriendClass are cam aceleasi atribute ca UserClass pt ca in fond un friend e tot un user, dar nu puteam sa folosesc inheritence ca se facea
//override de PK
public class FriendClass{
    @PrimaryKey(autoGenerate = true)
    public int friendId; //id pt prieten
    private String username;
    private String fullName;
    private String country;
    private String email;
    public String usernameUser;

    @Ignore
    public FriendClass(){
    }

    public FriendClass(String username, String fullName, String country, String email, String usernameUser) {
        this.username = username;
        this.fullName = fullName;
        this.country = country;
        this.email = email;
        this.usernameUser = usernameUser;
    }

    public int getFriendId() {
        return friendId;
    }

    public void setFriendId(int friendId) {
        this.friendId = friendId;
    }

    public String getUsername() {
        return username;
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

    public String getUsernameUser() {
        return usernameUser;
    }

    public void setUsernameUser(String usernameUser) {
        this.usernameUser = usernameUser;
    }
}





