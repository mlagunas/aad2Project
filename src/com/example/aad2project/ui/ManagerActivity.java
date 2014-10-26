package com.example.aad2project.ui;

import java.util.Locale;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.IBinder;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.aad2project.R;
import com.example.aad2project.services.WeatherService;
//import com.example.aad2project.services.WeatherService.MonBinder;

public class ManagerActivity extends ActionBarActivity implements
		ActionBar.TabListener {
	private final static String TAG = "TestRequette";

	SectionsPagerAdapter mSectionsPagerAdapter;
	ViewPager mViewPager;
	WeatherService mService;
	boolean mBound = false;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_manager);
		ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(CONNECTIVITY_SERVICE);
		NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
		// Check if there is internet on the phone. Yes-> Download new
		// information about the weather/ No-> Toast to prevent information are
		// not updated
		if (networkInfo.isConnected()) {
			Intent i = new Intent(ManagerActivity.this, WeatherService.class);
			Log.d(TAG,"Test0");
			startService(i);

		} else
			Toast.makeText(
					getApplicationContext(),
					"There is not internet connection, the data can't be updated.",
					Toast.LENGTH_LONG).show();

		// Set up the action bar.
		final ActionBar actionBar = getSupportActionBar();
		actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);

		// Create the adapter that will return a fragment for each of the three
		// primary sections of the activity.
		mSectionsPagerAdapter = new SectionsPagerAdapter(
				getSupportFragmentManager());

		// Set up the ViewPager with the sections adapter.
		mViewPager = (ViewPager) findViewById(R.id.pager);
		mViewPager.setAdapter(mSectionsPagerAdapter);

		// When swiping between different sections, select the corresponding
		// tab. We can also use ActionBar.Tab#select() to do this if we have
		// a reference to the Tab.
		mViewPager
				.setOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {
					@Override
					public void onPageSelected(int position) {
						actionBar.setSelectedNavigationItem(position);
					}
				});

		// For each of the sections in the app, add a tab to the action bar.
		for (int i = 0; i < mSectionsPagerAdapter.getCount(); i++) {
			// Create a tab with text corresponding to the page title defined by
			// the adapter. Also specify this Activity object, which implements
			// the TabListener interface, as the callback (listener) for when
			// this tab is selected.
			actionBar.addTab(actionBar.newTab()
					.setText(mSectionsPagerAdapter.getPageTitle(i))
					.setTabListener(this));
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.manager, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		switch (item.getItemId()) {
		// action with ID action_refresh was selected
		case R.id.log_out:
			Toast.makeText(this, "Log out selected", Toast.LENGTH_SHORT).show();

			logout();

			break;
		// action with ID action_settings was selected
		case R.id.action_settings:
			Toast.makeText(this, "Settings selected", Toast.LENGTH_SHORT)
					.show();

			Intent intent = new Intent(ManagerActivity.this,
					SettingsActivity.class);
			startActivity(intent);

			break;
		default:
			break;
		}
		return super.onOptionsItemSelected(item);
	}

	@Override
	public void onTabSelected(ActionBar.Tab tab,
			FragmentTransaction fragmentTransaction) {
		// When the given tab is selected, switch to the corresponding page in
		// the ViewPager.
		mViewPager.setCurrentItem(tab.getPosition());
	}

	@Override
	public void onTabUnselected(ActionBar.Tab tab,
			FragmentTransaction fragmentTransaction) {
	}

	@Override
	public void onTabReselected(ActionBar.Tab tab,
			FragmentTransaction fragmentTransaction) {
	}

	/**
	 * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
	 * one of the sections/tabs/pages.
	 */
	public class SectionsPagerAdapter extends FragmentPagerAdapter {

		public SectionsPagerAdapter(FragmentManager fm) {
			super(fm);
		}

		@Override
		public Fragment getItem(int position) {
			// getItem is called to instantiate the fragment for the given page.
			// Return a PlaceholderFragment (defined as a static inner class
			// below).
			switch (position) {
			case 0:
				return new TaskCalendarFragment();
			case 1:
				return new PlantManagerFragment();
			}
			return null;
		}

		@Override
		public int getCount() {
			// Show 2 total pages.
			return 2;
		}

		@Override
		public CharSequence getPageTitle(int position) {
			Locale l = Locale.getDefault();
			switch (position) {
			case 0:
				return getString(R.string.title_section2).toUpperCase(l);
			case 1:
				return getString(R.string.title_section1).toUpperCase(l);
			}
			return null;
		}
	}

	public void logout() {
		SharedPreferences sharedPreferences = getSharedPreferences(
				MainActivity.MyPREFERENCES, Context.MODE_PRIVATE);
		Editor editor = sharedPreferences.edit();
		editor.clear();
		editor.commit();
		moveTaskToBack(true);
		Intent i = new Intent(ManagerActivity.this, MainActivity.class);
		startActivity(i);
		finish();

	}

//	private ServiceConnection mConnection = new ServiceConnection() {
//
//		@Override
//		public void onServiceConnected(ComponentName className, IBinder service) {
//
//			MonBinder binder = (MonBinder) service;
//			mService = binder.getService();
//			mBound = true;
//		}
//
//		@Override
//		public void onServiceDisconnected(ComponentName arg0) {
//			mBound = false;
//		}
//	};

}
