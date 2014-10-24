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
			"humidityMax    NUMBER(3) NOT NULL DEFAULT 0, " +
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
	
	public static final String INSERT_WEATHER_Potatoes = 
			"INSERT INTO Weather (id, temperatureMin, temperatureMax, humidityMin, humidityMax, lightnessMin, lightnessMax" +
			") VALUES ( 1, 15, 20, 40, 60, 25000, 75000)";
	
	public static final String INSERT_WEATHER_CARROTS = 
			"INSERT INTO Weather (id, temperatureMin, temperatureMax, humidityMin, humidityMax, lightnessMin, lightnessMax" +
			") VALUES ( 2, 15, 18, 10, 20, 500, 50000)";
	
	public static final String INSERT_WEATHER_TOMATOES =
			"INSERT INTO Weather (id, temperatureMin, temperatureMax, humidityMin, humidityMax, lightnessMin, lightnessMax" +
			") VALUES ( 3, 16, 20, 20, 30, 25000, 75000)";
	
	public static final String INSERT_WEATHER_LETTUCE =
			"INSERT INTO Weather (id, temperatureMin, temperatureMax, humidityMin, humidityMax, lightnessMin, lightnessMax" +
			") VALUES ( 4, 12, 15, 40, 60, 25000, 75000)";
	
	public static final String INSERT_WEATHER_SWEETPEAS =
			"INSERT INTO Weather (id, temperatureMin, temperatureMax, humidityMin, humidityMax, lightnessMin, lightnessMax" +
			") VALUES ( 5, 15, 20, 40, 60, 25000, 75000)";
	
	public static final String INSERT_WEATHER_LILIES =
			"INSERT INTO Weather (id, temperatureMin, temperatureMax, humidityMin, humidityMax, lightnessMin, lightnessMax" +
			") VALUES ( 6, 15, 20, 40, 60, 25000, 75000)";
	
	public static final String INSERT_POTATOES =
			"INSERT INTO ExistingPlants (id, name, description, timeToGrow, weatherId) VALUES (1,'Potatoes'," + 
			"'Potatoes are a key for anybody who seeks to be self-sufficient. The potato is a starchy, tuberous crop that came from America but grow easily all over the world. It takes between 65 and 100 days to grow, and it should planted in spring. The soil should always be humid so you should water the around 3 times a week. When they flourish they will need more water. They can be harvested whenever they flourish and they should leave on the ground 1 day so they get dry.'," +
			"120, 1);";
			
	public static final String INSERT_CARROTS =
			"INSERT INTO ExistingPlants (id, name, description, timeToGrow, weatherId) VALUES (2, 'Carrots'," + 
			"'Carrots are a root vegetable, usually orange and with a crisp texture when fresh. They provide a good source of A vitamin which is good for the sight. They take between 2 and 3 months to grow and they should be planted either in spring, autumn or winter. Carrot seeds need a lot of humidity so two days before planting you could put the seeds between two humid papers. Make a hole of 1 cm deep in the ground and pour more than one seed because some will not germinate. They like cold climate but sunny at the same time. It is recommended that they get sun throughout all the day. When watering carrots you have to make sure that the water reaches deep in the hole.'," +
			"120, 2);";
	
	public static final String INSERT_TOMATOES =
			"INSERT INTO ExistingPlants (id, name, description, timeToGrow, weatherId) VALUES (3, 'Tomatoes'," + 
			"'Tomato is a red fruit but considered a vegetable for culinary purposes. Its many varieties are now widely grown, sometimes in greenhouses in cooler climates. It should be planted in the end of spring and it will grow in about 3 months. Tomatoes need rich soil so you can previously treat yours with compost or similar. You have also to be careful with the cold since it can kill the plant. Tomatoes also need at least 6 hours of sun so make sure that your garden or terrace has plenty of sunlight. Regarding the watering, you should not water the plant’s leaves and water the plant not so regularly but with abundant water each time. You should also put a vertical stick attached so it grows properly.'," +
			"210, 3);";
			
	public static final String INSERT_LETTUCE =
			"INSERT INTO ExistingPlants (id, name, description, timeToGrow, weatherId) VALUES (4, 'Lettuces'," + 
			"'Lettuce is a green annual plant which is most often grown as a leaf vegetable. It is easily cultivated, although it requires relatively low temperatures to prevent it from flowering quickly. It can be planted almost throughout all the year and it takes between 20 and 65 days to grow. The soil must be very rich in nutrients and have to drain water very good. You should also maintain the ground humid. You can harvest you lettuce during all the flourishing period, as they will always be very tasty.'," +
			"56, 4);";
	
	public static final String INSERT_SweetPea =
			"INSERT INTO ExistingPlants (id, name, description, timeToGrow, weatherId) VALUES (5, 'Sweet Peas'," + 
			"'Sweet peas enchant us with their fragile, seductive fragrance and make great bouquets. These pea-like flowers grow in many lovely colours and are suitable for an annual border, a woodland garden, and a trellis or arch. Early sowing is one of the secrets of sweet peas. The bloom time for this flower is in summer and fall. Before planting, soak the seeds in water for 1 day. If you are in temperate climate you do not need to do so. Germination can take 7 to 15 days and you must keep the soil humid and moist. Sweet peas prefer cool days and nights and will start to fade when temperatures go above 65 Fº. They are climbers so give them at least 1.8 m of good support.'," +
			"70, 5);";	
	
	public static final String INSERT_LILIES =
			"INSERT INTO ExistingPlants (id, name, description, timeToGrow, weatherId) VALUES (6, 'Lilies'," + 
			"'Lilies are a group of flowering plants which are important in culture and literature in much of the world. Lily flowers are valued for their very showy, often fragrant flowers. You should plant the bulbs in autumn and they need from 6 to 8 hours of sunlight. Regarding the soil, lilies require a well-drained site as water trapped beneath the scales may rot the bulb. In active growth, water freely and apply a high-potash liquid fertilizer every 2 weeks.'," +
			"30, 6);";
			
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
		db.execSQL(INSERT_WEATHER_Potatoes);
		db.execSQL(INSERT_WEATHER_CARROTS);
		db.execSQL(INSERT_WEATHER_TOMATOES);
		db.execSQL(INSERT_WEATHER_LETTUCE);
		db.execSQL(INSERT_WEATHER_SWEETPEAS);
		db.execSQL(INSERT_WEATHER_LILIES);
		db.execSQL(INSERT_POTATOES);
		db.execSQL(INSERT_CARROTS);
		db.execSQL(INSERT_LETTUCE);
		db.execSQL(INSERT_SweetPea);
		db.execSQL(INSERT_LILIES);
				
	}
	
	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub

	}

}
