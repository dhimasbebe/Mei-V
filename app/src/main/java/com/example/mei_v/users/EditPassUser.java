package com.example.mei_v.users;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.mei_v.HomeActivity;
import com.example.mei_v.R;
import com.example.mei_v.api.ApiClient;
import com.example.mei_v.api.model.InputData;
import com.example.mei_v.api.model.users.Users;
import com.example.mei_v.api.model.users.UsersResponses;

import java.util.HashMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EditPassUser extends AppCompatActivity {
    UserPreferences userPreferences;
    EditText etpasswordlama, etpasswordbaru, etkonfirmpass;
    Button updatepass;
    ImageView tombolkembali;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_pass_user);
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();

        }
        etpasswordlama = findViewById(R.id.etpasswordlama);
        etpasswordbaru = findViewById(R.id.etpasswordbaru);
        etkonfirmpass = findViewById(R.id.etkonfirmpass);
        updatepass = findViewById(R.id.tomboleditpass);
        tombolkembali = findViewById(R.id.tombolkembali);
        userPreferences = new UserPreferences(this);
        HashMap<String, String> user = userPreferences.getUserDetail();

        updatepass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (etpasswordlama.getText().toString().equals(user.get(userPreferences.PASSWORD)) && etpasswordbaru.getText().toString().equals(etkonfirmpass.getText().toString())) {
                    editpassword(user.get(userPreferences.USERNAME), etpasswordlama.getText().toString(), etkonfirmpass.getText().toString());
                } else if (!etpasswordlama.getText().toString().equals(user.get(userPreferences.PASSWORD))) {
                    showToast("Password Lama Salah");
                } else {
                    showToast("Password Baru Berbeda");
                }
            }
        });
        tombolkembali.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(EditPassUser.this, UserProfileActivity.class);
                startActivity(intent);
            }
        });

    }

    private void editpassword(String username, String old_password, String password) {
        Call<InputData> client = ApiClient.getApiService().updatepassword(username, old_password, password);
        client.enqueue(new Callback<InputData>() {
            @Override
            public void onResponse(Call<InputData> call, Response<InputData> response) {
                if (response.isSuccessful()) {
                    if (response.body().getMessage().equals("success")) {
                        HashMap<String, String> user = userPreferences.getUserDetail();
                        userPreferences.createSession(user.get(username), user.get(userPreferences.FULLNAME), user.get(userPreferences.ROLE), user.get(userPreferences.EMAIL), password);
                        showToast("Password berhasil diganti");
                        startActivity(new Intent(EditPassUser.this, UserProfileActivity.class));
                        finish();
                    } else {
                        showToast(" Ganti password gagal ");
                    }
                }
            }

            @Override
            public void onFailure(Call<InputData> call, Throwable t) {
                showToast(" Jaringan Internet Bermasalah");
            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(EditPassUser.this, UserProfileActivity.class);
        startActivity(intent);
    }

    private void showToast(String text) {
        Toast.makeText(this, text, Toast.LENGTH_SHORT).show();

    }
}