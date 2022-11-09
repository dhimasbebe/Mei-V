package com.example.mei_v.api.model.alat;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class DetailAlatResponse{

	@SerializedName("data")
	private List<DetailAlat> data;

	@SerializedName("message")
	private String message;

	@SerializedName("status")
	private String status;

	public List<DetailAlat> getData(){
		return data;
	}

	public String getMessage(){
		return message;
	}

	public String getStatus(){
		return status;
	}
}