package com.example.mei_v;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.example.mei_v.users.LoginActivity;
import com.example.mei_v.users.UserPreferences;

import java.util.HashMap;

public class SplashScreen extends AppCompatActivity {
    UserPreferences userPreferences;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        userPreferences = new UserPreferences(this);
        HashMap<String,String> userpref = userPreferences.getUserDetail();
        if (getSupportActionBar()!= null){
            getSupportActionBar().hide();
        }
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                    Intent intent = new Intent( SplashScreen.this, LoginActivity.class);
                    startActivity(intent);
                    finish();


            }
        }, 2000);
    }
}