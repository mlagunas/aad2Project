package com.example.aad2project.model;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;

import com.example.aad2project.object.Green;
import com.example.aad2project.object.Plant;
import com.example.aad2project.object.Task;
import com.example.aad2project.object.TaskPlant;
import com.microsoft.windowsazure.mobileservices.MobileServiceTable;
import com.microsoft.windowsazure.mobileservices.ServiceFilterResponse;
import com.microsoft.windowsazure.mobileservices.TableDeleteCallback;
import com.microsoft.windowsazure.mobileservices.TableOperationCallback;

public class PlantDao extends DaoBase {

	private Context context;
	
	/**
	 * Mobile Service Table used to access data
	 */
	private MobileServiceTable<Plant> tableP;
	
	public PlantDao(Context pContext) {
		super(pContext);
		super.open();
		this.context = pContext;
		tableP = mClient.getTable(Plant.class);
		
	}
	
	/**
	 * Search a plant in the two list that the user can find in the plant manager
	 * @param id
	 * @param upperGroup
	 * @return
	 */
	public Green searchPlant(int id, boolean upperGroup) {
		Green p = new Green();
		
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

	/**
	 * Return a plant from the database from the name
	 * @param name
	 * @return
	 */
	public Green getPlant(String name) {
		Green p = new Green();
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
	 * Search a plant to delete from the cloud from the id 
	 * @param id
	 * @return
	 */
	public Plant searchDeletePlant (int id){
		Plant p = new Plant();
		c = super.mDb.rawQuery("SELECT * FROM Plant "
				+ "WHERE id = " + id, null);
		if (c.moveToFirst()) {
			p.setPlantid(c.getInt(0));
			p.setExistingId(c.getInt(2));
			p.setDate(new Date(c.getLong(1)));
			p.setNumber(0);
			p.setId(c.getString(4));
			Log.d("TEST",p.getId());
			return p;
		} else {
			return null;
		}
	}
	
	/**
	 * returns a Green object from the id of a plant
	 * @param id
	 * @return
	 */
	public Green getPlant(int id) {
		Green p = new Green();
		c = super.mDb.rawQuery("SELECT * FROM existingPlants " + "WHERE id = "
				+ id, null);
		if (c.moveToFirst()) {
			p.setId(id);
			p.setName(c.getString(1));
			p.setDescription(c.getString(2));
			p.setTimeToGrow(c.getInt(3));
			p.setWeatherId(c.getInt(4));
			p.setExistingId(c.getInt(0));
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
	public void addPlant(Green plant,boolean cloud) {

		/*
		 * Different attributes that the plant needs
		 */
		String name, code;
		int timeToGrow, id;
		Date tday;

		name = plant.getName();
		timeToGrow = plant.getTimeToGrow();
		tday = new Date();
		code = plant.getCode();
		
		if(plant.getExistingId() == -1)
			id = plant.getId();
		else
			id = plant.getExistingId();
		
		super.mDb
				.execSQL("INSERT INTO  Plant (existingId,date,code)"
						+ "	VALUES ("
						+ id
						+ ", "
						+ tday.getTime()
						+ ","
						+"'"+code+"'"
						+ ");");
		
		//Insert the plant also in the Cloud database
		if(cloud){
			Plant i = new Plant();
			i.setDate(tday);
			i.setExistingId(id);
			c = mDb.rawQuery("SELECT id FROM Plant",null);
			c.moveToLast();
			i.setPlantid(c.getInt(0));
			tableP.insert(i, new TableOperationCallback<Plant>(){
				@Override
				public void onCompleted(
						Plant arg0,
						Exception arg1, ServiceFilterResponse arg2) {
					// TODO Auto-generated method stub
					if(arg1 != null){
						Log.d("TAG",arg1.getCause().toString());
					}
				}
				
			});
		}
		
		//Create a new Task to, afterwards, bind it with the Plant
		Task t = new Task();
		t.setDescription("Collect " + name);

		TaskDao td = new TaskDao(context);
		td.addTask(t);
		
		c = super.mDb.rawQuery("SELECT id FROM Task", null);
		c.moveToLast();
		t.setId(c.getInt(0));
		
		//Create new object TaskPlant and introduce it to the database
		TaskPlantDao tp = new TaskPlantDao(context);

		Calendar today = Calendar.getInstance();
		Date toGrow = new Date(today.getTimeInMillis()
				+ (1000 * 60 * 60 * 24 * timeToGrow));
		tp.createTaskPlant(plant, t, toGrow.getTime());

		// Notify the Loader
		notifyLoader();
	}

	/**
	 * Converts a object plant into a green object searching the missing fields in the 
	 * database 
	 * @param p
	 * @return
	 */
	public Green plantToGreen(Plant p){
		Green g = new Green();
		g.setDate(p.getDate());
		g.setExistingId(p.getExisitingId());
		g.setNumber(p.getNumber());
		g.setId(p.getPlantid());
		g.setCode(p.getId());
		//retrieve the rest of the attributes
		c = mDb.rawQuery("SELECT * FROM " +
						"existingPlants " +
						"where id = "+ p.getExisitingId(),null);
			c.moveToFirst();
			g.setName(c.getString(1));
			g.setDescription(c.getString(2));
			g.setTimeToGrow(c.getInt(3));
			g.setWeatherId(c.getInt(4));
		
		return g;
	}
	
	/**
	 * This method return an ArrayList which contains all the plants added in
	 * the database
	 * 
	 * @return
	 */
	public ArrayList<Green> getAllPlants() {
		ArrayList<Green> plants = new ArrayList<Green>();
		Cursor c = super.mDb.rawQuery("SELECT * FROM ExistingPlants", null);
		// Read the result of the Query and Add the objects to the ArrayList
		if (c.moveToFirst()) {
			do {
				Green p = new Green();
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
	public ArrayList<Green> getAddedPlants() {
		ArrayList<Green> plants = new ArrayList<Green>();

		c = super.mDb.rawQuery("SELECT * FROM Plant", null);
		// Read the result of the Query and Add the objects to the ArrayList
		if (c.moveToFirst()) {
			do {
					Green p = new Green();
					p.setId(c.getInt(0));
					p.setDate(new Date(c.getLong(1)));
					p.setExistingId(c.getInt(2));
					Cursor aux = super.mDb.rawQuery("SELECT * FROM existingPlants WHERE id = " + c.getInt(2), null);
					if(aux.moveToFirst()){
						p.setName(aux.getString(1));
						p.setDescription(aux.getString(2));
						p.setTimeToGrow(aux.getInt(3));
						p.setWeatherId(aux.getInt(4));
						Log.d("TAG",p.getName()+" "+p.getDescription());
					}
					plants.add(p);
			} while (c.moveToNext());
		}

		return plants;
	}

	
	
	protected boolean convertResultToObject() {
		return true;
	}

	/**
	 * Deletes a plant from the local database and from the cloud
	 * @param id
	 */
	public void deletePlant(int id) {
		tableP.delete(searchDeletePlant(id), new TableDeleteCallback(){
			@Override
			public void onCompleted(Exception arg0, ServiceFilterResponse arg1) {
				// TODO Auto-generated method stub
				if(arg0 == null){
					Log.d("TAG", "deleted");
				}
				else{
					Log.d("TAG",arg0.getMessage());
				}
			}
		});
		TaskPlantDao tp = new TaskPlantDao(context);
		for(TaskPlant t: tp.getAllTaskPlant()){
			Log.d("DELETE",t.getId()+"    "+t.getIdTask());

		}
		super.mDb.execSQL("DELETE FROM TaskPlant WHERE plantId = " + id);
		tp.deleteTaskPlant(id);
		super.mDb.execSQL("DELETE FROM Plant " + "WHERE id = " + id);
		
		// Notify the Loader
		notifyLoader();
	}

	private void notifyLoader() {
		Intent intent = new Intent("plant-database-changed");
		LocalBroadcastManager.getInstance(context).sendBroadcast(intent);
	}
}
