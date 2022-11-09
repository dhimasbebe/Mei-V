package com.example.mei_v.api.model.maintenance;

import com.google.gson.annotations.SerializedName;

public class HistoryMaintenance {

	@SerializedName("waktu")
	private String waktu;

	@SerializedName("catatan")
	private String catatan;

	@SerializedName("users")
	private String users;

	public String getWaktu(){
		return waktu;
	}

	public String getCatatan(){
		return catatan;
	}

	public String getUsers(){
		return users;
	}
}