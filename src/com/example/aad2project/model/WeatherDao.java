package com.example.aad2project.model;

import java.util.Calendar;

import android.content.Context;
import android.database.Cursor;

public class WeatherDao extends DaoBase {

	public WeatherDao(Context pContext) {
		super(pContext);
	}
	
	public Weather getWeather(int id){
		c = super.mDb.rawQuery("SELECT * FROM Weather WHERE id = " + id, null);
		if(c.moveToFirst()){
			Weather w = new Weather();
			w.setId(c.getInt(0));
			w.setMinTemp(c.getInt(1));
			w.setMaxTemp(c.getInt(2));
			w.setMinHumi(c.getInt(3));
			w.setMaxHumi(c.getInt(4));
			w.setMinLightness(c.getInt(5));
			w.setMinLightness(c.getInt(6));
			return w;
		}
		else
			return null;
	}
	

	public void add(Weather weather) {
		int minHumidity,maxHumidity,maxTemperature,minTemperature,minLightness,maxLightness;
		minHumidity = weather.getMinHumi();
		maxHumidity = weather.getMaxHumi();
		maxTemperature = weather.getMaxTemp();
		minTemperature = weather.getMinTemp();
		minLightness = weather.getMinLightness();
		maxLightness = weather.getMaxLightnesss();

		super.mDb
				.execSQL("INSERT INTO  Plant (minHumidity,maxHumidity,maxTemperature,minTemperature,minLightness,maxLightness)"
						+ "	VALUES ('"
						+ minHumidity
						+ "','"
						+ maxHumidity
						+ "',"
						+ maxTemperature + "," + minTemperature + "," + minLightness + "," + maxLightness + ");");
	}

	protected boolean convertResultToObject() {
		return true;
	}
}
