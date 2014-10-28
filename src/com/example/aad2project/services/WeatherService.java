package com.example.aad2project.services;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.StatusLine;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONException;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import android.app.IntentService;
import android.content.Intent;

import com.example.aad2project.model.WeatherDao;
import com.example.aad2project.object.Weather;

public class WeatherService extends IntentService {
	private final static String TAG = "TestRequette";
	private WeatherDao w;
	private Weather weather;

	public WeatherService() {
		super(TAG);
	}

	protected void onHandleIntent(Intent intent) {
		try {
			getWeatherRequest();
		} catch (JSONException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		}

	}

	protected String getPosition() {
		// LocationManager locationManager =
		// (LocationManager)getSystemService(Context.LOCATION_SERVICE);
		// Criteria crit = new Criteria();
		// crit.setAccuracy(Criteria.ACCURACY_FINE);
		// String provider = LocationManager.NETWORK_PROVIDER;
		// Location loc = locationManager.getLastKnownLocation(provider);

		// Log.d(TAG,LocationManager.NETWORK_PROVIDER);
		// locationManager.requestLocationUpdates(
		// LocationManager.NETWORK_PROVIDER, 1000, 0, this);

		//
		// Log.d("GPS", ""+loc+"");
		// Log.d(TAG, "Requettes4");

		return null;
	}

	protected String getWeatherRequest() throws JSONException, ParseException {
		HttpClient httpclient = new DefaultHttpClient();
		HttpResponse response = null;

		try {
			response = httpclient
					.execute(new HttpGet(
							"http://api.openweathermap.org/data/2.5/weather?q=Arhus,dk"));
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		StatusLine statusLine = response.getStatusLine();
		if (statusLine.getStatusCode() == HttpStatus.SC_OK) {
			ByteArrayOutputStream out = new ByteArrayOutputStream();
			try {
				response.getEntity().writeTo(out);

			} catch (IOException e) {
				e.printStackTrace();
			}
			try {
				out.close();
			} catch (IOException e) {
				e.printStackTrace();
			}

			String responseString = out.toString();

			JSONObject json = (JSONObject) new JSONParser()
					.parse(responseString);
			JSONObject info = (JSONObject) json.get("main");

			info.get("temp_min");

			/*
			 * weather = new Weather(); weather.setMaxTemp((Integer)
			 * info.get("temp_max")); weather.setMinTemp((Integer)
			 * info.get("temp_min")); weather.setMinHumi((Integer)
			 * info.get("humidity")); weather.setMaxHumi((Integer)
			 * info.get("humidity")); weather.setMaxLightness(1);
			 * weather.setMinLightness(2);
			 * 
			 * w = new WeatherDao(this); w.add(weather);
			 */

		} else {
			// Closes the connection.
			try {
				response.getEntity().getContent().close();
			} catch (IllegalStateException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
			try {
				throw new IOException(statusLine.getReasonPhrase());
			} catch (IOException e) {
				e.printStackTrace();
			}

			return null;
		}
		return null;
	}

	//
	// private LocationManager mLocationManager = null;
	// private static final int LOCATION_INTERVAL = 1000;
	// private static final float LOCATION_DISTANCE = 10f;
	//
	// private class LocationListener implements
	// android.location.LocationListener{
	// Location mLastLocation;
	//
	//
	// public LocationListener(String provider)
	// {
	// Log.e(TAG, "LocationListener " + provider);
	// mLastLocation = new Location(provider);
	// }
	// @Override
	// public void onLocationChanged(Location location)
	// {
	// Log.e(TAG, "onLocationChanged: " + location);
	// mLastLocation.set(location);
	// }
	// @Override
	// public void onProviderDisabled(String provider)
	// {
	// Log.e(TAG, "onProviderDisabled: " + provider);
	// }
	// @Override
	// public void onProviderEnabled(String provider)
	// {
	// Log.e(TAG, "onProviderEnabled: " + provider);
	// }
	// @Override
	// public void onStatusChanged(String provider, int status, Bundle extras)
	// {
	// Log.e(TAG, "onStatusChanged: " + provider);
	// }
	// }
	//
	// LocationListener[] mLocationListeners = new LocationListener[] {
	// new LocationListener(LocationManager.GPS_PROVIDER),
	// new LocationListener(LocationManager.NETWORK_PROVIDER)
	// };
	// @Override
	// public IBinder onBind(Intent arg0)
	// {
	// return null;
	// }
	// @Override
	// public int onStartCommand(Intent intent, int flags, int startId)
	// {
	// Log.e(TAG, "onStartCommand");
	// super.onStartCommand(intent, flags, startId);
	// return START_STICKY;
	// }
	// @Override
	// public void onCreate()
	// {
	// initializeLocationManager();
	// try {
	//
	// Log.d(TAG,LocationManager.NETWORK_PROVIDER);
	//
	//
	// mLocationManager.requestLocationUpdates(
	// LocationManager.NETWORK_PROVIDER, LOCATION_INTERVAL, LOCATION_DISTANCE,
	// mLocationListeners[1]);
	// } catch (java.lang.SecurityException ex) {
	// Log.i(TAG, "fail to request location update, ignore", ex);
	// } catch (IllegalArgumentException ex) {
	// Log.d(TAG, "network provider does not exist, " + ex.getMessage());
	// }
	// try {
	// mLocationManager.requestLocationUpdates(
	// LocationManager.GPS_PROVIDER, LOCATION_INTERVAL, LOCATION_DISTANCE,
	// mLocationListeners[0]);
	// } catch (java.lang.SecurityException ex) {
	// Log.i(TAG, "fail to request location update, ignore", ex);
	// } catch (IllegalArgumentException ex) {
	// Log.d(TAG, "gps provider does not exist " + ex.getMessage());
	// }
	// }
	// @Override
	// public void onDestroy()
	// {
	// Log.e(TAG, "onDestroy");
	// super.onDestroy();
	// if (mLocationManager != null) {
	// for (int i = 0; i < mLocationListeners.length; i++) {
	// try {
	// mLocationManager.removeUpdates(mLocationListeners[i]);
	// } catch (Exception ex) {
	// Log.i(TAG, "fail to remove location listners, ignore", ex);
	// }
	// }
	// }
	// }
	// private void initializeLocationManager() {
	// if (mLocationManager == null) {
	// mLocationManager = (LocationManager)
	// getApplicationContext().getSystemService(Context.LOCATION_SERVICE);
	//
	// String fournisseur = LocationManager.NETWORK_PROVIDER;
	// mLocationManager.getProvider(fournisseur);
	//
	// Log.d(TAG,String.valueOf(mLocationManager.getAllProviders()));
	// }
	// }

}
