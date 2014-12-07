package com.example.aad2project.object;

import java.util.Date;

public class Plant {
	
	@com.google.gson.annotations.SerializedName("id")
	private String id;
	
	@com.google.gson.annotations.SerializedName("number")
	private int number;
	
	@com.google.gson.annotations.SerializedName("time")
	private Date date;
	
	@com.google.gson.annotations.SerializedName("existingid")
	private int eId;
	
	@com.google.gson.annotations.SerializedName("plantid")
	private int plantId;
	
	public Plant() {}
	
	public void setExistingId(int eId){
		this.eId=eId;
	}
	
	public int getExisitingId(){
		return eId;
	}
	
	public void setId(String id) {
		this.id = id;
	}
	
	public String getId() {
		return id;
	}

	public int getPlantid(){
		return plantId;
	}
	
	public void setPlantid(int id){
		this.plantId=id;
	}
	
	public Date getDate(){
		return date;
	}
	
	public void setDate(Date date){
		this.date=date;
	}

	public void setNumber(int number){
		this.number=number;
	}
	
	public int getNumber(){
		return number;
	}
}