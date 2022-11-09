package com.example.mei_v;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mei_v.api.ApiClient;
import com.example.mei_v.api.model.maintenance.HistoryMaintenance;
import com.example.mei_v.api.model.maintenance.HistoryMaintenanceResponse;
import com.example.mei_v.api.model.InputData;
import com.example.mei_v.api.model.lokasi.Lokasi;
import com.example.mei_v.api.model.lokasi.LokasiResponse;
import com.example.mei_v.users.UserPreferences;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MaintenanceAPARActivity extends AppCompatActivity {
    UserPreferences userPreferences;
    TextView tvjamdigital;
    ImageView tombolkembali;
    RecyclerView recyclerView;
    LinearLayout linear;
    Spinner spinnerruang, spinnernossel, spinnerselang, spinnertekanan;
    String barcode, nossel, tekanan, selang, ruang;
    HashMap<String, String> maplokasi = new HashMap<String, String>();
    ArrayList<String> listlokasi = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maintenance_apar);
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }

        Bundle extras = getIntent().getExtras();
        if (extras.getString("nama") != null && extras.getString("barcode") != null) {
            barcode = extras.getString("barcode");
            FilterMaintenance(barcode);
        }
        spinnernossel = findViewById(R.id.detailnosselapar);
        spinnerselang = findViewById(R.id.detailselangapar);
        spinnertekanan = findViewById(R.id.detailtekananapar);
        spinnerruang = findViewById(R.id.detailruangapar);
        tvjamdigital = findViewById(R.id.tanggalsekarangapar);
        recyclerView = findViewById(R.id.recycletabel);
        Button submitapar = findViewById(R.id.submitapar);
        tombolkembali = findViewById(R.id.backhomeapar);
        linear = findViewById(R.id.linear);
        blink();
        getLokasi();

        userPreferences = new UserPreferences(this);
        HashMap<String, String> user = userPreferences.getUserDetail();
        if (user.get(userPreferences.ROLE).equals("Supervisor")) {
            linear.setVisibility(View.GONE);
            recyclerView.getLayoutParams().height = 900;

        }

        spinnertekanan.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                tekanan = parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        spinnerselang.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                selang = parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        spinnernossel.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                nossel = parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        spinnerruang.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                ruang = parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        submitapar.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                String lokasi = maplokasi.get(spinnerruang.getSelectedItem().toString());//mengambil id lokasi dr nama lokasi
                String barcode_apar = barcode;
                String user = userPreferences.getUserDetail().get(UserPreferences.USERNAME);
                String catatan = ((EditText) findViewById(R.id.textboxapar)).getText().toString();
                String waktu = tvjamdigital.getText().toString();
                postData(waktu, barcode_apar, user, lokasi, catatan, tekanan, selang, nossel);
            }
        });
        tombolkembali.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
    }

    private void getLokasi() {
        Call<LokasiResponse> client = ApiClient.getApiService().getLokasi();
        client.enqueue(new Callback<LokasiResponse>() {
            @Override
            public void onResponse(Call<LokasiResponse> call, Response<LokasiResponse> response) {
                if (response.isSuccessful()) {
                    if (response.body().getData() != null) {
                        List<Lokasi> data = response.body().getData();

                        for (int i = 0; i < data.size(); i++) {
                            Lokasi lok = data.get(i);
                            listlokasi.add(lok.getNamaLokasi());
                            maplokasi.put(lok.getNamaLokasi(), lok.getIdLokasi());
                        }
                        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(MaintenanceAPARActivity.this, android.R.layout.simple_spinner_item, listlokasi);
                        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        spinnerruang.setAdapter(arrayAdapter);
                    }
                }
            }

            @Override
            public void onFailure(Call<LokasiResponse> call, Throwable t) {
                showToast("Koneksi Internet bermasalah");
                Log.e("LOGIN", t.getMessage());

            }
        });
    }

    private void postData(String waktu_maintenance, String barcode, String username, String lokasi, String catatan, String tekanan, String selang, String nossel) {
        Call<InputData> client = ApiClient.getApiService().inputDataAPAR(waktu_maintenance, barcode, username, lokasi, catatan, tekanan, selang, nossel);
        client.enqueue(new Callback<InputData>() {
            @Override
            public void onResponse(Call<InputData> call, Response<InputData> response) {
                if (response.isSuccessful()) {
                    if (response.body().getMessage().equals("success")) {
                        showToast("Input Data Sukses");
                        startActivity(getIntent());
                        finish();
                    }
                } else {
                    showToast("Data masih kosong !");
                }
            }

            @Override
            public void onFailure(Call<InputData> call, Throwable t) {
                showToast("Koneksi Internet bermasalah");
                Log.e("apar", t.getMessage());
            }
        });
    }

    private void blink() {
        final Handler hander = new Handler();
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(550);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                hander.post(new Runnable() {
                    @Override
                    public void run() {
                        Date today = new Date();
//                        SimpleDateFormat formatter = new SimpleDateFormat("EEE, d MMM yyyy HH:mm:ss");
                        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                        tvjamdigital.setText(formatter.format(today));
                        blink();
                    }
                });
            }
        }).start();
    }

    private void FilterMaintenance(String barcode) {
        Call<HistoryMaintenanceResponse> client = ApiClient.getApiService().getHistoryMaintenance(barcode);
        client.enqueue(new Callback<HistoryMaintenanceResponse>() {
            @Override
            public void onResponse(Call<HistoryMaintenanceResponse> call, Response<HistoryMaintenanceResponse> response) {
                if (response.isSuccessful()) {
                    if (response.body().getData() != null) {
                        showRecycleview(response.body().getData());
                    }
                }
            }

            @Override
            public void onFailure(Call<HistoryMaintenanceResponse> call, Throwable t) {
                showToast("Koneksi Internet bermasalah");
            }
        });
    }

    private void showRecycleview(List<HistoryMaintenance> data) {
        ListTableAdapter listTableAdapter = new ListTableAdapter();
        listTableAdapter.setListMaintenance(data);
        listTableAdapter.notifyDataSetChanged();
        recyclerView.setLayoutManager(new GridLayoutManager(this,2));
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(listTableAdapter);

    }

    private void showToast(String text) {
        Toast.makeText(this, text, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(MaintenanceAPARActivity.this, HomeActivity.class);
        startActivity(intent);
    }
}