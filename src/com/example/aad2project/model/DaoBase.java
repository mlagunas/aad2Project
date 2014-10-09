package com.example.aad2project.model;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

public abstract class DaoBase {
	  // First base version
	  // Update this attribute if version change
	  protected final static int VERSION = 1;
	  // database name
	  protected final static String NOM = "database.db";
	  
	  protected SQLiteDatabase mDb = null;
	  protected DatabaseHandler mHandler = null;

	  public DaoBase(Context pContext) {
		    this.mHandler = new DatabaseHandler(pContext, NOM, null, VERSION);
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

}
