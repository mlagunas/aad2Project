package com.example.aad2project.services;

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
import com.example.aad2project.ui.ManagerActivity;

public class NotificationService extends Service {
 
    /**
     * Class for clients to access
     */
    public class ServiceBinder extends Binder {
        NotificationService getService() {
            return NotificationService.this;
        }
    }
 
    // Unique id to identify the notification.
    private static final int NOTIFICATION = 123;
    // Name of an intent extra we can use to identify if this service was started to create a notification  
    public static final String INTENT_NOTIFY = "com.example.aad2project.INTENT_NOTIFY";
    // The system notification manager
    private NotificationManager mNM;
 
    @Override
    public void onCreate() {
        Log.i("NotificationService", "onCreate()");
        mNM = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
    }
 
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.i("LocalService", "Received start id " + startId + ": " + intent);
         
        // If this service was started by out AlarmTask intent then we want to show our notification
        if(intent.getBooleanExtra(INTENT_NOTIFY, false))
            showNotification();
         
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
