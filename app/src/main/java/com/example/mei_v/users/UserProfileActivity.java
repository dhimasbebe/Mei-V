package com.example.mei_v.users;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.mei_v.HomeActivity;
import com.example.mei_v.R;

import java.util.HashMap;

public class UserProfileActivity extends AppCompatActivity {
UserPreferences userPreferences;
Button editpass,logout;
ImageView tombolkembali;
TextView role,namalengkap,nip,email;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        if (getSupportActionBar()!= null){
            getSupportActionBar().hide();
        }
        userPreferences = new UserPreferences(this);
        HashMap<String,String> user = userPreferences.getUserDetail();
        tombolkembali = findViewById(R.id.tombolkembali);
        role = findViewById(R.id.role);
        namalengkap = findViewById(R.id.namalengkap);
        nip = findViewById(R.id.nip);
        email = findViewById(R.id.emailprofile);
        logout = findViewById(R.id.tombollogout);
        editpass = findViewById(R.id.tomboleditpass);
        role.setText(user.get(userPreferences.ROLE));
        namalengkap.setText(user.get(userPreferences.FULLNAME));
        nip.setText(user.get(userPreferences.USERNAME));
        email.setText(user.get(userPreferences.EMAIL));


        tombolkembali.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                userPreferences.logout();
                Intent intent = new Intent(UserProfileActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });
        editpass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(UserProfileActivity.this, EditPassUser.class);
                startActivity(intent);
            }
        });
    }
}