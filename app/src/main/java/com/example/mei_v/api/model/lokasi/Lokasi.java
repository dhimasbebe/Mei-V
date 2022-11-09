package com.example.mei_v.api.model.lokasi;

import com.google.gson.annotations.SerializedName;

public class Lokasi {

	@SerializedName("nama_lokasi")
	private String namaLokasi;

	@SerializedName("id_lokasi")
	private String idLokasi;

	@SerializedName("lantai")
	private String lantai;

	public void setNamaLokasi(String namaLokasi){
		this.namaLokasi = namaLokasi;
	}

	public String getNamaLokasi(){
		return namaLokasi;
	}

	public void setIdLokasi(String idLokasi){
		this.idLokasi = idLokasi;
	}

	public String getIdLokasi(){
		return idLokasi;
	}

	public void setLantai(String lantai){
		this.lantai = lantai;
	}

	public String getLantai(){
		return lantai;
	}
}