package com.example.aad2project.model;

import java.net.MalformedURLException;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.microsoft.windowsazure.mobileservices.MobileServiceClient;

public abstract class DaoBase {
	
	// First base version
	// Update this attribute if version change
	protected final static int VERSION = 1;
	
	// database name
	protected final static String NAME = "test_conn_3.db";
	
	protected SQLiteDatabase mDb = null;
	protected DatabaseHandler mHandler = null;
	  	  
	protected String query;
	protected String[] args;
	protected Cursor c;
	
	protected MobileServiceClient mClient;
  
	  public DaoBase(Context pContext) {
	    this.mHandler = new DatabaseHandler(pContext, NAME, null, VERSION);
	    try {
			mClient = new MobileServiceClient(
					"https://greenhub.azure-mobile.net/",
					"TnxUiYgqqbPovNDQHROYaqrALQQUMw32",
					pContext);
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	  }
	  
	  /**
	   * Open the database to read and write   
	   * @return
	   */
	  public SQLiteDatabase open() {
	    mDb = mHandler.getWritableDatabase();
	    return mDb;
	  }
	  
	  /**
	   * Close the database
	   */
	  public void close() {
	    mDb.close();
	  }
	   /**
	    * This method returns the database
	    * @return
	    */
	  public SQLiteDatabase getDb() {
	    return mDb;
	  }
 	  
	  /**
	   * Clean all the tables in the database
	   */
	  public void clean(){
		mDb.execSQL("DROP TABLE existingplants");
		mDb.execSQL("DROP TABLE weather");
		mDb.execSQL("DROP TABLE task");
		mDb.execSQL("DROP TABLE taskplant");
		mDb.execSQL("DROP TABLE weathercalendar");
		mDb.execSQL("DROP TABLE plant");
		  		
		mDb.execSQL(mHandler.WEATHER_CREATE_TABLE);
		mDb.execSQL(mHandler.PLANT_CREATE_TABLE);
		mDb.execSQL(mHandler.WEATHER_CALENDAR_CREATE_TABLE);
		mDb.execSQL(mHandler.TASK_CREATE_TABLE);
		mDb.execSQL(mHandler.TASK_PLANT_CREATE_TABLE);
		mDb.execSQL(mHandler.ALL_EXISTING_PLANTS);
	  }
	  
	  protected abstract boolean convertResultToObject ();

}
