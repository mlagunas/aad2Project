package com.example.aad2project.object;

public class Weather {

	private double maxHumidity;
	private double minHumidity;
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
	
	
	public double getMaxHumi() {
		return maxHumidity;
	}


	public void setMaxHumi(int maxHumidity) {
		this.maxHumidity = maxHumidity;
	}


	public double getMinHumi() {
		return minHumidity;
	}


	public void setMinHumi(int minHumidity) {
		this.minHumidity = minHumidity;
	}


	public double getMaxTemp() {
		return maxTemperature;
	}


	public void setMaxTemp(int maxTemperature) {
		this.maxTemperature = maxTemperature;
	}


	public double getMinTemp() {
		return minTemperature;
	}


	public void setMinTemp(int minTemperature) {
		this.minTemperature = minTemperature;
	}


	public double getMinLightness() {
		return minLightness;
	}


	public void setMinLightness(int minLightness) {
		this.minLightness = minLightness;
	}


	public double getMaxLightnesss() {
		return maxLightnesss;
	}


	public void setMaxLightness(int maxLightnesss) {
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
