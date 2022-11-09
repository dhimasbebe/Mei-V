package com.example.mei_v.api.model.alat;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class AlatResponse{

	@SerializedName("data")
	private List<Alat> data;

	@SerializedName("message")
	private String message;

	@SerializedName("status")
	private String status;

	public void setData(List<Alat> data){
		this.data = data;
	}

	public List<Alat> getData(){
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