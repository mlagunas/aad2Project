package com.example.aad2project.model;

import java.util.ArrayList;

import android.content.Context;
import android.database.Cursor;

public class PlantDao extends DaoBase {
	
	public PlantDao (Context pContext) {
		super(pContext);
		super.open();
	}
 	
	public void addPlant(Plant plant) {
		
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
		super.mDb.execSQL("INSERT INTO  Plant (id,name,description,timeToGrow,number,weatherId)" +
				"	VALUES ("+getId()+",'"+name+"','"+description+"',"+timeToGrow+","+number+",1);");
	}
	
	/**
	 * This method return the next free id 
	 * @return
	 */
	private int getId(){
		 Cursor c = super.mDb.rawQuery("SELECT * FROM Plant",null);
		//Read the result of the Query
		int result = 0;
		if (c.moveToFirst()) {
		     do {
		    	 result ++;
		     } while(c.moveToNext());
		}
		return result;
	 }
	protected boolean convertResultToObject() { return true;}
}
