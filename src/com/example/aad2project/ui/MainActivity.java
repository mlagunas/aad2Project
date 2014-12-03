package com.example.aad2project.ui;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

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
import com.example.aad2project.server.SendToServer;

public class MainActivity extends ActionBarActivity implements
		LoginFragment.OnLoginFragmentInteractionListener,
		AccountCreationFragment.OnAccountCreationFragmentInteractionListener {

	public String EXTRA_USERNAME = "EXTRA_USERNAME";
	public static final String MyPREFERENCES = "MyPrefs";
	public static final String name = "nameKey";
	public static final String pass = "passwordKey";
	private SharedPreferences sharedPreferences;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

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