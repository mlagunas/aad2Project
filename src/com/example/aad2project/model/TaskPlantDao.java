package com.example.aad2project.model;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import com.example.aad2project.ui.TaskCalendarFragment;

import android.content.Context;
import android.database.Cursor;
import android.util.Log;

public class TaskPlantDao extends DaoBase {
	
	public TaskPlantDao (Context pContext) {
		super(pContext);
		super.open();
	}
	
	public void deleteAllTaskPlant(){
		super.mDb.execSQL("DELETE FROM TaskPlant");
	}
	
	public void createTaskPlant(Plant p, Task t, Date d){
		//Log.d("all task", this.getAllTaskPlant().toString());
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
	        p.setWeatherId(null);

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
		         tp.setDate(new Date(c.getInt(2)));
		         tp.setDone(c.getInt(3));
		         aux.add(tp);
		     } while(c.moveToNext());
		}
		
		return aux;
	}
	
	protected boolean convertResultToObject() { return true;}
}
