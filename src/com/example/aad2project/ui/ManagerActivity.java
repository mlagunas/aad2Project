package com.example.aad2project.ui;

import java.net.MalformedURLException;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import android.annotation.SuppressLint;
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
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.TaskStackBuilder;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ProgressBar;

import com.example.aad2project.R;
import com.example.aad2project.model.ExistingPlantDao;
import com.example.aad2project.model.PlantDao;
import com.example.aad2project.model.TaskPlantDao;
import com.example.aad2project.model.WeatherDao;
import com.example.aad2project.object.ExistingPlant;
import com.example.aad2project.object.Green;
import com.example.aad2project.object.Plant;
import com.example.aad2project.object.TaskPlant;
import com.example.aad2project.object.Weather;
import com.example.aad2project.receiver.MyReceiver;
import com.example.aad2project.services.SensorService;
import com.example.aad2project.ui.LongClickDialogFragment.LongClickDialogListener;
import com.example.aad2project.ui.PlantManagerFragment.OnPlantManagerFragmentInteractionListener;
import com.example.aad2project.ui.TaskCalendarFragment.OnTaskCalendarFragmentInteractionListener;
import com.microsoft.windowsazure.mobileservices.MobileServiceClient;
import com.microsoft.windowsazure.mobileservices.MobileServiceTable;
import com.microsoft.windowsazure.mobileservices.ServiceFilterResponse;
import com.microsoft.windowsazure.mobileservices.TableQueryCallback;

