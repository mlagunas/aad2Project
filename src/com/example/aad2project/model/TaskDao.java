package com.example.aad2project.model;

import java.sql.Date;
import java.util.ArrayList;

import com.example.aad2project.ui.TaskCalendarFragment;

import android.app.Fragment;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.util.Log;

public class TaskDao extends DaoBase {
	
	public static final String TABLE_NAME  = "Task";
	public static final String KEY 		   = "id";
	public static final String DESCRIPTION = "description";
	
	private Task task;

	
	public TaskDao(Context pContext) {
		super(pContext);
		super.open();
	}
	
	public void deleteAllTask(){
		super.mDb.execSQL("DELETE FROM Task");
	}
	
	/*public boolean addTask(Task newTask) {
		
		ContentValues value = new ContentValues();
		long result 		= -1;
		
		value.put(TaskDao.DESCRIPTION, newTask.getDescription());
		
		result = mDb.insert(TaskDao.TABLE_NAME, null, value);
		
		return result != -1;
	}*/
	
	public void addTask(Task newTask){
		super.mDb.execSQL("INSERT INTO Task(description) " +
				"VALUES ('"+newTask.getDescription()+"');");
		Log.d("ALL TASK", getAllTask().toString());

	}
	
	public void addTask(String d){
		super.mDb.execSQL("INSERT INTO Task (description)" +
				"VALUES ('"+d+"');");
		Log.d("ALL TASK", getAllTask().toString());

	}
	
	/*public Task getAllTask() {
		query = " SELECT " + TaskDao.KEY + ", " + TaskDao.DESCRIPTION +
				" FROM   " + TaskDao.TABLE_NAME;
		
		c 	  = mDb.rawQuery(query, null);
		
		if (convertResultToObject()) {
			return task;
		}
		
		return null;
	}*/	
	
	public ArrayList<Task> getAllTask(){
		ArrayList<Task> aux = new ArrayList<Task>();
		Cursor c = super.mDb.rawQuery("SELECT * FROM Task",null);
		
		if (c.moveToFirst()) {
		     do {
		    	 Task t = new Task();
		    	 t.setId(c.getInt(0));
		    	 t.setDescription(c.getString(1));
		    	 aux.add(t);
		     } while(c.moveToNext());
		}
		return aux;
	}
	
	public Task getTask(int id) {
		
		query = " SELECT " + TaskDao.KEY + ", " + TaskDao.DESCRIPTION +
				" FROM   " + TaskDao.TABLE_NAME +
				" WHERE  id = ?";
		
		args = new String[1];
		
		args[0] = "" + id + "";
		
		c = mDb.rawQuery(query, args);
		
		if (convertResultToObject()) {
			return task;
		}
		
		return null;
	}
	
	protected boolean convertResultToObject() {
		if (!c.moveToFirst()) {
			return false;
		}
		
		int id = c.getInt(0);
		String description = c.getString(1);
		task = new Task();
		task.setId(id);
		task.setDescription(description);
		c.moveToNext();
		
		do {
			id 			= c.getInt(0);
			description = c.getString(1);
			Task aux = new Task();
			aux.setId(id);
			aux.setDescription(description);
			task.addTaskToQueue(aux);
		} while(c.moveToNext());
		
		c.close();
		
		return true;
	}
}
