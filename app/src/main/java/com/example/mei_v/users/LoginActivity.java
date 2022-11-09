package com.example.mei_v.users;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.mei_v.HomeActivity;
import com.example.mei_v.R;
import com.example.mei_v.api.ApiClient;
import com.example.mei_v.api.model.users.Users;
import com.example.mei_v.api.model.users.UsersResponses;

import java.util.HashMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {
    UserPreferences userPreferences;
    EditText etusername, etpassword;
    Spinner spinnerrole;
    String role;
    Button loginbutton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }
        userPreferences = new UserPreferences(this);
        if (userPreferences.checkLogin()) {
            HashMap<String, String> user = userPreferences.getUserDetail();
            if (user.get(userPreferences.ROLE).equals("Admin")) {
                startActivity(new Intent(LoginActivity.this, RegistrasiActivity.class));
                finish();
            } else {
                startActivity(new Intent(LoginActivity.this, HomeActivity.class));
                finish();
            }

        }
        spinnerrole = findViewById(R.id.spinnerrolelogin);
        etusername = findViewById(R.id.etUsername);
        etpassword = findViewById(R.id.etPassword);
        loginbutton = findViewById(R.id.loginbutton);

        spinnerrole.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                role = parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        loginbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String role = spinnerrole.getSelectedItem().toString();
                String username_user = etusername.getText().toString().trim();
                String password_user = etpassword.getText().toString().trim();
                if (!username_user.isEmpty() && !password_user.isEmpty()) {
                    login(role, username_user, password_user);
                }
            }
        });
    }

    private void login(String role, String username_user, String password_user) {
        Call<UsersResponses> client = ApiClient.getApiService().getUsers(role, username_user, password_user);
        client.enqueue(new Callback<UsersResponses>() {
            @Override
            public void onResponse(Call<UsersResponses> call, Response<UsersResponses> response) {
                if (response.isSuccessful()) {
                    if (response.body().getData() != null) {
                        Users users = response.body().getData().get(0);
                        userPreferences.createSession(users.getmUsername(), users.getNamaUser(), users.getRoleUser(), users.getEmailUser(), users.getmPassword());
                        if (users.getRoleUser().equals("Admin")) {
                            startActivity(new Intent(LoginActivity.this, RegistrasiActivity.class));
                            finish();
                        } else {
                            startActivity(new Intent(LoginActivity.this, HomeActivity.class));
                            finish();
                        }
                    } else {
                        toast(" Username dan Password Salah ");
                    }
                }
            }

            @Override
            public void onFailure(Call<UsersResponses> call, Throwable t) {
                toast(" Jaringan Internet Bermasalah");
            }
        });
    }

    private void toast(String text) {
        Toast.makeText(this, text, Toast.LENGTH_SHORT).show();
    }
}