@SuppressLint("NewApi")
public class ManagerActivity extends ActionBarActivity implements
		ActionBar.TabListener, OnPlantManagerFragmentInteractionListener,
		OnTaskCalendarFragmentInteractionListener, LongClickDialogListener {
	
	private AlarmManager alarmMgr;
	private PendingIntent alarmIntent;
	

	/**
	 * Android UI objects
	 */
	public SectionsPagerAdapter mSectionsPagerAdapter;
	public ViewPager mViewPager;
	private FrameLayout container;
	private Context context;
	private ProgressBar progressBar; 

	/**
	 * Model objects, they let us to manage the different database tables
	 */
	private TaskPlantDao tpDAO;
	private TaskPlant tp;

	/**
	 * Mobile Service Client reference
	 */
	private MobileServiceClient mClient;

	/**
	 * Objects to connect with the Windows Azure backend
	 */
	private MobileServiceTable<ExistingPlant> tableEp;
	private MobileServiceTable<Weather> tableW;
	private MobileServiceTable<Plant> tableP;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_manager);
		context = this;

        progressBar = (ProgressBar) findViewById(R.id.progressBar);

        try {
			mClient = new MobileServiceClient(
					"https://greenhub.azure-mobile.net/",
					"TnxUiYgqqbPovNDQHROYaqrALQQUMw32",
					this);
			
			progressBar = (ProgressBar) findViewById(R.id.progressBar);
			progressBar.setVisibility(View.VISIBLE);
	        
	        tableEp = mClient.getTable(ExistingPlant.class);
	        tableW = mClient.getTable(Weather.class);
	        tableP = mClient.getTable(Plant.class);
	        
			tableEp.execute(new TableQueryCallback<ExistingPlant>() {
				@Override
				public void onCompleted(List<ExistingPlant> result, int count,
	                    Exception exception, ServiceFilterResponse response) {
	                    if (exception == null) {
                        	ExistingPlantDao p = new ExistingPlantDao(context);
	                    	p.clean();
	                    	for (ExistingPlant item : result) {
	                        	p.addExistingPlant(item);
	                        }
	                    	downloadPlant();
	                    }
	                    else{
	                    	Log.d("TAG",exception.getMessage());
	                    }
	                }
	            });
        } catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
	
	/**
	 * Download the tables of the database stored on windows azure
	 */
	private void downloadWeather(){
		tableW.execute(new TableQueryCallback<Weather>(){
			@Override
			public void onCompleted(List<Weather> result, int count,
                    Exception exception, ServiceFilterResponse response) {
                    if (exception == null) {
                        for (Weather item : result) {
                        	WeatherDao w = new WeatherDao(context);
                        	w.add(item);
                        }                        
                    }
                    else{
                    	Log.d("TAG",exception.getMessage());
                    }
                    start();
                }
		});
	}
	
	/**
	 * Download from the cloud all the plants the user have on his garden
	 */
	private void downloadPlant(){
		tableP.execute(new TableQueryCallback<Plant>(){
			@Override
			public void onCompleted(List<Plant> result, int count,
                    Exception exception, ServiceFilterResponse response){
				// TODO Auto-generated method stub
				if (exception == null) {
					for (Plant p : result) {
						Log.d("ID"," "+p.getExisitingId());
                    	PlantDao pd = new PlantDao(context);
                    	Green g = pd.plantToGreen(p);
                    	pd.addPlant(g, false);
					}
				}
                else{
                	Log.d("TAG",exception.getMessage());
                }
                downloadWeather();
			}	
		});
	}
	
	
	
	/**
	 * Inicialize the action bar
	 * @param actionBar
	 */
	private void initActionBar(ActionBar actionBar){
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
			// Toast.makeText(this, "Log out selected",
			// Toast.LENGTH_SHORT).show();

			logout();

			break;
		// action with ID action_settings was selected
		case R.id.action_settings:
			// Toast.makeText(this, "Settings selected", Toast.LENGTH_SHORT)
			// .show();

			Intent intent = new Intent(ManagerActivity.this,
					SettingsActivity.class);
			startActivity(intent);
			break;

		case R.id.action_about:
			Intent intent2 = new Intent(ManagerActivity.this,
					AboutActivity.class);
			startActivity(intent2);
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

	/**
	 * Initialize the fragments and the application when the database is downloaded
	 */
	private void start(){

        container = (FrameLayout) findViewById(R.id.fragment_container);
        progressBar.setVisibility(View.GONE);
        
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
		// mViewPager.setOffscreenPageLimit(1);

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
		initActionBar(actionBar);
		
		// For each of the sections in the app, add a tab to the action bar.
		
		registerReceiver(broadcastReceiver, new IntentFilter("TEST"));
		scheduleSensorDataChecks();

	}
	
	@Override
	public void onTabReselected(ActionBar.Tab tab,
			FragmentTransaction fragmentTransaction) {
	}

	/**
	 * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
	 * one of the sections/tabs/pages.
	 */
	public class SectionsPagerAdapter extends FragmentStatePagerAdapter {

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

	/**
	 * Stop the users session and displays the login screen
	 */
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
	
	public void scheduleSensorDataChecks(){
		Log.d("TAG","AlarmManager set");
		alarmMgr = (AlarmManager) this.getSystemService(Context.ALARM_SERVICE);
		Intent intent = new Intent(this, SensorService.class);
		alarmIntent = PendingIntent.getService(this, 0, intent, 0);

		alarmMgr.setInexactRepeating(AlarmManager.RTC_WAKEUP, System.currentTimeMillis(),
		        60*1000, alarmIntent);
	}

	@Override
	public void onPlantManagerFragmentInteraction(int id, boolean upperGroup) {
		if (container != null) {
			// Tablet behavior (Add Fragment to the side of the screen)
			PlantInformationFragment loginFragment = PlantInformationFragment
					.newInstance(id, upperGroup);

			FragmentTransaction fragmentTransaction = getSupportFragmentManager()
					.beginTransaction();
			fragmentTransaction.replace(R.id.fragment_container, loginFragment);
			fragmentTransaction.commit();
		} else {
			// Phone behavior (Start new activity)
			Intent intent = new Intent(this, PlantInformationActivity.class);
			intent.putExtra("id", id);
			intent.putExtra("upper_group", upperGroup);
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
	 * 
	 * @param title
	 *            : title of the notification
	 * @param text
	 *            : text of the notification
	 * @param mId
	 *            : id of the notification (can handle more than one
	 *            notification)
	 */
	public void notifications(String title, String text, int mId) {

		NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(
				this).setSmallIcon(R.drawable.notification_icon)
				.setContentTitle(title).setContentText(text);
		// Creates an explicit intent for an Activity in your app
		Intent resultIntent = new Intent(this, ManagerActivity.class);

		// The stack builder object will contain an artificial back stack for
		// the
		// started Activity.
		// This ensures that navigating backward from the Activity leads out of
		// your application to the Home screen.
		TaskStackBuilder stackBuilder = TaskStackBuilder.create(this);
		// Adds the back stack for the Intent (but not the Intent itself)
		stackBuilder.addParentStack(ManagerActivity.class);
		// Adds the Intent that starts the Activity to the top of the stack
		stackBuilder.addNextIntent(resultIntent);
		PendingIntent resultPendingIntent = stackBuilder.getPendingIntent(0,
				PendingIntent.FLAG_UPDATE_CURRENT);
		mBuilder.setContentIntent(resultPendingIntent);
		NotificationManager mNotificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
		// mId allows you to update the notification later on.
		mNotificationManager.notify(mId, mBuilder.build());
	}

	/**
	 * Start the alarm for the daily notifications for the daily tasks
	 */
	public void startAlarm() {
		// Prepare intent to launch notification
		Intent intent = new Intent(this, MyReceiver.class);
		PendingIntent pendingIntent = PendingIntent.getBroadcast(this, 0,
				intent, 0);

		// Sets the date and the hour on today, 18;00
		Date dat = new Date();// initializes to now
		Calendar cal_alarm = Calendar.getInstance();
		Calendar cal_now = Calendar.getInstance();
		cal_now.setTime(dat);
		cal_alarm.setTime(dat);
		cal_alarm.set(Calendar.HOUR_OF_DAY, 17);// set the alarm time
		cal_alarm.set(Calendar.MINUTE, 30);
		cal_alarm.set(Calendar.SECOND, 0);
		if (cal_alarm.before(cal_now)) {// if its in the past increment
			cal_alarm.add(Calendar.DATE, 1);
		}

		// The alarm manager is an android system service
		AlarmManager am = (AlarmManager) this
				.getSystemService(Context.ALARM_SERVICE);
		am.set(AlarmManager.RTC_WAKEUP, cal_alarm.getTimeInMillis(),
				pendingIntent);
	}

	// BroadcastReceiver of the ManagerActivity, to receive the intent from the
	// MyReceiver class
	BroadcastReceiver broadcastReceiver = new BroadcastReceiver() {
		@Override
		public void onReceive(Context context, Intent intent) {
			tpDAO = new TaskPlantDao(getBaseContext());
			tp = tpDAO.getCurrentDayTaskPlant();
			for (int i = 0; i < tp.size(); i++) {

				String plantName = tp.getTaskPlantNumber(i).getPlant()
						.getName();
				String taskName = tp.getTaskPlantNumber(i).getTask()
						.getDescription();
				notifications(plantName, taskName, i);

			}
		}
	};

	@Override
	protected void onDestroy() {
		super.onDestroy();
		unregisterReceiver(broadcastReceiver);
	}

	@Override
	public void onLongClickedPlantFragmentInteraction(Green plant, boolean added) {

		// Create an instance of the dialog fragment
		DialogFragment dialog = new LongClickDialogFragment();

		// Put the boolean inside
		Bundle bundle = new Bundle();
		
		bundle.putInt("eId", plant.getExistingId());
		bundle.putBoolean("function", added);
		bundle.putString("description", plant.getDescription());
		bundle.putInt("number", 5);
		bundle.putString("name", plant.getName());
		bundle.putInt("timeToGrow", plant.getTimeToGrow());
		bundle.putInt("id", plant.getId());
		dialog.setArguments(bundle);

		// Show the dialog
		dialog.show(getSupportFragmentManager(), "LongClickDialogFragment");
	}

	@Override
	public void onDialogItemClick() {
	}
}
