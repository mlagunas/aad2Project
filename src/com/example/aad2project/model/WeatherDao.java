package com.example.aad2project.model;

import java.util.Calendar;

import android.content.Context;
import android.database.Cursor;

public class WeatherDao extends DaoBase {

	public WeatherDao(Context pContext) {
		super(pContext);
		super.open();
	}
	
	public Weather getWeather(int id){
		c = super.mDb.rawQuery("SELECT * FROM Weather WHERE id = " + id, null);
		if(c.moveToFirst()){
			Weather w = new Weather();
			w.setId(c.getInt(0));
			w.setMinTemp(c.getInt(1));
			w.setMaxTemp(c.getInt(2));
			w.setMinHumi((long) c.getInt(3));
			w.setMinLightness(c.getInt(5));
			w.setMaxLightness(c.getInt(6));
			return w;
		}
		else
			return null;
	}
	

	public void add(Weather weather) {
		double minHumidity,maxTemperature,minTemperature,minLightness,maxLightness;
		minHumidity = weather.getMinHumi();
		maxTemperature = weather.getMaxTemp();
		minTemperature = weather.getMinTemp();
		minLightness = weather.getMinLightness();
		maxLightness = weather.getMaxLightnesss();

		super.mDb
				.execSQL("INSERT INTO  Weather (HumidityMin,TemperatureMax,TemperatureMin,LightnessMin,LightnessMax)"
						+ "	VALUES ('"
						+ minHumidity
						+ "',"
						+ maxTemperature + "," + minTemperature + "," + minLightness + "," + maxLightness + ");");
	}

	protected boolean convertResultToObject() {
		return true;
	}
}
