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
			long id = db.insertOrThrow(
					MyContentProvider.CONTENT_PROVIDER_TABLE_NAME, null,
					values);

			if (id == -1) {
				throw new RuntimeException(String.format(
						"%s : Failed to insert [%s] for unknown reasons.",
						"TutosAndroidProvider", values, uri));
			} else {
				return ContentUris.withAppendedId(uri, id);
			}

		} finally {
			db.close();
		}
	}

	/**
	 * update a value
	 */
	@Override
	public int update(Uri uri, ContentValues values, String selection,
			String[] selectionArgs) {
		long id = getId(uri);
		SQLiteDatabase db = dbHelper.getWritableDatabase();

		try {
			if (id < 0)
				return db.update(
						MyContentProvider.CONTENT_PROVIDER_TABLE_NAME,
						values, selection, selectionArgs);
			else
				return db.update(
						MyContentProvider.CONTENT_PROVIDER_TABLE_NAME,
						values, Account.ACCOUNT_ID + "=" + id, null);
		} finally {
			db.close();
		}
	}

	@Override
	public int delete(Uri uri, String selection, String[] selectionArgs) {
		long id = getId(uri);
		SQLiteDatabase db = dbHelper.getWritableDatabase();
		try {
			if (id < 0)
				return db.delete(
						MyContentProvider.CONTENT_PROVIDER_TABLE_NAME,
						selection, selectionArgs);
			else
				return db.delete(
						MyContentProvider.CONTENT_PROVIDER_TABLE_NAME,
						Account.ACCOUNT_ID + "=" + id, selectionArgs);
		} finally {
			db.close();
		}
	}

	@Override
	public Cursor query(Uri uri, String[] projection, String selection,
			String[] selectionArgs, String sortOrder) {
		long id = getId(uri);
		SQLiteDatabase db = dbHelper.getReadableDatabase();
		if (id < 0) {
			return db
					.query(MyContentProvider.CONTENT_PROVIDER_TABLE_NAME,
							projection, selection, selectionArgs, null, null,
							sortOrder);
		} else {
			return db.query(MyContentProvider.CONTENT_PROVIDER_TABLE_NAME,
					projection, Account.ACCOUNT_ID + "=" + id, null, null, null,
					null);
		}
	}

}
