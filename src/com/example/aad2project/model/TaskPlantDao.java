package com.example.aad2project.model;

import android.content.Context;
import android.database.Cursor;

public class TaskPlantDao extends DaoBase {

	public TaskPlantDao (Context pContext) {
		super(pContext);
	}
	
	protected boolean convertResultToObject() { return true;}
}
