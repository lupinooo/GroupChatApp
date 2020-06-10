package com.example.proiectandroid;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface FriendDAO {
    @Insert
    public void insert(FriendClass user);

    @Delete
    public void delete(FriendClass user);

    @Update
    public void update(FriendClass user);

    @Query("Select * from friends where usernameUser like :myUsername")
    public List<FriendClass> getFriends(String myUsername);

    @Query("Delete from friends where username like :username")
    public void deleteFriend(String username);

    @Query("Select distinct country from friends where usernameUser like :myUsername")
    public String[] getfriendsCountries(String myUsername);

    @Query("Select count(*) from friends where country like :country")
    public int friendsFromCountry(String country);


}
