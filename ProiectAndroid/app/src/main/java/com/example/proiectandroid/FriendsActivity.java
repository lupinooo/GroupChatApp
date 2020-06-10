package com.example.proiectandroid;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.io.FileOutputStream;
import java.util.List;

public class FriendsActivity extends AppCompatActivity {


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_friends);
        Intent intent=getIntent();
        final String myUsername=intent.getExtras().getString("myUsername");
        final String FILE_NAME=myUsername+": friend_list.txt";


        int i=0;
        final UsersDB usersDB=UsersDB.getInstance(this);
        final List<FriendClass> listaFriends=usersDB.getFriendsDAO().getFriends(myUsername);
        String [] friends=new String[listaFriends.size()];
        for (FriendClass friend:listaFriends) {
            friends[i]=friend.getUsername();
            i++;
        }
        final ArrayAdapter friendsAdapter=new ArrayAdapter(this, android.R.layout.simple_list_item_1, friends);
        final ListView listViewFriends=findViewById(R.id.listViewFriends);
        listViewFriends.setAdapter(friendsAdapter);


        listViewFriends.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {
                final AlertDialog alertDialog=new AlertDialog.Builder(FriendsActivity.this).create();
                alertDialog.setTitle("Are you sure you want to delete this friend?");
                alertDialog.setButton(AlertDialog.BUTTON_POSITIVE,"Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //stergere din baza de date
                        usersDB.getFriendsDAO().deleteFriend(listViewFriends.getItemAtPosition(position).toString());
                        dialog.dismiss();
                    }
                });
                alertDialog.setButton(AlertDialog.BUTTON_NEGATIVE,"No, cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
                alertDialog.show();
            }
        });



        Button saveFriends=(Button)findViewById(R.id.buttonSaveFriends);
        saveFriends.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String text="";
                for(int i=0;i<friendsAdapter.getCount();i++){
                    text+="Friend: "+friendsAdapter.getItem(i).toString()+"\n";
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

