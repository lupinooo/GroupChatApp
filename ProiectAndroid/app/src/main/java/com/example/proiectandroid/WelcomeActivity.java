package com.example.proiectandroid;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.style.UnderlineSpan;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;

public class WelcomeActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        final UsersDB usersDB=UsersDB.getInstance(this);
        Button btnLogin = (Button) findViewById(R.id.buttonLogin);
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final EditText user = (EditText) findViewById(R.id.username);
                final EditText pass = (EditText) findViewById(R.id.password);
                Intent main = new Intent(getApplicationContext(), ProfileActivity.class);
                List<UserClass> listaUseri=usersDB.getUserDao().getAll();
                int OK=0;
                for (UserClass userDB: listaUseri) {
                    if(userDB.getUsername().equals(user.getText().toString())&&
                    userDB.getPassword().equals(pass.getText().toString())){
                        Toast.makeText(getApplicationContext(), "Welcome back!", Toast.LENGTH_LONG).show();
                        main.putExtra("username", userDB.getUsername());
                        startActivity(main);
                        OK=1;
                    }
                }
                if(OK==0){
                    Toast.makeText(getApplicationContext(), "Incorrect username or password", Toast.LENGTH_LONG).show();
                }

            }
        });

                TextView reg = (TextView) findViewById(R.id.registerLink);
        SpannableString content=new SpannableString(reg.getText());
        content.setSpan(new UnderlineSpan(),0,content.length(),0);
        reg.setText(content);
                reg.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent register = new Intent(getApplicationContext(), RegisterActivity.class);
                        startActivity(register);
                    }
                });

}
    }





