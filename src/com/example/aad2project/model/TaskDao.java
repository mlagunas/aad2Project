package com.example.aad2project.model;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

public class TaskDao extends DaoBase {
	
	public static final String TABLE_NAME  = "Task";
	public static final String KEY 		   = "id";
	public static final String DESCRIPTION = "description";
	
	private Task task;

	
	public TaskDao(Context pContext) {
		super(pContext);
	}
	
	public boolean addTask(Task newTask) {
		
		ContentValues value = new ContentValues();
		long result 		= -1;
		
		value.put(TaskDao.DESCRIPTION, newTask.getDescription());
		
		result = mDb.insert(TaskDao.TABLE_NAME, null, value);
		
		return result != -1;
	}
	
	public Task getAllTask() {
		query = " SELECT " + TaskDao.KEY + ", " + TaskDao.DESCRIPTION +
				" FROM   " + TaskDao.TABLE_NAME;
		
		c 	  = mDb.rawQuery(query, null);
		
		if (convertResultToObject(c)) {
			return task;
		}
		
		return null;
	}
	
	public Task getTask(int id) {
		
		query = " SELECT " + TaskDao.KEY + ", " + TaskDao.DESCRIPTION +
				" FROM   " + TaskDao.TABLE_NAME +
				" WHERE  id = ?";
		
		args = new String[1];
		
		args[0] = "" + id + "";
		
		c = mDb.rawQuery(query, args);
		
		if (convertResultToObject(c)) {
			return task;
		}
		
		return null;
	}
	
	protected boolean convertResultToObject(Cursor c) {
		if (!c.moveToFirst()) {
			return false;
		}
		
		int id = c.getInt(0);
		String description = c.getString(1);
		task = new Task(id, description);
		c.moveToNext();
		
		do {
			id 			= c.getInt(0);
			description = c.getString(1);
			task.addTaskToQueue(new Task(id, description));
		} while(c.moveToNext());
		
		c.close();
		
		return true;
	}
}
