package com.example.aad2project.model;

import android.content.Context;
import android.database.Cursor;

public class PlantDao extends DaoBase {
	
	public PlantDao (Context pContext) {
		super(pContext);
	}
 	
	public void addPlant(Plant plant) {
		
	}
	
	protected boolean convertResultToObject() { return true;}
}
