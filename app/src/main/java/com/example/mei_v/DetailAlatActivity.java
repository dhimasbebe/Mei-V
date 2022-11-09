package com.example.mei_v;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.mei_v.api.ApiClient;
import com.example.mei_v.api.model.alat.DetailAlat;
import com.example.mei_v.api.model.alat.DetailAlatResponse;
import com.example.mei_v.api.model.maintenance.HistoryMaintenance;
import com.example.mei_v.api.model.maintenance.HistoryMaintenanceResponse;
import com.example.mei_v.api.model.maintenance.Maintenance;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetailAlatActivity extends AppCompatActivity {
RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_alat);
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        if (getSupportActionBar()!= null){
            getSupportActionBar().hide();
        }
        recyclerView = findViewById(R.id.recycletabel);
        Bundle extras = getIntent().getExtras();
        ImageView imagealat = (ImageView) findViewById(R.id.image_alat);
        TextView judul = (TextView) findViewById(R.id.judul);
        TextView deskripsialat = (TextView) findViewById(R.id.deskripsi_alat);
        if(extras!=null){
            if (extras.getString("KATEGORI").equals("hydrant")){
                judul.setText("Hydrant");
                FilterMaintenance("HYDRANT");
                imagealat.setImageResource(R.drawable.hydrant);
                deskripsialat.setText(R.string.hydrant_description);
            }
            if (extras.getString("KATEGORI").equals("ac")){
                FilterMaintenance("AC");
                judul.setText("AC");
                imagealat.setImageResource(R.drawable.ac);
                deskripsialat.setText(R.string.ac_description);
            }
            if (extras.getString("KATEGORI").equals("genset")){
                FilterMaintenance("GENSET");
                judul.setText("Genset");
                imagealat.setImageResource(R.drawable.genset);
                deskripsialat.setText(R.string.genset_description);
            }
            if (extras.getString("KATEGORI").equals("apar")){
                FilterMaintenance("APAR");
                judul.setText("APAR");
                imagealat.setImageResource(R.drawable.apar);
                deskripsialat.setText(R.string.apar_description);
            }
            if (extras.getString("KATEGORI").equals("panellistrik")){
                FilterMaintenance("PANEL LISTRIK");
                judul.setText("Panel Listrik");
                imagealat.setImageResource(R.drawable.panel_listrik);
                deskripsialat.setText(R.string.panellistrik_description);
            }
            if (extras.getString("KATEGORI").equals("firealarm")){
                FilterMaintenance("ALARM");
                judul.setText("Fire Alarm");
                imagealat.setImageResource(R.drawable.fire_alarm);
                deskripsialat.setText(R.string.firealarm_description);
            }
            if (extras.getString("KATEGORI").equals("penangkalpetir")) {
                FilterMaintenance("PENANGKAL PETIR");
                judul.setText("Penangkal Petir");
                imagealat.setImageResource(R.drawable.penangkal_petir);
                deskripsialat.setText(R.string.penangkalpetir_description);
            }
        }
    }
    private void FilterMaintenance(String nama) {
        Call<DetailAlatResponse> client = ApiClient.getApiService().getDetailAlat(nama);
        client.enqueue(new Callback<DetailAlatResponse>() {
            @Override
            public void onResponse(Call<DetailAlatResponse> call, Response<DetailAlatResponse> response) {
                if(response.isSuccessful()){
                    if (response.body().getData() != null){
                        showRecycleview(response.body().getData());
                    }
                }
            }
            @Override
            public void onFailure(Call<DetailAlatResponse> call, Throwable t) {
                Log.e("LOGIN", t.getMessage());

            }
        });
    }

    private void showRecycleview(List<DetailAlat> data) {
        ListAlatAdapter listAlatAdapter=new ListAlatAdapter();
        listAlatAdapter.setListMaintenance(data);
        listAlatAdapter.notifyDataSetChanged();
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(listAlatAdapter);

    }
}