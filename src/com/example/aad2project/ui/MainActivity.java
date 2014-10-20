package com.example.aad2project.ui;

import java.util.ArrayList;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.aad2project.R;
import com.example.aad2project.model.MyContentProvider;
import com.example.aad2project.model.SharedInformation.Account;

public class MainActivity extends ActionBarActivity implements LoginFragment.OnLoginFragmentInteractionListener, AccountCreationFragment.OnAccountCreationFragmentInteractionListener {

	public String EXTRA_USERNAME = "EXTRA_USERNAME";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		// Creation of the first fragment
		LoginFragment loginFragment =  new LoginFragment();
		// Fragment transaction with fragment manager
		android.support.v4.app.FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();         
		// Fragment add into the frame_layout
		fragmentTransaction.replace(R.id.frame_content, loginFragment);
		
		// Actions displayed
		fragmentTransaction.commit();
		


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
	      Toast.makeText(this, "Settings selected", Toast.LENGTH_SHORT)
	          .show();
	      
	      Intent intent = new Intent(MainActivity.this,SettingsActivity.class);
	      startActivity(intent);
	            
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
	public void successfulAuthentication(String username){

		// Intent creation, to switch activity
		Intent intent = new Intent (MainActivity.this, ManagerActivity.class);
		// Put the username in extra
		intent.putExtra(EXTRA_USERNAME, username);
		// Start the activity
		startActivity(intent);
		
		// Toast to inform the user that the connection is a success
		Toast.makeText(getApplicationContext(), getResources().getString(R.string.success_login), 
				Toast.LENGTH_SHORT).show();
		finish();
	}

	/**
	 * Use this method when the user don't use the correct credentials
	 */
	public void wrongCredentials(){
		// Toast to inform the user that the credentials were wrong
		Toast.makeText(getApplicationContext(), getResources().getString(R.string.wrong_credentials),
				Toast.LENGTH_SHORT).show();
	}
	// TODO Preba
	/**
	 * Use this method when the user click on the "create a new account" text on the login page
	 */
	public void newAccount(){

		// Creation of the first fragment
		AccountCreationFragment accountCreationFragment = new AccountCreationFragment();
		// Fragment transaction with fragment manager
		android.support.v4.app.FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();         
		// Fragment add into the frame_layout
		fragmentTransaction.replace(R.id.frame_content, accountCreationFragment);
		fragmentTransaction.addToBackStack(null);
		// Actions displayed
		fragmentTransaction.commit();
	}

	@Override
	public void onFragmentInteraction(Uri uri) {
		// TODO Auto-generated method stub

	}
	
	public int login(String email, String password) {
		String columns[] = new String[] { Account.ACCOUNT_ID, Account.ACCOUNT_EMAIL, Account.ACCOUNT_PASSWORD };
		Uri mContacts = MyContentProvider.CONTENT_URI;
		Cursor cur = managedQuery(mContacts, columns, null, null, null);
		int login = 0;
		ArrayList<Account> listAccount = new ArrayList<Account>();
		
		if (cur.moveToFirst()) {
			
			do {
				if (cur.getString(cur.getColumnIndex(Account.ACCOUNT_EMAIL)).equals(email)){
					if (cur.getString(cur.getColumnIndex(Account.ACCOUNT_PASSWORD)).equals(password)){
						login = 1;
					}
				}
			} while (cur.moveToNext());
		}
		return login;

	}
	
	public void addAccount(String email, String password) {
		ContentValues account = new ContentValues();
		account.put(Account.ACCOUNT_EMAIL, email);
		account.put(Account.ACCOUNT_PASSWORD, password);
		getContentResolver().insert(MyContentProvider.CONTENT_URI, account);
		
		// Toast to inform the user that the account has been created
		Toast.makeText(getApplicationContext(), getResources().getString(R.string.creation_account), 
						Toast.LENGTH_SHORT).show();
	}

}