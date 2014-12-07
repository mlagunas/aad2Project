package com.example.aad2project.object;

public class ExistingPlant {

	/**
	 * Item Id
	 */
	@com.google.gson.annotations.SerializedName("id")
	private String Id;

	@com.google.gson.annotations.SerializedName("plantId")
	private Integer plantId;
	
	@com.google.gson.annotations.SerializedName("description")
	private String description;
	
	@com.google.gson.annotations.SerializedName("weatherid")
	private Integer weatherId;
	
	@com.google.gson.annotations.SerializedName("name")
	private String name;
	
	@com.google.gson.annotations.SerializedName("timetogrow")
	private Integer timeToGrow;
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public int getPlantId(){
		return plantId;
	}
	
	public void setPlantId(int id){
		plantId = id;
	}
	
	public String getDescription() {
		return description;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}

	public int getWeatherId() {
		return weatherId;
	}

	public void setWeatherId(int weather) {
		this.weatherId = weather;
	}
	
	public int getTimeToGrow() {
		return timeToGrow;
	}

	public void setTimeToGrow(int timeToGrow) {
		this.timeToGrow = timeToGrow;
	}
	
}

