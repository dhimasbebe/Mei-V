package com.example.mei_v;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class DownloadReportActivity extends AppCompatActivity {
    Button tomboldownload;
    EditText periodeawal, periodeakhir;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_waktu_report);
        periodeawal = findViewById(R.id.etperiodeawal);
        periodeakhir = findViewById(R.id.etperiodeakhir);
        tomboldownload = findViewById(R.id.tomboldownload);

        periodeawal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setCalendar("periode awal");
            }
        });
        periodeakhir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setCalendar("periode akhir");
            }
        });


        tomboldownload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                downloadreport(periodeawal.getText().toString(), periodeakhir.getText().toString());

            }
        });
    }

    private void setCalendar(String edittext) {
        Calendar calendar = Calendar.getInstance();
        DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int dayOfMonth) {
                calendar.set(Calendar.YEAR, year);
                calendar.set(Calendar.MONTH, month);
                calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);

                updateCalendar();
            }

            private void updateCalendar() {
                String Format = "YYYY-MM-dd";
                SimpleDateFormat sdf = new SimpleDateFormat(Format, Locale.US);
                if (edittext.equals("periode awal")) {
                    periodeawal.setText(sdf.format(calendar.getTime()));
                } else {
                    periodeakhir.setText(sdf.format(calendar.getTime()));
                }

            }
        };
        new DatePickerDialog(DownloadReportActivity.this, date, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH)).show();
    }

    private void downloadreport(String start_date, String end_date) {
        Log.e("start_date", start_date);
        Log.e("end_date", end_date);
        String url = "https://meivtelpro.my.id/download_report.php?periodeawal=" + start_date + "&periodeakhir=" + end_date;
        Intent i = new Intent(Intent.ACTION_VIEW);
        i.setData(Uri.parse(url));
        startActivity(i);
    }

    private void toast(String s) {
    }
}