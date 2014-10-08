package com.example.aad2project.model;

public class Plant {

	private int id;
	private String description;
	private Weather weather;
 
	public Plant(){} 
 
	
 
	public int getId() {
		return id;
	}
 
	public void setId(int id) {
		this.id = id;
	}
 
	public String getDescription() {
		return description;
	}
 
	public void setDescription(String description) {
		this.description = description;
	}
	
	public Weather getWeatherId()
	{
		return weather;
	}
	
	public void setWeatherId(Weather weather)
	{
		this.weather = weather;
	}
 
	}
