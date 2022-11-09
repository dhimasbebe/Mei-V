package com.example.mei_v.api.model.alat;

import com.google.gson.annotations.SerializedName;

public class Alat {

	@SerializedName("merk_alat")
	private String merkAlat;

	@SerializedName("id_kategori")
	private String idKategori;

	@SerializedName("nama")
	private String nama;

	@SerializedName("barcode_alat")
	private String barcodeAlat;

	public void setMerkAlat(String merkAlat){
		this.merkAlat = merkAlat;
	}

	public String getMerkAlat(){
		return merkAlat;
	}

	public void setIdKategori(String idKategori){
		this.idKategori = idKategori;
	}

	public String getIdKategori(){
		return idKategori;
	}

	public void setNama(String nama){
		this.nama = nama;
	}

	public String getNama(){
		return nama;
	}

	public void setBarcodeAlat(String barcodeAlat){
		this.barcodeAlat = barcodeAlat;
	}

	public String getBarcodeAlat(){
		return barcodeAlat;
	}
}