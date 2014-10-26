package com.example.aad2project.model;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import com.example.aad2project.ui.TaskCalendarFragment;

import android.content.Context;
import android.database.Cursor;
import android.util.Log;

public class TaskPlantDao extends DaoBase {

	private TaskPlant taskPlant;

	
	public TaskPlantDao (Context pContext) {
		super(pContext);
		super.open();
	}
	
	public void deleteAllTaskPlant(){
		super.mDb.execSQL("DELETE FROM TaskPlant");
	}
	
	public void createTaskPlant(Plant p, Task t, long d){
		super.mDb.execSQL("INSERT INTO TaskPlant (taskId,plantId,date,done)" +
				"VALUES ("+p.getId()+","+t.getId()+","+d+",0);");
		Log.d("all taskPlant", this.getAllTaskPlant().toString());
	}
	
	public void deleteTaskPlant(Integer id){
		super.mDb.execSQL("DELETE FROM TaskPlant WHERE plantId = "+id);
		Log.d("id", id.toString());
		Log.d("all taskPlant", this.getAllTaskPlant().toString());

	}
	
	private Plant createPlant(Cursor c, int idP){
		Plant p = new Plant();
		if(c.moveToFirst()){
			
			p.setId(c.getInt(0));
	        p.setName(c.getString(1));
	        p.setDescription(c.getString(2));
	        p.setTimeToGrow(c.getInt(3));
	        p.setNumber(c.getInt(4));
	        p.setWeatherId(c.getInt(5));

		}
        return p;
	}
	
	private Task createTask(Cursor c, int idT){
		Task t = new Task();
		if(c.moveToFirst()){
			
				t.setId(c.getInt(0));
				t.setDescription(c.getString(1));
				
		}
		return t;
	}
	
	public List<TaskPlant> getAllTaskPlant(){
		ArrayList<TaskPlant> aux = new ArrayList<TaskPlant>();
		Cursor c = super.mDb.rawQuery("SELECT * FROM TaskPlant",null);
		
		if (c.moveToFirst()) {
		     do{
		         TaskPlant tp = new TaskPlant();
		         Integer idP = c.getInt(0);
		         Integer idT = c.getInt(1);
		         Log.d("ALL PLANT ID"," "+idP);
		 		 Log.d("ALL TASK ID"," "+idT);  
		         
		 		 tp.setTask(createTask(super.mDb.rawQuery("SELECT * FROM Task WHERE id ="+ idT, null),idT));
		         tp.setPlant(createPlant(super.mDb.rawQuery("SELECT * FROM Plant WHERE id ="+ idP, null),idP));
		         
		         tp.setIdTask(idP);		         
		         tp.setIdPlant(idT);
		         Log.d("FECHA"," "+c.getInt(2));
		         Date test = new Date(c.getLong(2));
		         Log.d("REALDATE",test.toString());
		         tp.setDate(test);
		         tp.setDone(c.getInt(3));
		         aux.add(tp);
		     } while(c.moveToNext());
		}
		
		return aux;
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
		Task task = new Task();
		task.setId(taskId);
		task.setDescription(taskDescription);
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
			
			task  = new Task();
			task.setId(taskId);
			task.setDescription(taskDescription);
			plant = new Plant(plantId, plantName);
			
			taskPlant.addTaskPlantToQueue(new TaskPlant(plant, task));
			
		} while(c.moveToNext());
		
		c.close();
		
		return true;
	}
}
