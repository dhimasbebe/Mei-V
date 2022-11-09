package com.example.mei_v.api.model.lokasi;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class LokasiResponse{

	@SerializedName("data")
	private List<Lokasi> data;

	@SerializedName("message")
	private String message;

	@SerializedName("status")
	private String status;

	public void setData(List<Lokasi> data){
		this.data = data;
	}

	public List<Lokasi> getData(){
		return data;
	}

	public void setMessage(String message){
		this.message = message;
	}

	public String getMessage(){
		return message;
	}

	public void setStatus(String status){
		this.status = status;
	}

	public String getStatus(){
		return status;
	}
}