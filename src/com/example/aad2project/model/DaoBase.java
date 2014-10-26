package com.example.aad2project.model;

import com.example.aad2project.ui.TaskCalendarFragment;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

public abstract class DaoBase {
	  // First base version
	  // Update this attribute if version change
	  protected final static int VERSION = 1;
	  // database name
	  protected final static String NAME = "database_0.db";
	  
	  protected SQLiteDatabase mDb = null;
	  protected DatabaseHandler mHandler = null;
	  	  
	  protected String query;
	  protected String[] args;
	  protected Cursor c;
  
	  public DaoBase(Context pContext) {
		    this.mHandler = new DatabaseHandler(pContext, NAME, null, VERSION);
		  }
		    
	  public SQLiteDatabase open() {
	    mDb = mHandler.getWritableDatabase();
	    return mDb;
	  }
	    
	  public void close() {
	    mDb.close();
	  }
	    
	  public SQLiteDatabase getDb() {
	    return mDb;
	  }
 	  	 	  
	  protected abstract boolean convertResultToObject ();
}
