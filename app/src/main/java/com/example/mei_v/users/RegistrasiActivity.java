package com.example.mei_v.users;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.mei_v.R;
import com.example.mei_v.api.ApiClient;
import com.example.mei_v.api.model.InputData;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegistrasiActivity extends AppCompatActivity {
    EditText etusername, etpassword, etemail, etnama;
    Spinner spinnerrole;
    String role;
    Button tombolregistrasi;
    ImageView imageuser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrasi);
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }

        etusername = findViewById(R.id.etregisusername);
        etpassword = findViewById(R.id.etregispassword);
        etemail = findViewById(R.id.etregisemail);
        etnama = findViewById(R.id.etregisnama);
        spinnerrole = findViewById(R.id.spinnerroleregis);
        imageuser = findViewById(R.id.image_user);
        tombolregistrasi = findViewById(R.id.tomboldaftar);

        spinnerrole.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                role = parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        tombolregistrasi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username_user = etusername.getText().toString().trim();
                String email_user = etemail.getText().toString().trim();
                String password_user = etpassword.getText().toString().trim();
                String nama_user = etnama.getText().toString().trim();
                if (!username_user.isEmpty() && !password_user.isEmpty()) {
                    registrasi(nama_user, email_user, role, username_user, password_user);
                }
            }
        });
        imageuser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(RegistrasiActivity.this, UserProfileActivity.class);
                startActivity(intent);
            }
        });

    }
    private void registrasi(String nama, String email, String role, String username, String password) {
        Call<InputData> client = ApiClient.getApiService().registrasiuser(nama, email, role, username, password);
        client.enqueue(new Callback<InputData>() {
            @Override
            public void onResponse(Call<InputData> call, Response<InputData> response) {
                if (response.isSuccessful()) {
                    if (response.body().getMessage().equals("success")) {
                        toast(" Pendaftaran Berhasil ");
                        Restart();
                    }
                } else {
                    toast(" Pendaftaran Gagal ");
                }
            }

            @Override
            public void onFailure(Call<InputData> call, Throwable t) {
                toast(" Koneksi Internet Bermasalah");
            }
        });
    }
    private void Restart()
    {
        etnama.setText("");
        etemail.setText("");
        etusername.setText("");
        etpassword.setText("");

        this.recreate();
    }
    private void toast(String s) {
        Toast.makeText(this, s, Toast.LENGTH_SHORT).show();
    }
}