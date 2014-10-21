package com.example.aad2project.model;

import java.util.Date;

public class WeatherCalendar {
	private int id;
	private Date date;
	private Weather weather;
	private WeatherCalendar next; 
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	} 	
	
	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Weather getWeather() {
		return weather;
	}
	
	public void setWeather(Weather weather) {
		this.weather = weather;
	}
	
	public void addWeatherCalendarToQueue(WeatherCalendar newWeatherCalendar) {
		if (this.next == null) {
			this.next = newWeatherCalendar;
		} else {
			this.next.addWeatherCalendarToQueue(newWeatherCalendar);
		}
	}
	
	public WeatherCalendar getWeatherCalendarNumber(int i) {
		WeatherCalendar weatherCalendar = this;
		
		for (int j = 0; j < i; j++) {
			weatherCalendar = weatherCalendar.next;
		}
		
		return weatherCalendar;
	}
	
	public int size() {
		int j = 1;
		WeatherCalendar weatherCalendar = this;
		do {
			j++;
		} while(weatherCalendar.next != null);
		return j;
	}
}
