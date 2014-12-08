package com.example.aad2project.services;

import java.net.MalformedURLException;
import java.util.List;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

import com.example.aad2project.R;
import com.example.aad2project.model.PlantDao;
import com.example.aad2project.model.TaskPlantDao;
import com.example.aad2project.model.WeatherDao;
import com.example.aad2project.object.Plant;
import com.example.aad2project.object.Sensors;
import com.example.aad2project.object.Task;
import com.example.aad2project.ui.ManagerActivity;
import com.microsoft.windowsazure.mobileservices.MobileServiceClient;
import com.microsoft.windowsazure.mobileservices.QueryOrder;
import com.microsoft.windowsazure.mobileservices.ServiceFilterResponse;
import com.microsoft.windowsazure.mobileservices.TableQueryCallback;

public class SensorService extends Service {
	
	private MobileServiceClient mClient;

	
    /**
     * Class for clients to access
     */
    public class ServiceBinder extends Binder {
        SensorService getService() {
            return SensorService.this;
        }
    }
 
    // Unique id to identify the notification.
    private static final int NOTIFICATION = 94;
    // Name of an intent extra we can use to identify if this service was started to create a notification  
    public static final String INTENT_NOTIFY = "com.example.aad2project.INTENT_NOTIFY";
    // The system notification manager
    private NotificationManager mNM;
 
    @Override
    public void onCreate() {
        Log.i("SensorService", "onCreate()");
        mNM = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        
        try {
			mClient = new MobileServiceClient(
					"https://greenhub.azure-mobile.net/",
					"TnxUiYgqqbPovNDQHROYaqrALQQUMw32", this);
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
 
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.i("LocalService", "Received start id " + startId + ": " + intent);
         
        mClient.getTable(Sensors.class).top(1).orderBy("__createdAt", QueryOrder.Descending).execute(
				new TableQueryCallback<Sensors>() {

					@Override
					public void onCompleted(List<Sensors> sensors, int arg1,
							Exception exception, ServiceFilterResponse arg3) {
						if (exception != null) {
							Log.d("EXCEPTION",exception.toString());
							Log.d("TAG", " getCause = " + exception.getCause());
		                    Log.d("TAG", " getStackTrace = " + exception.getStackTrace());
						} else {
							PlantDao pDao = new PlantDao(getApplicationContext());
							WeatherDao wDao = new WeatherDao(getApplicationContext());
							TaskPlantDao tpDao = new TaskPlantDao(getApplicationContext());
							Sensors s = sensors.get(0);
							boolean needsWater = false;
							for (Plant p:pDao.getAddedPlants()){
								long lowHumidityLimit = wDao.getWeather(p.getWeatherId()).getMinHumi();
								if (s.getHumidity() < lowHumidityLimit){
									needsWater = true;
									Task t = new Task();
									t.setDescription("Water " + p.getName());
									tpDao.createTaskPlant(p, t, System.currentTimeMillis());
								}
							}
							if (needsWater){
								showNotification();
							}
						}
					}
				});
         
        // We don't care if this service is stopped as we have already delivered our notification
        return START_NOT_STICKY;
    }
 
    @Override
    public IBinder onBind(Intent intent) {
        return mBinder;
    }
 
    // This is the object that receives interactions from clients
    private final IBinder mBinder = new ServiceBinder();
 
    /**
     * Creates a notification and shows it in the OS drag-down status bar
     */
    private void showNotification() {  
        // The PendingIntent to launch our activity if the user selects this notification
        PendingIntent contentIntent = PendingIntent.getActivity(this, 0, new Intent(this, ManagerActivity.class), 0);
        
        Notification notification = new NotificationCompat .Builder(this)
        .setContentTitle("You have some tasks to do today")
        .setContentText("Click to see more details")
        .setSmallIcon(R.drawable.ic_greenhub_grey)
        .setContentIntent(contentIntent)
        .build();
 
        // Clear the notification when it is pressed
        notification.flags |= Notification.FLAG_AUTO_CANCEL;
         
        // Send the notification to the system.
        mNM.notify(NOTIFICATION, notification);
         
        // Stop the service when we are finished
        stopSelf();
    }
}

