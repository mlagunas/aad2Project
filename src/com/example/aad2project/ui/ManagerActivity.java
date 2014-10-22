package com.example.aad2project.ui;

import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import android.app.AlarmManager;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.TaskStackBuilder;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.example.aad2project.R;
import com.example.aad2project.model.MyReceiver;
import com.example.aad2project.ui.PlantManagerFragment.OnPlantManagerFragmentInteractionListener;
import com.example.aad2project.ui.TaskCalendarFragment.OnTaskCalendarFragmentInteractionListener;

public class ManagerActivity extends ActionBarActivity implements
		ActionBar.TabListener, OnPlantManagerFragmentInteractionListener,
		OnTaskCalendarFragmentInteractionListener {

	SectionsPagerAdapter mSectionsPagerAdapter;
	ViewPager mViewPager;
	private FrameLayout container;

	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_manager);
		
		startAlarm();
		
		container = (FrameLayout) findViewById(R.id.fragment_container);

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
		
		registerReceiver(broadcastReceiver, new IntentFilter("TEST"));
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
		Intent i = new Intent(this, MainActivity.class);
		startActivity(i);
		finish();

	}

	@Override
	public void onPlantManagerFragmentInteraction(int id) {
		if (container != null) {
			// Tablet behavior (Add Fragment to the side of the screen)
			PlantInformationFragment loginFragment = PlantInformationFragment
					.newInstance(id);

			FragmentTransaction fragmentTransaction = getSupportFragmentManager()
					.beginTransaction();
			fragmentTransaction.replace(R.id.fragment_container, loginFragment);
			fragmentTransaction.commit();
		} else {
			// Phone behavior (Start new activity)
			Intent intent = new Intent(this, PlantInformationActivity.class);
			intent.putExtra("id", id);
			startActivity(intent);
		}
	}

	@Override
	public void onTaskCalendarFragmentInteraction(int id) {
		if (container != null) {
			// Tablet behavior (Add Fragment to the side of the screen)
			TaskInformationFragment fragment = TaskInformationFragment
					.newInstance(id);

			FragmentManager manager = getSupportFragmentManager();
			FragmentTransaction transaction = manager.beginTransaction();
			transaction.replace(R.id.fragment_container, fragment);
			transaction.commit();
		} else {
			// Phone behavior (Start new activity)
			Intent intent = new Intent(this, TaskInformationActivity.class);
			intent.putExtra("TASK_ID", id);
			startActivity(intent);
		}
	}

	/**
	 * Display the notification with the informations provided
	 * @param title : title of the notification
	 * @param text : text of the notification
	 * @param mId : id of the notification (can handle more than one notification)
	 */
	public void notifications(String title, String text, int mId){
		
		NotificationCompat.Builder mBuilder =
		        new NotificationCompat.Builder(this)
		        .setSmallIcon(R.drawable.notification_icon)
		        .setContentTitle(title)
		        .setContentText(text);
		// Creates an explicit intent for an Activity in your app
		Intent resultIntent = new Intent(this, ManagerActivity.class);

		// The stack builder object will contain an artificial back stack for the
		// started Activity.
		// This ensures that navigating backward from the Activity leads out of
		// your application to the Home screen.
		TaskStackBuilder stackBuilder = TaskStackBuilder.create(this);
		// Adds the back stack for the Intent (but not the Intent itself)
		stackBuilder.addParentStack(ManagerActivity.class);
		// Adds the Intent that starts the Activity to the top of the stack
		stackBuilder.addNextIntent(resultIntent);
		PendingIntent resultPendingIntent =
		        stackBuilder.getPendingIntent(
		            0,
		            PendingIntent.FLAG_UPDATE_CURRENT
		        );
		mBuilder.setContentIntent(resultPendingIntent);
		NotificationManager mNotificationManager =
		    (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
		// mId allows you to update the notification later on.
		mNotificationManager.notify(mId, mBuilder.build());
		
		Log.i("TAG", "notification");
	}
	
	public void startAlarm() {
	    // Prepare intent to launch notification
	    Intent intent = new Intent(this, MyReceiver.class);
	    PendingIntent pendingIntent = PendingIntent.getBroadcast(this, 0, intent, 0);

	    Date dat  = new Date();//initializes to now
	    Calendar cal_alarm = Calendar.getInstance();
	    Calendar cal_now = Calendar.getInstance();
	    cal_now.setTime(dat);
	    cal_alarm.setTime(dat);
	    cal_alarm.set(Calendar.HOUR_OF_DAY,18);//set the alarm time
	    cal_alarm.set(Calendar.MINUTE, 0);
	    cal_alarm.set(Calendar.SECOND,0);
	    if(cal_alarm.before(cal_now)){//if its in the past increment
	        cal_alarm.add(Calendar.DATE,1);
	    }
	    
	    // The alarm manager is an android system service
	    AlarmManager am = (AlarmManager) this.getSystemService(Context.ALARM_SERVICE);
	    am.set(AlarmManager.RTC_WAKEUP, cal_alarm.getTimeInMillis(), pendingIntent);
	    
	    Log.i("TAG", "Start timer...");
	}
	
	BroadcastReceiver broadcastReceiver = new BroadcastReceiver() {
	    @Override
	    public void onReceive(Context context, Intent intent) {
	    	String title = "Put a title";
	    	String text = "Put a text";
	        notifications(title, text, 0);
	        Log.i("TAG", "Receive");
	    }
	};

	@Override
	protected void onDestroy() {
	    super.onDestroy();
	    unregisterReceiver(broadcastReceiver);
	}
}

