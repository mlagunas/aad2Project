package com.example.aad2project.ui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.aad2project.R;

public class MainActivity extends Activity implements OnClickListener {
	
	public String EXTRA_USERNAME = "EXTRA_USERNAME";
	private EditText  usernameView = null;
	private EditText  passwordView = null;
	private String username = "admin";
	private String password = "admin";
	private Button login;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		usernameView = (EditText) findViewById (R.id.email);
		passwordView = (EditText) findViewById (R.id.password);
		login = (Button) findViewById (R.id.button1);
		login.setOnClickListener(this);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		if(usernameView.getText().toString().equals(username) && 
				passwordView.getText().toString().equals(password)){
			
			Intent intent = new Intent (this, ManagerActivity.class);
			intent.putExtra(EXTRA_USERNAME, username);
			
			startActivity(intent);
			
			Toast.makeText(getApplicationContext(), "Redirecting...", 
					Toast.LENGTH_SHORT).show();
		}	
		else{
			Toast.makeText(getApplicationContext(), "Wrong Credentials",
					Toast.LENGTH_SHORT).show();
		}
	}

}