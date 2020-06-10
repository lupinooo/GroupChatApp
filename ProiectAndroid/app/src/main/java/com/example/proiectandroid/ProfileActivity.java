package com.example.proiectandroid;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.util.Random;

public class ProfileActivity extends AppCompatActivity {
    TextView tvuser;
    public static final String PREFERENCES_FILE_NAME="ImgPrefs";
    public static final String PREFERENCES2_FILE_NAME="MenuPrefs";
    private static final int IMAGE=100;
    ImageView profilePic;
    Uri imageUri;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        //setare imagine de profil random dintr-o serie de imagini
        profilePic=(ImageView)findViewById(R.id.profilePic); //image view-ul imaginii de profil
        Random r=new Random();
        Integer [] imgs={R.drawable.pic1, R.drawable.pic2, R.drawable.pic3, R.drawable.pic4, R.drawable.pic5, R.drawable.pic6};
        profilePic.setImageResource(imgs[r.nextInt(imgs.length)]);


        SharedPreferences imgFile=getSharedPreferences(PREFERENCES_FILE_NAME, 0);
        SharedPreferences.Editor editor=imgFile.edit();
        editor.putInt("1", R.drawable.pic1);
        editor.putInt("2", R.drawable.pic2);
        editor.putInt("3", R.drawable.pic3);
        editor.putInt("4", R.drawable.pic4);
        editor.putInt("5", R.drawable.pic5);
        editor.putInt("6", R.drawable.pic6);
        editor.apply();


        TextView tvPic=(TextView)findViewById(R.id.tvProfilePic);
        tvPic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent ii=new Intent(ProfileActivity.this, ProfilePic.class);
                startActivity(ii);
            }
        });



        //preluare user din WelcomeActivity
        Intent intent=getIntent();
        String username=intent.getExtras().getString("username");
        tvuser=(TextView)findViewById(R.id.tvUser);
        tvuser.setText(username); //TEXTVIEW CU USERNAME-UL USERULUI CURENT

        //un fel de meniu al paginii personale (al profilului)
        String [] options={"Personal Details", "Friends", "Add Friend", "Friends statistics"};
        Integer [] imgsOptions={R.drawable.personal_details, R.drawable.personal_contacts, R.drawable.add_friend, R.drawable.stats, R.drawable.findd};
        ListView listView;
        CustomAdapter whatever=new CustomAdapter(this, imgsOptions, options);
        listView=(ListView)findViewById(R.id.listViewOptions);
        listView.setAdapter(whatever);


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //activitatea de vizualizare a contactelor disponibile + adaugare prieten
                if(position==2){
                Intent intent2=new Intent(ProfileActivity.this, AddFriendActivity.class);
                intent2.putExtra("myUsername", tvuser.getText().toString());
                startActivity(intent2);}
                //activitate de vizualizare prieteni adaugati+stergere prieten
                if(position==1){
                    Intent intent3=new Intent(ProfileActivity.this, FriendsActivity.class);
                    intent3.putExtra("myUsername", tvuser.getText().toString());
                    startActivity(intent3);
                }
                //activitate de vizualizare a datelor personale+editare
                if(position==0){
                    Intent intent4=new Intent(ProfileActivity.this, PersonalDetailsActivity.class);
                    intent4.putExtra("user", tvuser.getText().toString());
                    startActivity(intent4);
                }
                if(position==3){
                    Intent intent5=new Intent(ProfileActivity.this, GraphActivity.class);
                    intent5.putExtra("myUsername", tvuser.getText().toString());
                    startActivity(intent5);
                }
            }
        });

        //butonul de CHAT
        Button chatButton=(Button)findViewById(R.id.buttonChat);
        chatButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(ProfileActivity.this, GroupChatActivity.class);
                intent.putExtra("myUsername", tvuser.getText().toString());
                startActivity(intent);
            }
        });

        //button LOG OUT
        Button logOut=(Button)findViewById(R.id.buttonLogOut);
        logOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(ProfileActivity.this, WelcomeActivity.class);
                startActivity(i);

            }
        });


        Button btnProfile=(Button)findViewById(R.id.buttonPic);
        btnProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent gallery=new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI);
                startActivityForResult(gallery, IMAGE);
            }
        });


        }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode==RESULT_OK && requestCode==IMAGE){
           imageUri=data.getData();
           profilePic.setImageURI(imageUri);
        }
    }
}
