package com.example.proiectandroid;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.Layout;
import android.text.SpannableString;
import android.text.style.UnderlineSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class PersonalDetailsActivity extends AppCompatActivity {
    EditText username;
    EditText fullName;
    EditText email;
    EditText country;
    public String password;
    UserClass loggedUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personal_details);
        final UsersDB userDB=UsersDB.getInstance(this);
        username=(EditText)findViewById(R.id.editTextUsername);
        fullName=(EditText)findViewById(R.id.editTextFullName);
        email=(EditText)findViewById(R.id.editTextEmail);
        country=(EditText)findViewById(R.id.editTextCountry);
        Intent intent5=getIntent();
        final String myUsername=intent5.getExtras().getString("user");
        loggedUser=userDB.getUserDao().login(myUsername);
        username.setText(myUsername);
        fullName.setText(loggedUser.getFullName());
        email.setText(loggedUser.getEmail());
        country.setText(loggedUser.getCountry());
        password=loggedUser.getPassword();

        Button SAVE=(Button)findViewById(R.id.buttonSaveDetails);
        SAVE.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                userDB.getUserDao().updateUser(username.getText().toString(), password, fullName.getText().toString(),
                        country.getText().toString(), email.getText().toString());
                Toast.makeText(getApplicationContext(), "Your changes have been saved!", Toast.LENGTH_LONG).show();
                Intent intent1=new Intent(PersonalDetailsActivity.this, ProfileActivity.class);
                intent1.putExtra("username", username.getText().toString());
                startActivity(intent1);
            }
        });

        Button DELETE=(Button) findViewById(R.id.buttonDeleteAccount);
        DELETE.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog dlg=new AlertDialog.Builder(PersonalDetailsActivity.this).create();
                LayoutInflater inflater=getLayoutInflater();
                View view=inflater.inflate(R.layout.dialog_delete, null);
                dlg.setView(view);
                dlg.setButton(AlertDialog.BUTTON_NEGATIVE, "No, cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
                dlg.setButton(AlertDialog.BUTTON_POSITIVE, "Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        userDB.getUserDao().deleteUser(username.getText().toString());
                        dialog.dismiss();
                        Intent i=new Intent(getApplicationContext(), WelcomeActivity.class);
                        startActivity(i);

                    }
                });
                dlg.show();
            }
        });


        TextView changePass=(TextView)findViewById(R.id.tvChangePass);
        SpannableString content=new SpannableString(changePass.getText());
        content.setSpan(new UnderlineSpan(),0,content.length(),0);
        changePass.setText(content);
        changePass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog dlg=new AlertDialog.Builder(PersonalDetailsActivity.this).create();
                LayoutInflater inflater=getLayoutInflater();
                final View view=inflater.inflate(R.layout.dialog_pass, null);
                dlg.setView(view);
                dlg.setButton(AlertDialog.BUTTON_NEGATIVE, "No, thanks", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
                dlg.setButton(AlertDialog.BUTTON_POSITIVE, "Save changes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        EditText newPass=(EditText)view.findViewById(R.id.edit_new_pass);
                        EditText currentPass=(EditText)view.findViewById(R.id.edit_current_pass);
                        String pass=userDB.getUserDao().getPass(myUsername);
                        if(pass.equals(currentPass.getText().toString())) {
                            userDB.getUserDao().updatePass(myUsername, newPass.getText().toString());
                        }
                        else{
                            Toast.makeText(getApplicationContext(), "Incorrect password. Try again", Toast.LENGTH_LONG).show();
                        }
                        dialog.dismiss();
                    }
                });
                dlg.show();

            }
        });




    }
}
