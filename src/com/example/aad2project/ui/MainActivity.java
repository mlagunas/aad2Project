package com.example.aad2project.ui;

import java.net.MalformedURLException;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.aad2project.R;
import com.example.aad2project.object.ExistingPlant;
import com.example.aad2project.object.Weather;
import com.example.aad2project.provider.MyContentProvider;
import com.example.aad2project.provider.SharedInformation.Account;
import com.example.aad2project.server.SendToServer;
import com.microsoft.windowsazure.mobileservices.MobileServiceClient;
import com.microsoft.windowsazure.mobileservices.MobileServiceTable;
import com.microsoft.windowsazure.mobileservices.ServiceFilterResponse;
import com.microsoft.windowsazure.mobileservices.TableOperationCallback;

public class MainActivity extends ActionBarActivity implements
		LoginFragment.OnLoginFragmentInteractionListener,
		AccountCreationFragment.OnAccountCreationFragmentInteractionListener {

	public String EXTRA_USERNAME = "EXTRA_USERNAME";
	public static final String MyPREFERENCES = "MyPrefs";
	public static final String name = "nameKey";
	public static final String pass = "passwordKey";
	private SharedPreferences sharedPreferences;
	/**
	 * Mobile Service Client reference
	 */
	private MobileServiceClient mClient;

	/**
	 * Mobile Service Table used to access data
	 */
	private MobileServiceTable<ExistingPlant> tableExistingPlant;
	private MobileServiceTable<Weather> tableWeather;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		//inicialize();
		
		// Creation of the first fragment
		LoginFragment loginFragment = new LoginFragment();
		// Fragment transaction with fragment manager
		android.support.v4.app.FragmentTransaction fragmentTransaction = getSupportFragmentManager()
				.beginTransaction();
		// Fragment add into the frame_layout
		fragmentTransaction.replace(R.id.frame_content, loginFragment);

		// Actions displayed
		fragmentTransaction.commit();
		
		
	}

	/**
	 * Inserts all the Exisiting Plant and the weathers to the server
	 */
	private void inicialize(){
		try {
			mClient = new MobileServiceClient(
					"https://greenhub.azure-mobile.net/",
					"TnxUiYgqqbPovNDQHROYaqrALQQUMw32",
					this);

			// Get the Mobile Service Table instance to use
			tableExistingPlant = mClient.getTable(ExistingPlant.class);
			tableWeather = mClient.getTable(Weather.class);
			
			if (mClient == null) {
				Log.d("TAG","mCLientNull");
	
				return;
			}
	
			// Create a new item
			ExistingPlant p = new ExistingPlant();
		
			p.setPlantId(6);
			p.setName("Lillies");
			p.setDescription("Lilies are a group of flowering plants which are important in culture and literature in much of the world. Lily flowers are valued for their very showy, often fragrant flowers. You should plant the bulbs in autumn and they need from 6 to 8 hours of sunlight. Regarding the soil, lilies require a well-drained site as water trapped beneath the scales may rot the bulb. In active growth, water freely and apply a high-potash liquid fertilizer every 2 weeks.");
			p.setTimeToGrow(30);
			p.setWeatherId(6);
			// Insert the new item
			tableExistingPlant.insert(p, new TableOperationCallback<ExistingPlant>() {
	
				public void onCompleted(ExistingPlant entity, Exception exception, ServiceFilterResponse response) {
					
					if (exception == null) {
							Log.d("TAG","inserted");
					} else {
							Log.d("EXCEPTION",exception.toString());
							Log.d("TAG", " getCause = " + exception.getCause());
		                    Log.d("TAG", " getStackTrace = " + exception.getStackTrace());
					}
	
				}
			});
			
			
			p.setPlantId(5);
			p.setName("Sweet Peas");
			p.setDescription("Sweet peas enchant us with their fragile, seductive fragrance and make great bouquets. These pea-like flowers grow in many lovely colours and are suitable for an annual border, a woodland garden, and a trellis or arch. Early sowing is one of the secrets of sweet peas. The bloom time for this flower is in summer and fall. Before planting, soak the seeds in water for 1 day. If you are in temperate climate you do not need to do so. Germination can take 7 to 15 days and you must keep the soil humid and moist. Sweet peas prefer cool days and nights and will start to fade when temperatures go above 65 Fº. They are climbers so give them at least 1.8 m of good support.");
			p.setTimeToGrow(70);
			p.setWeatherId(5);
			// Insert the new item
			tableExistingPlant.insert(p, new TableOperationCallback<ExistingPlant>() {
	
				public void onCompleted(ExistingPlant entity, Exception exception, ServiceFilterResponse response) {
					
					if (exception == null) {
							Log.d("TAG","inserted");
					} else {
							Log.d("EXCEPTION",exception.toString());
							Log.d("TAG", " getCause = " + exception.getCause());
		                    Log.d("TAG", " getStackTrace = " + exception.getStackTrace());
					}
	
				}
			});
			
			p.setPlantId(4);
			p.setName("Lettuces");
			p.setDescription("Lettuce is a green annual plant which is most often grown as a leaf vegetable. It is easily cultivated, although it requires relatively low temperatures to prevent it from flowering quickly. It can be planted almost throughout all the year and it takes between 20 and 65 days to grow. The soil must be very rich in nutrients and have to drain water very good. You should also maintain the ground humid. You can harvest you lettuce during all the flourishing period, as they will always be very tasty.");
			p.setTimeToGrow(56);
			p.setWeatherId(4);
			// Insert the new item
			tableExistingPlant.insert(p, new TableOperationCallback<ExistingPlant>() {
	
				public void onCompleted(ExistingPlant entity, Exception exception, ServiceFilterResponse response) {
					
					if (exception == null) {
							Log.d("TAG","inserted");
					} else {
							Log.d("EXCEPTION",exception.toString());
							Log.d("TAG", " getCause = " + exception.getCause());
		                    Log.d("TAG", " getStackTrace = " + exception.getStackTrace());
					}
	
				}
			});
			
			p.setPlantId(2);
			p.setName("Carrots");
			p.setDescription("Carrots are a root vegetable, usually orange and with a crisp texture when fresh. They provide a good source of A vitamin which is good for the sight. They take between 2 and 3 months to grow and they should be planted either in spring, autumn or winter. Carrot seeds need a lot of humidity so two days before planting you could put the seeds between two humid papers. Make a hole of 1 cm deep in the ground and pour more than one seed because some will not germinate. They like cold climate but sunny at the same time. It is recommended that they get sun throughout all the day. When watering carrots you have to make sure that the water reaches deep in the hole.");
			p.setTimeToGrow(120);
			p.setWeatherId(2);
			// Insert the new item
			tableExistingPlant.insert(p, new TableOperationCallback<ExistingPlant>() {
	
				public void onCompleted(ExistingPlant entity, Exception exception, ServiceFilterResponse response) {
					
					if (exception == null) {
							Log.d("TAG","inserted");
					} else {
							Log.d("EXCEPTION",exception.toString());
							Log.d("TAG", " getCause = " + exception.getCause());
		                    Log.d("TAG", " getStackTrace = " + exception.getStackTrace());
					}
	
				}
			});
					
			p.setPlantId(1);
			p.setName("Potatoes");
			p.setDescription("Potatoes are a key for anybody who seeks to be self-sufficient. The potato is a starchy, tuberous crop that came from America but grow easily all over the world. It takes between 65 and 100 days to grow, and it should planted in spring. The soil should always be humid so you should water the around 3 times a week. When they flourish they will need more water. They can be harvested whenever they flourish and they should leave on the ground 1 day so they get dry.");
			p.setTimeToGrow(10);
			p.setWeatherId(1);
			// Insert the new item
			tableExistingPlant.insert(p, new TableOperationCallback<ExistingPlant>() {
	
				public void onCompleted(ExistingPlant entity, Exception exception, ServiceFilterResponse response) {
					
					if (exception == null) {
							Log.d("TAG","inserted");
					} else {
							Log.d("EXCEPTION",exception.toString());
							Log.d("TAG", " getCause = " + exception.getCause());
		                    Log.d("TAG", " getStackTrace = " + exception.getStackTrace());
					}
	
				}
			});
			
			p.setPlantId(3);
			p.setName("tomatoes");
			p.setDescription("Tomato is a red fruit but considered a vegetable for culinary purposes. Its many varieties are now widely grown, sometimes in greenhouses in cooler climates. It should be planted in the end of spring and it will grow in about 3 months. Tomatoes need rich soil so you can previously treat yours with compost or similar. You have also to be careful with the cold since it can kill the plant. Tomatoes also need at least 6 hours of sun so make sure that your garden or terrace has plenty of sunlight. Regarding the watering, you should not water the plant’s leaves and water the plant not so regularly but with abundant water each time. You should also put a vertical stick attached so it grows properly.");
			p.setTimeToGrow(10);
			p.setWeatherId(1);
			
			tableExistingPlant.insert(p, new TableOperationCallback<ExistingPlant>() {
	
				public void onCompleted(ExistingPlant entity, Exception exception, ServiceFilterResponse response) {
					
					if (exception == null) {
							Log.d("TAG","inserted");
					} else {
							Log.d("EXCEPTION",exception.toString());
							Log.d("TAG", " getCause = " + exception.getCause());
		                    Log.d("TAG", " getStackTrace = " + exception.getStackTrace());
					}
	
				}
			});
			
			Weather w = new Weather();
			
			w.setWeatherId(6);
			w.setMinTemp(15);
			w.setMaxTemp(20);
			w.setMinHumi(40);
			w.setMaxHumi(60);
			w.setMinLightness(25000);
			w.setMaxLightness(75000);
			
			tableWeather.insert(w, new TableOperationCallback<Weather>() {
	
				public void onCompleted(Weather entity, Exception exception, ServiceFilterResponse response) {
					
					if (exception == null) {
							Log.d("TAG","inserted");
					} else {
							Log.d("EXCEPTION",exception.toString());
							Log.d("TAG", " getCause = " + exception.getCause());
		                    Log.d("TAG", " getStackTrace = " + exception.getStackTrace());
					}
	
				}
			});
			
			w.setWeatherId(5);
			w.setMinTemp(15);
			w.setMaxTemp(20);
			w.setMinHumi(40);
			w.setMaxHumi(60);
			w.setMinLightness(25000);
			w.setMaxLightness(75000);
			
			tableWeather.insert(w, new TableOperationCallback<Weather>() {
	
				public void onCompleted(Weather entity, Exception exception, ServiceFilterResponse response) {
					
					if (exception == null) {
							Log.d("TAG","inserted");
					} else {
							Log.d("EXCEPTION",exception.toString());
							Log.d("TAG", " getCause = " + exception.getCause());
		                    Log.d("TAG", " getStackTrace = " + exception.getStackTrace());
					}
	
				}
			});
			
			w.setWeatherId(4);
			w.setMinTemp(12);
			w.setMaxTemp(15);
			w.setMinHumi(40);
			w.setMaxHumi(60);
			w.setMinLightness(2500);
			w.setMaxLightness(75000);
			
			tableWeather.insert(w, new TableOperationCallback<Weather>() {
	
				public void onCompleted(Weather entity, Exception exception, ServiceFilterResponse response) {
					
					if (exception == null) {
							Log.d("TAG","inserted");
					} else {
							Log.d("EXCEPTION",exception.toString());
							Log.d("TAG", " getCause = " + exception.getCause());
		                    Log.d("TAG", " getStackTrace = " + exception.getStackTrace());
					}
	
				}
			});
			
			w.setWeatherId(3);
			w.setMinTemp(16);
			w.setMaxTemp(18);
			w.setMinHumi(20);
			w.setMaxHumi(30);
			w.setMinLightness(2500);
			w.setMaxLightness(75000);
			
			tableWeather.insert(w, new TableOperationCallback<Weather>() {
	
				public void onCompleted(Weather entity, Exception exception, ServiceFilterResponse response) {
					
					if (exception == null) {
							Log.d("TAG","inserted");
					} else {
							Log.d("EXCEPTION",exception.toString());
							Log.d("TAG", " getCause = " + exception.getCause());
		                    Log.d("TAG", " getStackTrace = " + exception.getStackTrace());
					}
	
				}
			});
			
			w.setWeatherId(2);
			w.setMinTemp(15);
			w.setMaxTemp(18);
			w.setMinHumi(10);
			w.setMaxHumi(20);
			w.setMinLightness(500);
			w.setMaxLightness(50000);
			
			tableWeather.insert(w, new TableOperationCallback<Weather>() {
	
				public void onCompleted(Weather entity, Exception exception, ServiceFilterResponse response) {
					
					if (exception == null) {
							Log.d("TAG","inserted");
					} else {
							Log.d("EXCEPTION",exception.toString());
							Log.d("TAG", " getCause = " + exception.getCause());
		                    Log.d("TAG", " getStackTrace = " + exception.getStackTrace());
					}
	
				}
			});
			
			w.setWeatherId(1);
			w.setMinTemp(15);
			w.setMaxTemp(20);
			w.setMinHumi(40);
			w.setMaxHumi(60);
			w.setMinLightness(25000);
			w.setMaxLightness(75000);
			
			tableWeather.insert(w, new TableOperationCallback<Weather>() {
	
				public void onCompleted(Weather entity, Exception exception, ServiceFilterResponse response) {
					
					if (exception == null) {
							Log.d("TAG","inserted");
					} else {
							Log.d("EXCEPTION",exception.toString());
							Log.d("TAG", " getCause = " + exception.getCause());
		                    Log.d("TAG", " getStackTrace = " + exception.getStackTrace());
					}
	
				}
			});
		
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Override
	protected void onResume() {
		sharedPreferences = getSharedPreferences(MyPREFERENCES,
				Context.MODE_PRIVATE);
		if (sharedPreferences.contains(name)) {
			if (sharedPreferences.contains(pass)) {
				Intent i = new Intent(this,
						com.example.aad2project.ui.ManagerActivity.class);
				startActivity(i);
				finish();
			}
		}
		super.onResume();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		// action with ID action_settings was selected
		case R.id.action_settings:
			//Toast.makeText(this, "Settings selected", Toast.LENGTH_SHORT)
				//	.show();
			Intent intent = new Intent(MainActivity.this,
					SettingsActivity.class);
			startActivity(intent);
			break;
			
		case R.id.action_about:
			Intent intent2 = new Intent(MainActivity.this,AboutActivity.class);
			startActivity(intent2);
			break;			
			
		default:
			break;
		}

		return true;
	}

	/**
	 * Use this method when the user is successfully authenticated
	 * 
	 * @param username
	 */
	public void successfulAuthentication(String username, String password) {
		// Save the username and the password for latter check
		Editor editor = sharedPreferences.edit();
		editor.putString(name, username);
		editor.putString(pass, password);
		editor.commit();

		// Intent creation, to switch activity
		Intent intent = new Intent(MainActivity.this, ManagerActivity.class);
		// Put the username in extra
		intent.putExtra(EXTRA_USERNAME, username);
		// Start the activity
		startActivity(intent);

		// Toast to inform the user that the connection is a success
		Toast.makeText(getApplicationContext(),
				getResources().getString(R.string.success_login),
				Toast.LENGTH_SHORT).show();
		finish();
	}

	/**
	 * Use this method when the user click on the "create a new account" text on
	 * the login page
	 */
	public void newAccount() {

		// Creation of the first fragment
		AccountCreationFragment accountCreationFragment = new AccountCreationFragment();
		// Fragment transaction with fragment manager
		android.support.v4.app.FragmentTransaction fragmentTransaction = getSupportFragmentManager()
				.beginTransaction();
		// Fragment add into the frame_layout
		fragmentTransaction
				.replace(R.id.frame_content, accountCreationFragment);
		fragmentTransaction.addToBackStack(null);
		// Actions displayed
		fragmentTransaction.commit();
	}

	@Override
	public void onFragmentInteraction(Uri uri) {
		// TODO Auto-generated method stub

	}

	/**
	 * Handle the login part (with the content provider)
	 * 
	 * @param email
	 *            : provided by the user
	 * @param password
	 *            : provided by the user
	 * @return login : if it succeed or not
	 */
	public int login(String email, String password) {

		SendToServer sts;
		String result = null;
		JSONObject windJSON = null;
		String emailReceived;
		String passwordReceived;
		int login = 0;

		sts = new SendToServer(email, password, "selectUser");
		sts.execute();
		while (result == null){
			result = sts.getReturn();
		}
		
		Log.d("TAGERT",result);
		if (!result.equals(false)){

			JSONArray myJSON = null;
	    	try {
				myJSON = new JSONArray(result);
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	    	
			for (int i = 0; i < myJSON.length(); i++) {
				
				try {
					windJSON = myJSON.getJSONObject(i);
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				try {
					emailReceived = windJSON.getString("email");
					passwordReceived = windJSON.getString("password");
					if (emailReceived.equals(email) && passwordReceived.equals(password)){
						login = 1;
					}
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				// Display in the LogCat
			
			}
		}
		return login;
		

	}

	/**
	 * Add an account in the database
	 * 
	 * @param email
	 *            : provided by the user
	 * @param password
	 *            : provided by the user
	 */
	public void addAccount(String email, String password) {
		
		new SendToServer(email, password, "insert").execute();
		
		// Toast to inform the user that the account has been created
		Toast.makeText(getApplicationContext(),
				getResources().getString(R.string.creation_account),
				Toast.LENGTH_SHORT).show();

		// Creation of the first fragment
		LoginFragment loginFragment = new LoginFragment();
		// Fragment transaction with fragment manager
		FragmentTransaction fragmentTransaction = getSupportFragmentManager()
				.beginTransaction();
		// Fragment add into the frame_layout
		fragmentTransaction.replace(R.id.frame_content, loginFragment);
		// Actions displayed
		fragmentTransaction.commit();
	}

}