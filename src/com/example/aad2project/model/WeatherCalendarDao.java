package com.example.aad2project.model;

import android.content.Context;
import android.database.Cursor;

public class WeatherCalendarDao extends DaoBase {

	public WeatherCalendarDao(Context pContext) {
		super(pContext);
	}
	
	protected boolean convertResultToObject(Cursor c) { return true;}
}
