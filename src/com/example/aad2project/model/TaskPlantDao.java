package com.example.aad2project.model;

import android.content.Context;

public class TaskPlantDao extends DaoBase {

	private TaskPlant taskPlant;
	
	public TaskPlantDao (Context pContext) {
		super(pContext);
	}
	
	public TaskPlant getCurrentDayTaskPlant() {
		
		query = "SELECT t.id, t.description, p.id, p.name, " +
				"tp.date, tp.done " +
				"FROM Task t INNER JOIN TaskPlant tp ON tp.taskId = t.id " +
				"INNER JOIN PLANT p ON p.id = tp.plantId " +
				"WHERE tp.date = date('now') AND done = 0;";
		
	    c 	  = mDb.rawQuery(query, null);
		
	    if (convertResultToObject()) {
	    	return taskPlant;
		} else {
			return null;
		}
	}
	
	protected boolean convertResultToObject() {
		
		if (!c.moveToFirst()) {
			return false;
		}
		
		//get Task Info
		int    taskId 		   = c.getInt(0);
		String taskDescription = c.getString(1);
		Task task = new Task(taskId, taskDescription);
		
		//get Plant Info
		int	   plantId		   = c.getInt(2);
		String plantName	   = c.getString(3);
		Plant plant 		   = new Plant(plantId, plantName);
		
		
		taskPlant = new TaskPlant(plant, task);
		
		c.moveToNext();
		
		do {
			taskId = c.getInt(0);
			taskDescription = c.getString(1);
			plantId = c.getInt(2);
			plantName = c.getString(3);
			
			task  = new Task(taskId, taskDescription);
			plant = new Plant(plantId, plantName);
			
			taskPlant.addTaskPlantToQueue(new TaskPlant(plant, task));
			
		} while(c.moveToNext());
		
		c.close();
		
		return true;
	}
}
