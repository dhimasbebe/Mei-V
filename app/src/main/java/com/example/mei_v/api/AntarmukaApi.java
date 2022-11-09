package com.example.mei_v.api;

import com.example.mei_v.api.model.alat.AlatResponse;
import com.example.mei_v.api.model.alat.DetailAlatResponse;
import com.example.mei_v.api.model.maintenance.HistoryMaintenanceResponse;
import com.example.mei_v.api.model.InputData;
import com.example.mei_v.api.model.lokasi.LokasiResponse;
import com.example.mei_v.api.model.users.UsersResponses;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface AntarmukaApi {
    @FormUrlEncoded
    @POST("login.php")
    Call<UsersResponses> getUsers(
            @Field("role") String role,
            @Field("username") String username,
            @Field("password") String password
    );

    @GET("history_maintenance.php")
    Call<HistoryMaintenanceResponse> getHistoryMaintenance(
            @Query("barcode") String barcode
    );

    @GET("detail_alat.php")
    Call<DetailAlatResponse> getDetailAlat(
            @Query("nama") String nama
    );


    @GET("cari_id_alat.php")
    Call<AlatResponse> getAlat(
            @Query("barcode") String barcode
    );

    @GET("get_lokasi.php")
    Call<LokasiResponse> getLokasi();


    @GET("input_data_ac.php")
    Call<InputData> inputDataAC(
                @Query("waktu_maintenance") String waktu_maintenance,
                @Query("barcode") String barcode,
                @Query("username") String username,
                @Query("id_lokasi") String lokasi,
                @Query("catatan") String catatan,
                @Query("suhu_ac") String suhu_ac,
                @Query("tekanan_ac") String tekanan_ac,
                @Query("ampere_ac") String ampere,
                @Query("kondisi_ac") String kondisi_ac
    );

    @GET("input_data_petir.php")
    Call<InputData> inputDataPetir(
            @Query("waktu_maintenance") String waktu_maintenance,
            @Query("barcode") String barcode,
            @Query("username") String username,
            @Query("id_lokasi") String lokasi,
            @Query("catatan") String catatan,
            @Query("kondisi_Baut") String kondisi_baut,
            @Query("grouding_test") String grounding_test

    );

    @GET("input_data_panel.php")
    Call<InputData> inputDataPanel(
            @Query("waktu_maintenance") String waktu_maintenance,
            @Query("barcode") String barcode,
            @Query("username") String username,
            @Query("id_lokasi") String lokasi,
            @Query("catatan") String catatan,
            @Query("voltage_rs") String voltage_rs,
            @Query("voltage_rt") String voltage_rt,
            @Query("voltage_st") String voltage_st,
            @Query("ampere_rs") String ampere_rs,
            @Query("ampere_rt") String ampere_rt,
            @Query("ampere_st") String ampere_st,
            @Query("kondisi_baut") String kondisi_baut

    );

    @GET("input_data_apar.php")
    Call<InputData> inputDataAPAR(
            @Query("waktu_maintenance") String waktu_maintenance,
            @Query("barcode") String barcode,
            @Query("username") String username,
            @Query("id_lokasi") String lokasi,
            @Query("catatan") String catatan,
            @Query("tekanan") String tekanan,
            @Query("selang") String selang,
            @Query("nossel") String nossel

            );
    @GET("input_data_alarm.php")
    Call<InputData> inputDataAlarm(
            @Query("waktu_maintenance") String waktu_maintenance,
            @Query("barcode") String barcode,
            @Query("username") String username,
            @Query("id_lokasi") String lokasi,
            @Query("catatan") String catatan,
            @Query("smoke_test") String smoke_test,
            @Query("heat_test") String heat_test,
            @Query("cek_batre") String cek_batre

    );

    @GET("input_data_hydrant.php")
    Call<InputData> inputDataHydrant(
            @Query("waktu_maintenance") String waktu_maintenance,
            @Query("barcode") String barcode,
            @Query("username") String username,
            @Query("id_lokasi") String lokasi,
            @Query("catatan") String catatan,
            @Query("tekanan") String tekanan,
            @Query("fungsi") String fungsi

    );

    @GET("download_report.php")
    Call<InputData> downloadreport(
            @Query("periodeawal") String periodeawal,
            @Query("periodeakhir") String periodeakhir
    );

    @GET("formregistrasi.php")
    Call<InputData> registrasiuser(
            @Query("nama") String nama,
            @Query("email") String email,
            @Query("role") String role,
            @Query("username") String username,
            @Query("password") String password
    );
    @GET("updatepassword.php")
    Call<InputData> updatepassword(
            @Query("username") String username,
            @Query("old_password") String old_password,
            @Query("password") String password
    );
}
