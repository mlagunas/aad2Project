package com.example.aad2project.model;

import java.util.Calendar;

import android.content.Context;
import android.database.Cursor;

public class WeatherDao extends DaoBase {

	public WeatherDao(Context pContext) {
		super(pContext);
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
