/**
 * Content Provider class, which handle all the content provider stuff
 * @author Alexandre
 *
 */

package com.example.aad2project.model;

import com.example.aad2project.model.SharedInformation.Account;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.net.Uri;
import android.util.Log;

public class MyContentProvider extends ContentProvider {

	public static final Uri CONTENT_URI = Uri
			.parse("content://com.example.aad2project.model.MyContentProvider");
	public static final String CONTENT_PROVIDER_DB_NAME = "account.db";
	public static final int CONTENT_PROVIDER_DB_VERSION = 1;
	public static final String CONTENT_PROVIDER_TABLE_NAME = "account";
	public static final String CONTENT_PROVIDER_MIME = "vnd.android.cursor.item/vnd.com.example.aad2project.model.account";

	private static class DatabaseHelper extends SQLiteOpenHelper {

		DatabaseHelper(Context context) {
			super(context, MyContentProvider.CONTENT_PROVIDER_DB_NAME, null,
					MyContentProvider.CONTENT_PROVIDER_DB_VERSION);
		}

		// Creation of the table where the data will be stored
		@Override
		public void onCreate(SQLiteDatabase db) {
			db.execSQL("CREATE TABLE "
					+ MyContentProvider.CONTENT_PROVIDER_TABLE_NAME + " ("
					+ Account.ACCOUNT_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
					+ Account.ACCOUNT_EMAIL + " VARCHAR(255)," + Account.ACCOUNT_PASSWORD
					+ " VARCHAR(255)" + ");");
		}

		@Override
		public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
			db.execSQL("DROP TABLE IF EXISTS "
					+ MyContentProvider.CONTENT_PROVIDER_TABLE_NAME);
			onCreate(db);
		}
	}

	private DatabaseHelper dbHelper;

	@Override
	public boolean onCreate() {
		dbHelper = new DatabaseHelper(getContext());
		return true;
	}

	@Override
	public String getType(Uri uri) {
		return MyContentProvider.CONTENT_PROVIDER_MIME;
	}

	private long getId(Uri uri) {
		String lastPathSegment = uri.getLastPathSegment();
		if (lastPathSegment != null) {
			try {
				return Long.parseLong(lastPathSegment);
			} catch (NumberFormatException e) {
				Log.e("MyContentProvider", "Number Format Exception : " + e);
			}
		}
		return -1;
	}

	/**
	 * Insert a value
	 */
	@Override
	public Uri insert(Uri uri, ContentValues values) {
		SQLiteDatabase db = dbHelper.getWritableDatabase();
		try {
			// Create a content with the data we want to add, here "values"
			long id = db.insertOrThrow(MyContentProvider.CONTENT_PROVIDER_TABLE_NAME, null,
					values);

			// Manage of the non existence of the table
			if (id == -1) {
				throw new RuntimeException(String.format(
						"%s : Failed to insert [%s] for unknown reasons.",
						"TutosAndroidProvider", values, uri));
			} else {
				// Insert the value
				return ContentUris.withAppendedId(uri, id);
			}

		} finally {
			// Close the database
			db.close();
		}
	}

	/**
	 * Update a value
	 */
	@Override
	public int update(Uri uri, ContentValues values, String selection,
			String[] selectionArgs) {
		long id = getId(uri);
		SQLiteDatabase db = dbHelper.getWritableDatabase();

		// Update a content with the data we want to switch to, here "values"
		try {
			if (id < 0)
				// If the table is empty
				return db.update(MyContentProvider.CONTENT_PROVIDER_TABLE_NAME,
						values, selection, selectionArgs);
			else
				// If the table has already been implemented with data
				return db.update(MyContentProvider.CONTENT_PROVIDER_TABLE_NAME,
						values, Account.ACCOUNT_ID + "=" + id, null);
		} finally {
			// Close the database
			db.close();
		}
	}

	/**
	 * Delete a value
	 */
	@Override
	public int delete(Uri uri, String selection, String[] selectionArgs) {
		long id = getId(uri);
		SQLiteDatabase db = dbHelper.getWritableDatabase();
		
		// Delete a content at a selected position
		try {
			if (id < 0)
				// If the table is empty
				return db.delete(MyContentProvider.CONTENT_PROVIDER_TABLE_NAME,
						selection, selectionArgs);
			else
				// If the table has already been implemented with data
				return db.delete(MyContentProvider.CONTENT_PROVIDER_TABLE_NAME,
						Account.ACCOUNT_ID + "=" + id, selectionArgs);
		} finally {
			// Close the database
			db.close();
		}
	}

	/**
	 * Position of the cursor
	 */
	@Override
	public Cursor query(Uri uri, String[] projection, String selection,
			String[] selectionArgs, String sortOrder) {
		long id = getId(uri);
		SQLiteDatabase db = dbHelper.getReadableDatabase();
		
		if (id < 0) {
			return db.query(MyContentProvider.CONTENT_PROVIDER_TABLE_NAME,
							projection, selection, selectionArgs, null, null,
							sortOrder);
		} else {
			return db.query(MyContentProvider.CONTENT_PROVIDER_TABLE_NAME,
					projection, Account.ACCOUNT_ID + "=" + id, null, null, null,
					null);
		}
	}

}
