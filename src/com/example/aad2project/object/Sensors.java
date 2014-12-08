package com.example.aad2project.object;

public class Sensors {
	
	@com.google.gson.annotations.SerializedName("id")
	private String id;
	
	@com.google.gson.annotations.SerializedName("humidity")
	private int humidity;
	
	@com.google.gson.annotations.SerializedName("temperature")
	private int temperature;
	
	@com.google.gson.annotations.SerializedName("brightness")
	private int brightness;
	
	public int getHumidity() {
		return humidity;
	}
	public void setHumidity(int humidity) {
		this.humidity = humidity;
	}
	public int getTemperature() {
		return temperature;
	}
	public void setTemperature(int temperature) {
		this.temperature = temperature;
	}
	public int getBrightness() {
		return brightness;
	}
	public void setBrightness(int brightness) {
		this.brightness = brightness;
	}
	
	
}
