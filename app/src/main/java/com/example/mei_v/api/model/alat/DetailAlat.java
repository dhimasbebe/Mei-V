package com.example.mei_v.api.model.alat;

import com.google.gson.annotations.SerializedName;

public class DetailAlat {

	@SerializedName("nama_lokasi")
	private String namaLokasi;

	@SerializedName("lantai")
	private String lantai;

	@SerializedName("barcode")
	private String barcode;

	@SerializedName("merk_alat")
	private String merkalat;

	public String getNamaLokasi(){
		return namaLokasi;
	}

	public String getLantai(){
		return lantai;
	}

	public String getBarcode(){
		return barcode;
	}

	public String getMerkalat() {
		return merkalat;
	}
}