package com.example.aad2project.model;

import android.content.Context;
import android.database.Cursor;

public class WeatherDao extends DaoBase {

	public WeatherDao(Context pContext) {
		super(pContext);
	}
	
	protected boolean convertResultToObject() { return true;}
}
