package com.example.aad2project.model;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHandler extends SQLiteOpenHelper {
	public static final String WEATHER_CREATE_TABLE = 
			"CREATE TABLE Weather (                   			        " +
			"id             Integer PRIMARY KEY AUTOINCREMENT," +
			"temperatureMin NUMBER(2) NOT NULL DEFAULT 0,     			" +
			"temperatureMax NUMBER(2) NOT NULL DEFAULT 0,     			" +
			"humidityMin    NUMBER(3) NOT NULL DEFAULT 0,        			" +
			"lightnessMin   NUMBER(5) NOT NULL DEFAULT 0,       			" +
			"lightnessMax   NUMBER(5) NOT NULL DEFAULT 0        			" +
			");";
	
	public static final String PLANT_CREATE_TABLE =
			"CREATE TABLE Plant (									  " +
			"id 		 Integer PRIMARY KEY AUTOINCREMENT, " +
			"name 		 VARCHAR(50) NOT NULL,								  " +
			"description VARCHAR(200) NOT NULL,								  " +
			"timeToGrow  NUMBER(4) NOT NULL DEFAULT 0,				  " +
			"number 	 NUMBER(2) NOT NULL DEFAULT 1,				  " +
			"weatherId   NUMBER(2) NOT NULL,							  " +
			"FOREIGN KEY (weatherId) REFERENCES WEATHER(id)			  " +
			");" ;
	
	public static final String TASK_CREATE_TABLE = 
			"CREATE TABLE Task (							  			  " +
			"id 		 Integer PRIMARY KEY AUTOINCREMENT, " +
			"description VARCHAR(200) NOT NULL					     		  " +
			");";
	
	public static final String  TASK_PLANT_CREATE_TABLE =
			"CREATE TABLE TaskPlant ( 	" +
			"taskId  NUMBER(4) NOT NULL,							   " +
			"plantId NUMBER(4) NOT NULL, 							   " +
			"date    TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP, " +
			"done    NUMBER(1) NOT NULL DEFAULT 0, " +
			"PRIMARY KEY(plantId,taskId)"+
			");";
	
	public static final String WEATHER_CALENDAR_CREATE_TABLE =
			"CREATE TABLE WeatherCalendar(" +
			"date 	   TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP," +
			"weatherId INTEGER NOT NULL,				       " +
			"FOREIGN KEY (weatherId) REFERENCES Weather(id)    " +
			");";
	public static final String ALL_EXISTING_PLANTS =
			"CREATE TABLE ExistingPlants(" +
			"id		Integer PRIMARY KEY AUTOINCREMENT,	" +
			"name	VARCHAR(100) NOT NULL," +
			"description VARCHAR(200) NOT NULL," +
			"timeToGrow NUMBER(3) NOT NULL," +
			"weatherId   NUMBER(2) NOT NULL," +
			"FOREIGN KEY (weatherId) REFERENCES WEATHER(id)" +
			");" ;
		    
		
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
		db.execSQL(ALL_EXISTING_PLANTS);
		
		initializeDB(db);
		
		}
	
	private void initializeDB(SQLiteDatabase db){
		String[] plants = new String[]{
				new String("tomatoes"),
				new String("potatoes"),
				new String("onions"),
				new String("lettuce")
		};
		String[] description = new String[]{
				new String("red plant"),
				new String("grows underground"),
				new String("white plant, grows underground"),
				new String("green and white plant")
		};
		
		for (int i=0; i< plants.length;i++){
			db.execSQL(add(i,plants[i],description[i],(i+10),1));
		}
	}
	
	private String add(int id, String name, String description, int timeToGrow, int weatherId){
		return "INSERT INTO ExistingPlants " +
				"(id,name,description,timeTogrow,weatherId) VALUES (" +
				id+" ,'"+name+"','"+description+"', "+timeToGrow+","+weatherId+");";
	}
	
	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub

	}

}
