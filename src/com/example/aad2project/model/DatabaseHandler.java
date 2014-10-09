package com.example.aad2project.model;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHandler extends SQLiteOpenHelper {
	public static final String WEATHER_CREATE_TABLE = 
			"CREATE TABLE Weather (                   			        " +
			"id             INTEGER PRIMARY KEY AUTO_INCREMENT NOT NULL," +
			"temperatureMin INTEGER NOT NULL DEFAULT 0,     			" +
			"temperatureMax INTEGER NOT NULL DEFAULT 0,     			" +
			"humidityMin    INTEGER NOT NULL DEFAULT 0,        			" +
			"lightnessMin   INTEGER NOT NULL DEFAULT 0,       			" +
			"lightnessMax   INTEGER NOT NULL DEFAULT 0        			" +
			");";
	
	public static final String PLANT_CREATE_TABLE =
			"CREATE TABLE Plant (									  " +
			"id 		 INTEGER PRIMARY KEY AUTO_INCREMENT NOT NULL, " +
			"name 		 TEXT NOT NULL,								  " +
			"description TEXT NOT NULL,								  " +
			"timeToGrow  INTEGER NOT NULL DEFAULT 0,				  " +
			"number 	 INTEGER NOT NULL DEFAULT 1,				  " +
			"weatherId   INTEGER NOT NULL,							  " +
			"FOREIGN KEY (weatherId) REFERENCES WEATHER(id)			  " +
			");" ;
	
	public static final String TASK_CREATE_TABLE = 
			"CREATE TABLE Task							  			  " +
			"id 		 INTEGER PRIMARY KEY NOT NULL AUTO_INCREMENT, " +
			"description TEXT NOT NULL					     		  " +
			");";
	
	public static final String  TASK_PLANT_CREATE_TABLE =
			"CREATE TABLE TaskPlant ( 							   " +
			"taskId  INTEGER NOT NULL,							   " +
			"plantId INTEGER NOT NULL, 							   " +
			"date    TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP, " +
			"done    INTEGER NOT NULL DEFAULT 0					   " +
			");";
	
	public static final String WEATHER_CALENDAR_CREATE_TABLE =
			"CREATE TABLE WeatherCalendar(" +
			"date 	   TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP," +
			"weatherId INTEGER NOT NULL,				       " +
			"FOREIGN KEY (weatherId) REFERENCES Weather(id)    " +
			");";
	
	public DatabaseHandler(Context context, String name, CursorFactory factory, int version) {
		    super(context, name, factory, version);
	}
	@Override
	public void onCreate(SQLiteDatabase db) {
		db.execSQL(WEATHER_CREATE_TABLE    + " " + 
				   PLANT_CREATE_TABLE      + " " +
				   TASK_CREATE_TABLE       + " " +
				   TASK_PLANT_CREATE_TABLE + " " +
				   WEATHER_CALENDAR_CREATE_TABLE);

	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub

	}

}
