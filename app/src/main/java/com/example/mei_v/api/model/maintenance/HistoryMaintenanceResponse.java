package com.example.mei_v.api.model.maintenance;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class HistoryMaintenanceResponse{

	@SerializedName("data")
	private List<HistoryMaintenance> data;

	@SerializedName("message")
	private String message;

	@SerializedName("status")
	private String status;

	public List<HistoryMaintenance> getData(){
		return data;
	}

	public String getMessage(){
		return message;
	}

	public String getStatus(){
		return status;
	}
}