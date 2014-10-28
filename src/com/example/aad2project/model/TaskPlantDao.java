package com.example.aad2project.model;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.support.v4.content.LocalBroadcastManager;

import com.example.aad2project.object.Plant;
import com.example.aad2project.object.Task;
import com.example.aad2project.object.TaskPlant;

public class TaskPlantDao extends DaoBase {

	private TaskPlant taskPlant;
	private Context context;

	public TaskPlantDao(Context pContext) {
		super(pContext);
		super.open();
		context = pContext;
	}

	public void deleteAllTaskPlant() {
		super.mDb.execSQL("DELETE FROM taskPlant");
		notifyLoader();
	}

	public void createTaskPlant(Plant p, Task t, long d) {
		super.mDb.execSQL("INSERT INTO taskPlant (taskId,plantId,date,done)"
				+ "VALUES (" + p.getId() + "," + t.getId() + "," + d + ",0);");
		notifyLoader();
	}

	public void deleteTaskPlant(Integer id) {
		super.mDb.execSQL("DELETE FROM TaskPlant WHERE plantId = " + id);
		notifyLoader();
	}

	private Plant createPlant(Cursor c, int idP) {
		Plant p = new Plant();
		if (c.moveToFirst()) {

			p.setId(c.getInt(0));
			p.setName(c.getString(1));
			p.setDescription(c.getString(2));
			p.setTimeToGrow(c.getInt(3));
			p.setNumber(c.getInt(4));
			p.setWeatherId(c.getInt(5));

		}
		return p;
	}

	private Task createTask(Cursor c, int idT) {
		Task t = new Task();
		if (c.moveToFirst()) {

			t.setId(c.getInt(0));
			t.setDescription(c.getString(1));

		}
		return t;
	}

	public List<TaskPlant> getAllTaskPlant() {
		ArrayList<TaskPlant> aux = new ArrayList<TaskPlant>();
		Cursor c = super.mDb.rawQuery("SELECT * FROM TaskPlant", null);

		if (c.moveToFirst()) {
			do {
				TaskPlant tp = new TaskPlant();
				Integer idP = c.getInt(0);
				Integer idT = c.getInt(1);

				tp.setTask(createTask(
						super.mDb.rawQuery("SELECT * FROM Task WHERE id ="
								+ idT, null), idT));
				tp.setPlant(createPlant(
						super.mDb.rawQuery("SELECT * FROM Plant WHERE id ="
								+ idP, null), idP));

				tp.setIdTask(idP);
				tp.setIdPlant(idT);
				Date test = new Date(c.getLong(2));
				tp.setDate(test);
				tp.setDone(c.getInt(3));
				aux.add(tp);
			} while (c.moveToNext());
		}

		return aux;
	}

	public TaskPlant getCurrentDayTaskPlant() {

		query = "SELECT t.id, t.description, p.id, p.name, "
				+ "tp.date, tp.done "
				+ "FROM Task t INNER JOIN TaskPlant tp ON tp.taskId = t.id "
				+ "INNER JOIN PLANT p ON p.id = tp.plantId "
				+ "WHERE date(tp.date) = date('now') AND done = 0;";

		c = mDb.rawQuery(query, null);

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

		// get Task Info
		int taskId = c.getInt(0);
		String taskDescription = c.getString(1);
		Task task = new Task();
		task.setId(taskId);
		task.setDescription(taskDescription);
		// get Plant Info
		int plantId = c.getInt(2);
		String plantName = c.getString(3);
		Plant plant = new Plant(plantId, plantName);

		taskPlant = new TaskPlant(plant, task);

		c.moveToNext();

		do {
			taskId = c.getInt(0);
			taskDescription = c.getString(1);
			plantId = c.getInt(2);
			plantName = c.getString(3);

			task = new Task();
			task.setId(taskId);
			task.setDescription(taskDescription);
			plant = new Plant(plantId, plantName);

			taskPlant.addTaskPlantToQueue(new TaskPlant(plant, task));

		} while (c.moveToNext());

		c.close();

		return true;
	}

	private void notifyLoader() {
		Intent intent = new Intent("taskplant-database-changed");
		LocalBroadcastManager.getInstance(context).sendBroadcast(intent);
	}
}
