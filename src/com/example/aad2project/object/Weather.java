package com.example.aad2project.object;

public class Weather {

	@com.google.gson.annotations.SerializedName("id")
	private String Id;
	
	@com.google.gson.annotations.SerializedName("weatherid")
	private int weatherid;
	
	@com.google.gson.annotations.SerializedName("humiditymax")
	private int humiditymax;
	
	@com.google.gson.annotations.SerializedName("humiditymin")
	private int humiditymin;
	
	@com.google.gson.annotations.SerializedName("temperaturemax")
	private double temperaturemax;
	
	@com.google.gson.annotations.SerializedName("temperaturemin")
	private double temperaturemin;
	
	@com.google.gson.annotations.SerializedName("lightnessmin")
	private double lightnessmin;
	
	@com.google.gson.annotations.SerializedName("lightnessmax")
	private double lightnessmax;
	
	public Weather(){}
	
	public int getWeatherId() {
		return weatherid;
	}
 
	public void setWeatherId(int id) {
		this.weatherid = id;
	}
	
	public int getMaxHumi() {
		return humiditymax;
	}

	public void setMaxHumi(int i) {
		this.humiditymax = i;
	}
	
	public int getMinHumi() {
		return humiditymin;
	}


	public void setMinHumi(int i) {
		this.humiditymin = i;
	}


	public double getMaxTemp() {
		return temperaturemax;
	}


	public void setMaxTemp(double maxTemperature) {
		this.temperaturemax = maxTemperature;
	}


	public double getMinTemp() {
		return temperaturemin;
	}


	public void setMinTemp(double minTemperature) {
		this.temperaturemin = minTemperature;
	}


	public double getMinLightness() {
		return lightnessmin;
	}


	public void setMinLightness(double minLightness) {
		this.lightnessmin = minLightness;
	}


	public double getMaxLightnesss() {
		return lightnessmax;
	}


	public void setMaxLightness(double maxLightnesss) {
		this.lightnessmax = maxLightnesss;
	}


	/*public void addWeaterToQueue(Weather newWeather) {
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
	}	*/
 
	

 
	 

	
	
	
}
