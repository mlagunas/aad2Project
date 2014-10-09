package com.example.aad2project.model;

public class Weather {

	private int maxHumidity;
	private int minHumidity;
	private int maxTemperature;
	private int minTemperature;
	private int minLightness;
	private int maxLightnesss;
	private Weather next;
	
	private int id;

public Weather(){}
 
	
	public int getId() {
		return id;
	}
 
	public void setId(int id) {
		this.id = id;
	}
	
	
	public int getMaxHumi() {
		return maxHumidity;
	}


	public void setMaxHumi(int maxHumidity) {
		this.maxHumidity = maxHumidity;
	}


	public int getMinHumi() {
		return minHumidity;
	}


	public void setMinHumi(int minHumidity) {
		this.minHumidity = minHumidity;
	}


	public int getMaxTemp() {
		return maxTemperature;
	}


	public void setMaxTemp(int maxTemperature) {
		this.maxTemperature = maxTemperature;
	}


	public int getMinTemp() {
		return minTemperature;
	}


	public void setMinTemp(int minTemperature) {
		this.minTemperature = minTemperature;
	}


	public int getMinLightness() {
		return minLightness;
	}


	public void setMinLightness(int minLightness) {
		this.minLightness = minLightness;
	}


	public int getMaxLightnesss() {
		return maxLightnesss;
	}


	public void setMaxLightnesss(int maxLightnesss) {
		this.maxLightnesss = maxLightnesss;
	}


	public void addweaterToQueue(Weather newWeather) {
		if (this.next == null) {
			this.next = newWeather;
		} else {
			this.next.addweaterToQueue(newWeather);
		}
	}
	
	public Weather getWeatherNumber(int i) {
		Weather weater = this;
		
		for (int j = 0; j < i; j++) {
			weater = weater.next;
		}
		
		return weater;
	}
	
	public int size() {
		int j = 1;
		Weather weater = this;
		do {
			j++;
		} while(weater.next != null);
		return j;
	}	
 
	

 
	 

	
	
	
}
