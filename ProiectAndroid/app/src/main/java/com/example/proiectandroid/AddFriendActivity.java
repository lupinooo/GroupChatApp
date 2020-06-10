package com.example.proiectandroid;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import org.w3c.dom.Text;

import java.io.FileOutputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

public class AddFriendActivity extends AppCompatActivity {
    public RadioGroup rg;
    public RadioButton[] rb;
    public RadioButton[] rb1;
    LinearLayout ll;
    private static final String FILE_NAME="users_list.txt";
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addfriend);
        ll=findViewById(R.id.ll);
        rg=findViewById(R.id.rg);
        final UsersDB usersDB=UsersDB.getInstance(this);
        final List<UserClass> listaUseri=usersDB.getUserDao().getAll();


        Intent intent5=getIntent();
        final String myUsername=intent5.getExtras().getString("myUsername");

        //afisare lista de useri din aplicatie

        rb1=new RadioButton[listaUseri.size()];
        for(int i=0;i<listaUseri.size();i++) {
            if(!listaUseri.get(i).getUsername().equals(myUsername)){
            rb1[i]=new RadioButton(getApplicationContext());
            rb1[i].setText(listaUseri.get(i).toString());
            rb1[i].setTextColor(Color.RED);
            rg.addView(rb1[i]);}
        }


        //buton de adaugare prieten
        Button add=(Button)findViewById(R.id.buttonAddFriend);
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int selectedId=rg.getCheckedRadioButtonId();
                RadioButton rb=(RadioButton)findViewById(selectedId);
                for(int i=0;i<listaUseri.size();i++){
                    if(listaUseri.get(i).toString().equals(rb.getText().toString())){
                        FriendClass friend=new FriendClass(listaUseri.get(i).getUsername(),listaUseri.get(i).getFullName(),
                                listaUseri.get(i).getCountry(), listaUseri.get(i).getEmail(), myUsername);
                        usersDB.getFriendsDAO().insert(friend);

                    }
                }
                Intent intentFriends=new Intent(AddFriendActivity.this, ProfileActivity.class);
                intentFriends.putExtra("username", myUsername);
                startActivity(intentFriends);
        }
        });

        Button save=(Button)findViewById(R.id.buttonSave);
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    String text = "";
                    int count=rg.getChildCount();
                    for(int i=0;i<count;i++){
                        View o=rg.getChildAt(i);
                        if(o instanceof  RadioButton){
                            text+=((RadioButton) o).getText().toString()+"\n";
                        }
                    }
                FileOutputStream fos=null;
                    try{
                        fos=openFileOutput(FILE_NAME, Activity.MODE_PRIVATE);
                        fos.write(text.getBytes());
                        Toast.makeText(getApplicationContext(), "Saved file to "+FILE_NAME, Toast.LENGTH_LONG).show();
                    }
                    catch(Exception ex){
                        ex.printStackTrace();
                }


            }
        });

    }
}
