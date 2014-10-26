package com.example.aad2project.model;

import java.util.Date;
import java.util.ArrayList;
import java.util.Calendar;
import android.content.Context;
import android.database.Cursor;
import android.util.Log;

import com.example.aad2project.ui.TaskCalendarFragment;

public class PlantDao extends DaoBase {

	private Context context;
	private TaskCalendarFragment fragment;

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
			return p;
		} else {
			return null;
		}
	}

	public Plant searchPlant(int id) {
		for (Plant p : getAllPlants()) {
			if (p.getId() == id) {
				Log.d("PLANT",p.getName());
				return p;
			}
		}
		for(Plant p: getAddedPlants()){
			if(p.getId() == id){
				Log.d("PLANT",p.getName());
				return p;
			}
		}
		return null;
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
		int timeToGrow, number;

		name = plant.getName();
		description = plant.getDescription();
		timeToGrow = plant.getTimeToGrow();
		number = plant.getNumber();

		super.mDb
				.execSQL("INSERT INTO  Plant (name,description,timeToGrow,number,weatherId)"
						+ "	VALUES ('"
						+ name
						+ "','"
						+ description
						+ "',"
						+ timeToGrow + "," + number + ",1);");

		Task t = new Task();
		t.setDescription("Collect " + name);

		TaskDao td = new TaskDao(context);
		td.addTask(t);

		Cursor c = super.mDb.rawQuery("SELECT id FROM Plant", null);
		c.moveToLast();
		plant.setId(c.getInt(0));

		c = super.mDb.rawQuery("SELECT id FROM Task", null);
		c.moveToLast();
		t.setId(c.getInt(0));

		TaskPlantDao tp = new TaskPlantDao(context);

		Calendar today = Calendar.getInstance();
		Date toGrow = new Date(today.getTimeInMillis()
				+ (1000 * 60 * 60 * 24 * timeToGrow));

		tp.createTaskPlant(plant, t, toGrow.getTime());
	}

	public void setFragment(TaskCalendarFragment fragment) {
		this.fragment = fragment;
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
				plants.add(p);
			} while (c.moveToNext());
		}

		return plants;
	}

	public void deletePlant(Plant p) {

	}

	/**
	 * This method returns an ArrayList with all the plants which the user have
	 * added to the garden
	 * 
	 * @return
	 */
	public ArrayList<Plant> getAddedPlants() {
		ArrayList<Plant> plants = new ArrayList<Plant>();

		Cursor c = super.mDb.rawQuery("SELECT * FROM Plant", null);
		// Read the result of the Query and Add the objects to the ArrayList
		if (c.moveToFirst()) {
			do {
				if (c.getInt(4) > 0) {
					Plant p = new Plant();
					int id = c.getInt(0);

					p.setId(id);
					p.setName(c.getString(1));
					p.setDescription(c.getString(2));
					p.setTimeToGrow(c.getInt(3));
					plants.add(p);
				}
			} while (c.moveToNext());
		}

		return plants;
	}

	protected boolean convertResultToObject() {
		return true;
	}

	public void deletePlant(int id) {

		TaskPlantDao tp = new TaskPlantDao(context);

		tp.deleteTaskPlant(id);
		
		super.mDb.execSQL("DELETE FROM Plant " +
					"WHERE id = "+id+";");
	}
}
