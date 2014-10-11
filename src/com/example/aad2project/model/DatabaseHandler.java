package com.example.aad2project.model;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHandler extends SQLiteOpenHelper {
	public static final String WEATHER_CREATE_TABLE = 
			"CREATE TABLE Weather (                   			        " +
			"id             NUMBER(4) PRIMARY KEY ," +
			"temperatureMin NUMBER(2) NOT NULL DEFAULT 0,     			" +
			"temperatureMax NUMBER(2) NOT NULL DEFAULT 0,     			" +
			"humidityMin    NUMBER(3) NOT NULL DEFAULT 0,        			" +
			"lightnessMin   NUMBER(5) NOT NULL DEFAULT 0,       			" +
			"lightnessMax   NUMBER(5) NOT NULL DEFAULT 0        			" +
			");";
	
	public static final String PLANT_CREATE_TABLE =
			"CREATE TABLE Plant (									  " +
			"id 		 NUMBER(4) PRIMARY KEY, " +
			"name 		 VARCHAR(50) NOT NULL,								  " +
			"description VARCHAR(200) NOT NULL,								  " +
			"timeToGrow  NUMBER(4) NOT NULL DEFAULT 0,				  " +
			"number 	 NUMBER(2) NOT NULL DEFAULT 1,				  " +
			"weatherId   NUMBER(2) NOT NULL,							  " +
			"FOREIGN KEY (weatherId) REFERENCES WEATHER(id)			  " +
			");" ;
	
	public static final String TASK_CREATE_TABLE = 
			"CREATE TABLE Task (							  			  " +
			"id 		 NUMBER(4) PRIMARY KEY, " +
			"description VARCHAR(200) NOT NULL					     		  " +
			");";
	
	public static final String  TASK_PLANT_CREATE_TABLE =
			"CREATE TABLE TaskPlant ( 							   " +
			"taskId  NUMBER(4) NOT NULL,							   " +
			"plantId NUMBER(4) NOT NULL, 							   " +
			"date    TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP, " +
			"done    NUMBER(2) NOT NULL DEFAULT 0					   " +
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
		db.execSQL(WEATHER_CREATE_TABLE);
		db.execSQL(PLANT_CREATE_TABLE);
		db.execSQL(TASK_CREATE_TABLE);
		db.execSQL(TASK_PLANT_CREATE_TABLE);
		db.execSQL(WEATHER_CALENDAR_CREATE_TABLE);
		
		db.execSQL("INSERT INTO  Plant (id,name,description,timeToGrow,number,weatherId)" +
				"	VALUES (0,'tomatoes','Red plant which grows in Summer','25 days',0,1);");
		
		db.execSQL("INSERT INTO  Plant (id,name,description,timeToGrow,number,weatherId)" +
				"	VALUES (1,'potatoes','This plant grows underground and it has a yellow or red colour depending" +
				" on the kind of potatoe','60 days',0,1);");
		
		db.execSQL("INSERT INTO  Plant (id,name,description,timeToGrow,number,weatherId)" +
				"	VALUES (2,'lettuce','Green plant ','25 days',0,1);");

	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub

	}

}
