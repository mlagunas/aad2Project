package com.example.aad2project.model;

public class Plant {

	private int id;
	private String description,name;
	private Weather weather;
 
	public Plant(){} 
 
	
 
	public int getId() {
		return id;
	}
	
	public String getName(){
		return name;
	}
	
	public void setName(String name){
		this.name=name;
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