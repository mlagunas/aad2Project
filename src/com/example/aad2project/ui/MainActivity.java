package com.example.aad2project.ui;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.widget.Toast;

import com.example.aad2project.R;

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
		fragmentTransaction.add(R.id.frame_content, loginFragment);
		fragmentTransaction.addToBackStack(null);
		// Actions displayed
		fragmentTransaction.commit();



	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	public void successfulAutentication(String username){

		Intent intent = new Intent (MainActivity.this, ManagerActivity.class);
		intent.putExtra(EXTRA_USERNAME, username);

		startActivity(intent);

		Toast.makeText(getApplicationContext(), "Successful autentication", 
				Toast.LENGTH_SHORT).show();
	}

	public void wrongCredentials(){
		Toast.makeText(getApplicationContext(), "Wrong Credentials",
				Toast.LENGTH_SHORT).show();
	}

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

}