package com.example.aad2project.model;

import android.content.Context;

import com.example.aad2project.object.Weather;

public class WeatherDao extends DaoBase {

	public WeatherDao(Context pContext) {
		super(pContext);
		super.open();
	}

	public Weather getWeather(int id) {
		c = super.mDb.rawQuery("SELECT * FROM Weather WHERE id = " + id, null);
		if (c.moveToFirst()) {
			Weather w = new Weather();
			w.setWeatherId(c.getInt(0));
			w.setMinTemp(c.getInt(1));
			w.setMaxTemp(c.getInt(2));
			w.setMinHumi(c.getInt(3));
			w.setMaxHumi(c.getInt(4));
			w.setMinLightness(c.getInt(5));
			w.setMaxLightness(c.getInt(6));
			return w;
		} else
			return null;
	}

	public void add(Weather weather) {
		double minHumidity, maxHumidity, maxTemperature, minTemperature, minLightness, maxLightness;
		int id;

		minHumidity = weather.getMinHumi();
		maxHumidity = weather.getMaxHumi();
		maxTemperature = weather.getMaxTemp();
		minTemperature = weather.getMinTemp();
		minLightness = weather.getMinLightness();
		maxLightness = weather.getMaxLightnesss();
		id = weather.getWeatherId();
		super.mDb
				.execSQL("INSERT INTO  Weather (id,HumidityMin,humidityMax,TemperatureMax,TemperatureMin,LightnessMin,LightnessMax)"
						+ "	VALUES ("
						+ id
						+ ", "
						+ minHumidity
						+ ","
						+ maxHumidity
						+ ","
						+ 
						
						
						
						
						
						
						
						
						
						
						
						
						
						
						
						
						
						
						
						
						
						
						
						
						
						
						
						
						
						
						
						
						
						
						
						
						
						
						
						
						
						
						
						
						
						
						
						
						
						
						
						
						
						
						
						
						
						
						
						
						
						
						
						
						
						
						
						
						
						
						
						
						
						
						
						
						
						
						
						
						maxTemperature
						+ ","
						+ minTemperature
						+ ","
						+ minLightness
						+ ","
						+ maxLightness + ");");
	}

	protected boolean convertResultToObject() {
		return true;
	}
}
