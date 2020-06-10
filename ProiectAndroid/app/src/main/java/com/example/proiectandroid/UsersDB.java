package com.example.proiectandroid;

import android.content.Context;
import android.content.ContentUris;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;


@Database(entities = {UserClass.class, FriendClass.class}, version = 2, exportSchema = false)
public abstract class UsersDB extends RoomDatabase {
    private final static String DB_NAME="chat_db";
    private static UsersDB instanta;

    public static UsersDB getInstance(Context context){
        if(instanta==null){
            instanta= Room.databaseBuilder(context, UsersDB.class, DB_NAME)
                    .allowMainThreadQueries()
                    .fallbackToDestructiveMigration()
                    .build();  //asa se construieste instanta
        }
        return instanta;
    }

    //metoda abstracta
    public abstract UserDAO getUserDao();
    public abstract FriendDAO getFriendsDAO();


}
