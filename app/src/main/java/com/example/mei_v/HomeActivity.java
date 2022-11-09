package com.example.mei_v;

import androidx.activity.result.ActivityResultLauncher;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.mei_v.api.model.alat.AlatResponse;
import com.example.mei_v.api.ApiClient;
import com.example.mei_v.users.UserPreferences;
import com.example.mei_v.users.UserProfileActivity;
import com.journeyapps.barcodescanner.ScanContract;
import com.journeyapps.barcodescanner.ScanOptions;

import java.util.HashMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeActivity extends AppCompatActivity {
    UserPreferences userPreferences;
    ImageView ac,apar,panellistrik,firealarm,penangkalpetir,hydrant;
    ImageView userprofile;
    Button tombolreport, scan_supervisor,scan_teknisi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        if (getSupportActionBar()!= null){
            getSupportActionBar().hide();

        }
        userprofile = findViewById(R.id.image_user);
        hydrant = findViewById(R.id.image_hydrant);
        ac = findViewById(R.id.image_ac);
        apar = findViewById(R.id.image_apar);
        panellistrik = findViewById(R.id.image_panellistrik);
        firealarm = findViewById(R.id.image_firealarm);
        penangkalpetir = findViewById(R.id.image_penangkalpetir);
        scan_supervisor = findViewById(R.id.scan_supervisor);
        scan_teknisi = findViewById(R.id.scan_teknisi);
        tombolreport = findViewById(R.id.tombolreport);


        userPreferences = new UserPreferences(this);
        HashMap<String,String> user = userPreferences.getUserDetail();
        if (user.get(userPreferences.ROLE).equals("Teknisi")){
            tombolreport.setVisibility(View.GONE);
            scan_supervisor.setVisibility(View.GONE);
            scan_teknisi.setVisibility(View.VISIBLE);

        }


        scan_supervisor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                scanbarcode();

            }
        });
        tombolreport.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HomeActivity.this, DownloadReportActivity.class);
                startActivity(intent);
            }
        });

        scan_teknisi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                scanbarcode();

            }
        });

        userprofile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HomeActivity.this, UserProfileActivity.class);
                startActivity(intent);
            }
        });
        hydrant.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HomeActivity.this, DetailAlatActivity.class);
                intent.putExtra("KATEGORI","hydrant");
                startActivity(intent);
            }
        });
        ac.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HomeActivity.this, DetailAlatActivity.class);
                intent.putExtra("KATEGORI","ac");
                startActivity(intent);
            }
        });

        apar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HomeActivity.this, DetailAlatActivity.class);
                intent.putExtra("KATEGORI","apar");
                startActivity(intent);
            }
        });
        panellistrik.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HomeActivity.this, DetailAlatActivity.class);
                intent.putExtra("KATEGORI","panellistrik");
                startActivity(intent);
            }
        });
        firealarm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HomeActivity.this, DetailAlatActivity.class);
                intent.putExtra("KATEGORI","firealarm");
                startActivity(intent);
            }
        });
        penangkalpetir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HomeActivity.this, DetailAlatActivity.class);
                intent.putExtra("KATEGORI","penangkalpetir");
                startActivity(intent);
            }
        });
    }

    private void scanbarcode() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CAMERA}, PackageManager.PERMISSION_GRANTED);
        }
        else {
            ScanOptions options = new ScanOptions();
            options.setOrientationLocked(true);
            options.setCaptureActivity(CaptureAct.class);
            scanBarcode.launch(options);
        }
    }
    ActivityResultLauncher<ScanOptions> scanBarcode = registerForActivityResult(new ScanContract(), result -> {
        if (result.getContents() != null) {
    cariBarcodeMaintenance(result.getContents());
        }
    });

    private void cariBarcodeMaintenance(String barcode) {
        Call<AlatResponse> client = ApiClient.getApiService().getAlat(barcode);
        client.enqueue(new Callback<AlatResponse>() {
            @Override
            public void onResponse(Call<AlatResponse> call, Response<AlatResponse> response) {
                if(response.isSuccessful()){
                    if (response.body().getData() != null){
                        switch (response.body().getData().get(0).getNama()) {
                            case "AC": {
                                toast(response.body().getData().get(0).getNama());
                                Intent intent = new Intent(HomeActivity.this, MaintenanceACActivity.class);
                                intent.putExtra("nama", response.body().getData().get(0).getNama());
                                intent.putExtra("barcode", barcode);
                                startActivity(intent);
                                break;
                            }
                            case "ALARM": {
                                toast(response.body().getData().get(0).getNama());
                                Intent intent = new Intent(HomeActivity.this, MaintenanceAlarmActivity.class);
                                intent.putExtra("nama", response.body().getData().get(0).getNama());
                                intent.putExtra("barcode", barcode);
                                startActivity(intent);
                                break;
                            }
                            case "APAR": {
                                toast(response.body().getData().get(0).getNama());
                                Intent intent = new Intent(HomeActivity.this, MaintenanceAPARActivity.class);
                                intent.putExtra("nama", response.body().getData().get(0).getNama());
                                intent.putExtra("barcode", barcode);
                                startActivity(intent);
                                break;
                            }

                            case "Hydrant": {
                                toast(response.body().getData().get(0).getNama());
                                Intent intent = new Intent(HomeActivity.this, MaintenanceHydrantActivity.class);
                                intent.putExtra("nama", response.body().getData().get(0).getNama());
                                intent.putExtra("barcode", barcode);
                                startActivity(intent);
                                break;
                            }
                            case "Panel Listrik": {
                                toast(response.body().getData().get(0).getNama());
                                Intent intent = new Intent(HomeActivity.this, MaintenancePanelListrikActivity.class);
                                intent.putExtra("nama", response.body().getData().get(0).getNama());
                                intent.putExtra("barcode", barcode);
                                startActivity(intent);
                                break;
                            }
                            case "Penangkal Petir": {
                                toast(response.body().getData().get(0).getNama());
                                Intent intent = new Intent(HomeActivity.this, MaintenancePenangkalPetirActivity.class);
                                intent.putExtra("nama", response.body().getData().get(0).getNama());
                                intent.putExtra("barcode", barcode);
                                startActivity(intent);
                                break;
                            }

                            default:
                                toast("Data tidak ditemukan");
                                break;
                        }
                    }
                }
            }
            @Override
            public void onFailure(Call<AlatResponse> call, Throwable t) {
                Log.e("LOGIN", t.getMessage());

            }
        });
    }

    private void toast(String text) {
        Toast.makeText(this,text, Toast.LENGTH_SHORT).show();
    }
}