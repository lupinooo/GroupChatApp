package com.example.proiectandroid;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

public class ProfilePic extends AppCompatActivity {
    public static final String PREFERENCES_FILE_NAME="ImgPrefs";
    public int pic1;
    public int pic2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_pic);
        LinearLayout ll=findViewById(R.id.ll);
        final ImageView profilePic=(ImageView)findViewById(R.id.profilePic);

        SharedPreferences prefs=getSharedPreferences(PREFERENCES_FILE_NAME, 0);
        pic1=prefs.getInt("1", 0);
        ImageView im1=new ImageView(this);
        im1.setImageResource(pic1);
        ll.addView(im1);


        pic2=prefs.getInt("2", 0);
        ImageView im2=new ImageView(this);
        im2.setImageResource(pic2);
        ll.addView(im2);


        int pic3=prefs.getInt("3", 0);
        ImageView im3=new ImageView(this);
        im3.setImageResource(pic3);
        ll.addView(im3);

        int pic4=prefs.getInt("4", 0);
        ImageView im4=new ImageView(this);
        im4.setImageResource(pic4);
        ll.addView(im4);

        int pic5=prefs.getInt("5", 0);
        ImageView im5=new ImageView(this);
        im5.setImageResource(pic5);
        ll.addView(im5);

        int pic6=prefs.getInt("6", 0);
        ImageView im6=new ImageView(this);
        im6.setImageResource(pic6);
        ll.addView(im6);


    }


}
