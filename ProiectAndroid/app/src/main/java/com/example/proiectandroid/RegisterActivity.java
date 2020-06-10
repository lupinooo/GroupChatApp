package com.example.proiectandroid;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Patterns;
import android.util.Xml;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import org.xmlpull.v1.XmlSerializer;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.StringWriter;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Random;

public class RegisterActivity extends AppCompatActivity {
    public String [] options=null;
    public ArrayAdapter<String> adapter;
    public Spinner country;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        final UsersDB usersDB=UsersDB.getInstance(this);
        country=(Spinner)findViewById(R.id.spinner);

        try{
            GetContactsTask task=new GetContactsTask(){
                @Override
                protected void onPostExecute(String[] s) {
                   adapter=new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_spinner_dropdown_item, s);
                   country.setAdapter(adapter);

                }
            };

            task.execute(new URL("https://api.myjson.com/bins/19uy28"));
        }
        catch(MalformedURLException ex){
            ex.printStackTrace();
        }
        catch(Exception ex){
            ex.printStackTrace();
        }

        Button btnRegister = (Button) findViewById(R.id.btnRegister);
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //validare date
                EditText firstname = (EditText) findViewById(R.id.firstname_text);
                EditText lastname = (EditText) findViewById(R.id.lastname_text);
                EditText email = (EditText) findViewById(R.id.email_text);
                EditText user = (EditText) findViewById(R.id.username_text);
                EditText pass = (EditText) findViewById(R.id.password_text);
                CheckBox check=(CheckBox) findViewById(R.id.checkBox);

                int count=usersDB.getUserDao().countUsers(user.getText().toString());


                if (firstname.getText().toString().equals("")) {
                    firstname.setError("Please introduce your first name!");
                } else if (lastname.getText().toString().equals("")) {
                    lastname.setError("Please introduce your last name!");
                } else if (email.getText().toString().equals("")) {
                    email.setError("Please introduce a valid email address!");}
               else if (user.getText().toString().equals("")||user.getText().length()<3) {
                    user.setError("Please introduce a valid username (at least 3 characters long)!");
                }
               else if(count>0){
                   user.setError("The username is already taken");
                }
               else if (pass.getText().toString().equals("")||pass.getText().length()<5) {
                    pass.setError("Please introduce your password (at least 5 characters long)!");
                }
                else if (firstname.getText().toString().equals("")) {
           firstname.setError("Please introduce your first name!");
        } else if (lastname.getText().toString().equals("")) {
            lastname.setError("Please introduce your last name!");
        } else if (email.getText().toString().equals("")) {
            email.setError("Please introduce a valid email address!");
        } else if (pass.getText().toString().equals("") || pass.getText().toString().length() < 5) {
           pass.setError("Please introduce your password (at least 5 characters long)!");
        } else if (user.getText().toString().equals("") || user.getText().toString().length() < 3) {
            pass.setError("Please introduce a valid username (at least 3 characters long)!");
       }
                else if(!check.isChecked()){
                        check.setError("Please read and acccept our terms and conditions.");
                }
                else{
                    UserClass registeredUser=new UserClass(user.getText().toString(),
                            pass.getText().toString(), firstname.getText().toString()+" "+lastname.getText().toString(), country.getSelectedItem().toString()
                            , email.getText().toString());
                    usersDB.getUserDao().insert(registeredUser);
                    Toast.makeText(getApplicationContext(), registeredUser.toString(), Toast.LENGTH_LONG).show();
                    //aici se va face si conexiunea cu baza de date pentru a adauga userii+ne reintoarcem la activitatea de Welcome
                    //pt ca userul sa se logheze cu user si parola tocmai create
                    Intent i1=new Intent(getApplicationContext(), WelcomeActivity.class);
                    startActivity(i1);
                }

            }
    });
}
}




