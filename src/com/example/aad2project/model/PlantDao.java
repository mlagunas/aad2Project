package com.example.aad2project.model;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.support.v4.content.LocalBroadcastManager;

import com.example.aad2project.object.Plant;
import com.example.aad2project.object.Task;

public class PlantDao extends DaoBase {

	private Context context;

	public PlantDao(Context pContext) {
		super(pContext);
		this.context = pContext;
		super.open();
	}

	public Plant getPlant(int id) {
		Plant p = new Plant();
		c = super.mDb.rawQuery("SELECT * FROM existingPlants " + "WHERE id = "
				+ id, null);
		if (c.moveToFirst()) {
			p.setId(id);
			p.setName(c.getString(1));
			p.setDescription(c.getString(2));
			p.setTimeToGrow(c.getInt(3));
			p.setWeatherId(c.getInt(4));
			return p;
		} else {
			return null;
		}
	}

	public Plant searchPlant(int id, boolean upperGroup) {
		/*if (!upperGroup) {
			for (Plant p : getAllPlants()) {
				if (p.getId() == id) {
					return p;
				}
			}
		} else {
			for (Plant p : getAddedPlants()) {
				if (p.getId() == id) {
					return p;
				}
			}
		}*/
		Plant p = new Plant();

		if(!upperGroup){
			c = super.mDb.rawQuery("SELECT * FROM ExistingPlants WHERE id = " + id, null);
			if(c.moveToFirst()){
				p.setId(c.getInt(0));
				p.setName(c.getString(1));
				p.setDescription(c.getString(2));
				p.setTimeToGrow(c.getInt(3));
				p.setWeatherId(c.getInt(4));
				return p;
			}
			return null;
		}
		else{
			c = super.mDb.rawQuery("SELECT * FROM Plant WHERE id = "+ id, null);
			if (c.moveToFirst()) {
				
				p.setId(c.getInt(0));
				p.setDate(new Date(c.getLong(1)));
				p.setExistingId(c.getInt(2));
				Cursor aux = super.mDb.rawQuery("SELECT * FROM existingPlants WHERE id = " + c.getInt(2), null);
				if(aux.moveToFirst()){
					p.setName(aux.getString(1));
					p.setDescription(aux.getString(2));
					p.setTimeToGrow(aux.getInt(3));
					p.setWeatherId(aux.getInt(4));
				}
				return p;
			}
			return null;
		}
	}

	public Plant getPlant(String name) {
		Plant p = new Plant();
		c = super.mDb.rawQuery("SELECT * FROM existingPlants "
				+ "WHERE name = " + name, null);
		if (c.moveToFirst()) {
			p.setId(c.getInt(0));
			p.setName(name);
			p.setDescription(c.getString(2));
			p.setTimeToGrow(c.getInt(3));
			p.setWeatherId(c.getInt(4));
			return p;
		} else {
			return null;
		}
	}

	/**
	 * This method add a plant into the Database
	 * 
	 * @param plant
	 */
	public void addPlant(Plant plant) {

		String name, description;
		int timeToGrow, number, id;
		Date tday;

		name = plant.getName();
		description = plant.getDescription();
		timeToGrow = plant.getTimeToGrow();
		number = plant.getNumber();
		id = plant.getId();
		tday = new Date();
		
		super.mDb
				.execSQL("INSERT INTO  Plant (existingId,date)"
						+ "	VALUES ("
						+ id
						+ ", "
						+ tday.getTime()
						+ ");");

		Task t = new Task();
		t.setDescription("Collect " + name);

		TaskDao td = new TaskDao(context);
		td.addTask(t);

		Plant insert = new Plant();
		
		c = super.mDb.rawQuery("SELECT id FROM Plant", null);
		c.moveToLast();
		insert.setId(c.getInt(0));
		insert.setName(name);
		insert.setDescription(description);
		insert.setTimeToGrow(timeToGrow);
		insert.setExistingId(id);
		
		c = super.mDb.rawQuery("SELECT id FROM Task", null);
		c.moveToLast();
		t.setId(c.getInt(0));

		TaskPlantDao tp = new TaskPlantDao(context);

		Calendar today = Calendar.getInstance();
		Date toGrow = new Date(today.getTimeInMillis()
				+ (1000 * 60 * 60 * 24 * timeToGrow));
		tp.createTaskPlant(plant, t, toGrow.getTime());

		// Notify the Loader
		notifyLoader();
	}

	/**
	 * This method return an ArrayList which contains all the plants added in
	 * the database
	 * 
	 * @return
	 */
	public ArrayList<Plant> getAllPlants() {
		ArrayList<Plant> plants = new ArrayList<Plant>();
		Cursor c = super.mDb.rawQuery("SELECT * FROM ExistingPlants", null);
		// Read the result of the Query and Add the objects to the ArrayList
		if (c.moveToFirst()) {
			do {
				Plant p = new Plant();
				p.setName(c.getString(1));
				p.setId(c.getInt(0));
				p.setDescription(c.getString(2));
				p.setTimeToGrow(c.getInt(3));
				p.setWeatherId(c.getInt(4));
				p.setExistingId(-1);
				plants.add(p);
			} while (c.moveToNext());
		}

		return plants;
	}

	/**
	 * This method returns an ArrayList with all the plants which the user have
	 * added to the garden
	 * 
	 * @return
	 */
	public ArrayList<Plant> getAddedPlants() {
		ArrayList<Plant> plants = new ArrayList<Plant>();

		c = super.mDb.rawQuery("SELECT * FROM Plant", null);
		// Read the result of the Query and Add the objects to the ArrayList
		if (c.moveToFirst()) {
			do {
					Plant p = new Plant();
					p.setId(c.getInt(0));
					p.setDate(new Date(c.getLong(1)));
					p.setExistingId(c.getInt(2));
					Cursor aux = super.mDb.rawQuery("SELECT * FROM existingPlants WHERE id = " + c.getInt(2), null);
					if(aux.moveToFirst()){
						p.setName(aux.getString(1));
						p.setDescription(aux.getString(2));
						p.setTimeToGrow(aux.getInt(3));
						p.setWeatherId(aux.getInt(4));
					}
					plants.add(p);
			} while (c.moveToNext());
		}

		return plants;
	}

	
	
	protected boolean convertResultToObject() {
		return true;
	}

	public void deletePlant(int id) {
		TaskPlantDao tp = new TaskPlantDao(context);
		super.mDb.execSQL("DELETE FROM TaskPlant WHERE plantId = " + id);
		tp.deleteTaskPlant(id);
		super.mDb.execSQL("DELETE FROM Plant " + "WHERE id = " + id + ";");

		// Notify the Loader
		notifyLoader();
	}

	private void notifyLoader() {
		Intent intent = new Intent("plant-database-changed");
		LocalBroadcastManager.getInstance(context).sendBroadcast(intent);
	}
}
