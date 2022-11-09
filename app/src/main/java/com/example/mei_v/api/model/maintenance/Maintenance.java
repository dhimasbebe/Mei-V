package com.example.mei_v.api.model.maintenance;

import com.google.gson.annotations.SerializedName;

public class Maintenance {

	@SerializedName("nama_lokasi")
	private String namaLokasi;

	@SerializedName("merk_alat")
	private String merkAlat;

	@SerializedName("detail_maintenance")
	private String detailMaintenance;

	@SerializedName("nama")
	private String nama;

	@SerializedName("lantai")
	private String lantai;

	@SerializedName("nama_spesifikasi")
	private String namaSpesifikasi;

	@SerializedName("id")
	private String id;

	@SerializedName("waktu_maintenance")
	private String waktuMaintenance;

	@SerializedName("nama_users")
	private String namaUsers;

	@SerializedName("barcode")
	private String barcode;

	@SerializedName("status")
	private String status;

	public String getNamaLokasi(){
		return namaLokasi;
	}

	public String getMerkAlat(){
		return merkAlat;
	}

	public String getDetailMaintenance(){
		return detailMaintenance;
	}

	public String getNama(){
		return nama;
	}

	public String getLantai(){
		return lantai;
	}

	public String getNamaSpesifikasi(){
		return namaSpesifikasi;
	}

	public String getId(){
		return id;
	}

	public String getWaktuMaintenance(){
		return waktuMaintenance;
	}

	public String getNamaUsers(){
		return namaUsers;
	}

	public String getBarcode(){
		return barcode;
	}

	public String getStatus(){
		return status;
	}
}