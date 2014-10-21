package com.example.aad2project.model;

import java.util.ArrayList;

import android.content.Context;
import android.database.Cursor;

public class PlantDao extends DaoBase {
	
	public PlantDao (Context pContext) {
		super(pContext);
		super.open();
	}
 	
	/**
	 * This method add a plant 
	 * @param plant
	 */
	public void addPlant(Plant plant) {
		String name,description;
		int timeToGrow,number;
		name = plant.getName();
		description = plant.getDescription();
		timeToGrow = plant.getTimeToGrow();
		number = plant.getNumber();
		
		super.mDb.execSQL("INSERT INTO  Plant (name,description,timeToGrow,number,weatherId)" +
				"	VALUES ('"+name+"','"+description+"',"+timeToGrow+","+number+",1);");
	}
	
	/**
	 * This method return an ArrayList which contains all the plants added in the database
	 * @return
	 */
	public ArrayList<Plant> getAllPlants(){
		ArrayList<Plant> plants = new ArrayList<Plant>();
		Cursor c = super.mDb.rawQuery("SELECT * FROM ExistingPlants",null);
		//Read the result of the Query and Add the objects to the ArrayList
		if (c.moveToFirst()) {
		     do {
		         Plant p = new Plant();
		         p.setName(c.getString(1));
		    	 plants.add(p);
		     } while(c.moveToNext());
		}
		
		return plants;
	}
	
	
	public void deletePlant(Plant p){
		
	}
	/**
	 * This method returns an ArrayList with all the plants which the user 
	 * have added to the garden
	 * @return
	 */
	public ArrayList<Plant> getAddedPlants(){
		ArrayList<Plant> plants = new ArrayList<Plant>();
		
		Cursor c = super.mDb.rawQuery("SELECT * FROM Plant",null);
		//Read the result of the Query and Add the objects to the ArrayList
		if (c.moveToFirst()) {
		     do {
		    	 if(c.getInt(4) > 0){
			         Plant p = new Plant();
			         p.setName(c.getString(1));
			         p.setDescription(c.getString(2));
			         p.setTimeToGrow(c.getInt(3));
			         p.setId(c.getInt(0));
			    	 plants.add(p);
		    	 }
		     } while(c.moveToNext());
		}
		
		return plants;
	}
	
	/**
	 * This method add a new Plant to the database
	 * @param name
	 * @param description
	 * @param timeToGrow
	 * @param number
	 */
	public void addPlant(String name, String description,int timeToGrow, int number){
		super.mDb.execSQL("INSERT INTO  Plant (name,description,timeToGrow,number,weatherId)" +
				"	VALUES ('"+name+"','"+description+"',"+timeToGrow+","+number+",1);");
	}
	
	
	protected boolean convertResultToObject() { return true;}

	public void deletePlant(int id) {
		super.mDb.execSQL("DELETE FROM Plant " +
					"WHERE id = "+id+";");
		
	}
}
