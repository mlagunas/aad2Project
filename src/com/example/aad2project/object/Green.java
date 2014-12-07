package com.example.aad2project.object;

import java.util.Date;

public class Green {
	private int id;
	
	private int number;
	
	private Date date;
	
	private int eId;
	
	private String description;
	
	private Integer weatherId;
	
	private String name;
	
	private Integer timeToGrow;
	
	private String code;
	
	public Green(){}
	
	
	public void setExistingId(int eId){
		this.eId=eId;
	}
	
	public int getExistingId(){
		return eId;
	}
	
	public void setCode(String code){
		this.code = code;
	}
	
	public String getCode(){
		return code;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	public int getId() {
		return id;
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
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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
