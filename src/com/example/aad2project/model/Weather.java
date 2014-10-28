package com.example.aad2project.model;

public class Weather {

	private Long minHumidity;
	private double maxTemperature;
	private double minTemperature;
	private double minLightness;
	private double maxLightnesss;
	private Weather next;
	
	private int id;

public Weather(){}
 
	
	public int getId() {
		return id;
	}
 
	public void setId(int id) {
		this.id = id;
	}
	

	public Long getMinHumi() {
		return minHumidity;
	}


	public void setMinHumi(Long minHumidity) {
		this.minHumidity = minHumidity;
	}


	public double getMaxTemp() {
		return maxTemperature;
	}


	public void setMaxTemp(double maxTemperature) {
		this.maxTemperature = maxTemperature;
	}


	public double getMinTemp() {
		return minTemperature;
	}


	public void setMinTemp(double minTemperature) {
		this.minTemperature = minTemperature;
	}


	public double getMinLightness() {
		return minLightness;
	}


	public void setMinLightness(double minLightness) {
		this.minLightness = minLightness;
	}


	public double getMaxLightnesss() {
		return maxLightnesss;
	}


	public void setMaxLightness(double maxLightnesss) {
		this.maxLightnesss = maxLightnesss;
	}


	public void addWeaterToQueue(Weather newWeather) {
		if (this.next == null) {
			this.next = newWeather;
		} else {
			this.next.addWeaterToQueue(newWeather);
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
		int j = 0;
		Weather weater = this;
		do {
			j++;
		} while(weater.next != null);
		return j;
	}	
 
	

 
	 

	
	
	
}
