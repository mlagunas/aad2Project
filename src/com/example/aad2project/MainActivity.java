package com.example.aad2project;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends Activity {

   private EditText  username=null;
   private EditText  password=null;
   private Button login;
   
   @Override
   protected void onCreate(Bundle savedInstanceState) {
      super.onCreate(savedInstanceState);
      setContentView(R.layout.activity_main);
      username = (EditText)findViewById(R.id.email);
      password = (EditText)findViewById(R.id.password);
      login = (Button)findViewById(R.id.button1);
   }

   public void login(View view){
      if(username.getText().toString().equals("admin") && 
      password.getText().toString().equals("admin")){
      Toast.makeText(getApplicationContext(), "Redirecting...", 
      Toast.LENGTH_SHORT).show();
   }	
   else{
      Toast.makeText(getApplicationContext(), "Wrong Credentials",
      Toast.LENGTH_SHORT).show();

   }

}
   @Override
   public boolean onCreateOptionsMenu(Menu menu) {
      // Inflate the menu; this adds items to the action bar if it is present.
      getMenuInflater().inflate(R.menu.main, menu);
      return true;
   }

}