package com.example.proiectandroid;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;


import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.ui.database.FirebaseListAdapter;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.firebase.ui.FirebaseUI.*;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class GroupChatActivity extends AppCompatActivity {
    private DatabaseReference ref;
    private FirebaseDatabase db;
    private MessageListAdapter adapter;
    private static boolean calledAlready = false;
    List<MessageClass> messList;
    ListView lv;
    private FirebaseListAdapter<MessageClass> fbAdapter;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);
        Intent i = getIntent();
        final String myUsername = i.getExtras().getString("myUsername");

        //referinta la bd
        ref=FirebaseDatabase.getInstance().getReference("chat-app");
        ref.keepSynced(true);
        //buton trimitere mesaje
        Button send = (Button) findViewById(R.id.button_send);
        //edit text pt scriere mesaj
        final EditText mess = (EditText) findViewById(R.id.edittext_writemessage);
        lv = (ListView) findViewById(R.id.lvMesaje);
        messList=new ArrayList<MessageClass>();
        ValueEventListener messageListener = new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        if (dataSnapshot.exists()) {
                            messList.clear();
                            //Toast.makeText(getApplicationContext(), String.valueOf(dataSnapshot.getChildrenCount()), Toast.LENGTH_LONG).show();
                            for (DataSnapshot dn : dataSnapshot.getChildren()) {
                                MessageClass mess = dn.getValue(MessageClass.class);
                                messList.add(mess);
                            }
                        }
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                };

                ref.child("chat-app").addListenerForSingleValueEvent(messageListener);
                adapter = new MessageListAdapter(getApplicationContext(), messList);
                lv.setAdapter(adapter);

                  send.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        final MessageClass myMessage = new MessageClass();
                                        myMessage.setSender(myUsername);
                                        myMessage.setMessage(mess.getText().toString());
                                        myMessage.setTime(java.text.DateFormat.getDateTimeInstance().format(Calendar.getInstance().getTime()));
                                        //final DatabaseReference myRef = db.getReference("chat-app");
                                        //myRef.keepSynced(true);
                                        ref.child("chat-app").addListenerForSingleValueEvent(new ValueEventListener() {
                                            @Override
                                            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                                //se adauga mesaj nou in BD
                                                myMessage.setUid(ref.child("chat-app").push().getKey());
                                                ref.child("chat-app").child(myMessage.getUid()).setValue(myMessage);
                                                messList.add(myMessage);
                                                adapter.notifyDataSetChanged();
                                                mess.setText("");
                                            }

                                            @Override
                                            public void onCancelled(@NonNull DatabaseError databaseError) {

                                            }
                                        });
                                    }
                                });


    }

    }

