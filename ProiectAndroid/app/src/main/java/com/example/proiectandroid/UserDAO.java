package com.example.proiectandroid;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface UserDAO {

    @Insert
    public void insert(UserClass user);

    @Delete
    public void delete(UserClass user);

    @Update
    public void update(UserClass user);

    @Query("Select * from useri")
    public List<UserClass> getAll();

    @Query("Select * from useri where username like :username")
    public UserClass login(String username);

    @Query("Update useri set username=:username, password=:password, fullName=:fullName, country=:country, email=:email")
     public void updateUser(String username, String password, String fullName, String country, String email);

    @Query("Delete from useri where username like :username")
    public void deleteUser(String username);

    @Query("Select count(*) from useri where username like :username")
    public int countUsers(String username);

    @Query("Update useri set password=:password where username like :username")
    public void updatePass(String username, String password);

    @Query("Select password from useri where username like :username")
    public String getPass(String username);
}
