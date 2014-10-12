package com.example.aad2project.ui;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.aad2project.R;

public class PlantInformationActivity extends Activity {
	
	private int listId ;
	private TextView textView1,textView2;
	private ImageView imageView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_plant_information);
		
		// Retrieve the id of the plant that has been pressed
		// and get the references to the xml
		listId = getIntent().getIntExtra("id", 0);
		imageView = (ImageView) findViewById(R.id.imageView1);
		textView1 = (TextView) findViewById(R.id.textView1);
		textView2 = (TextView) findViewById(R.id.textView2);
		
		Toast.makeText(getApplicationContext(), "The id is: " + listId,
				Toast.LENGTH_SHORT).show();
				
		switch (listId) {
		case 1:
			imageView.setImageResource(R.drawable.potatoes);
			textView1.setText(R.string.potatoes);
			textView2.setText("Potatoes are nice");
			break;
		case 2:
			imageView.setImageResource(R.drawable.tomatoes);
			textView1.setText(R.string.tomatoes);
			textView2.setText("Tomatoes are red");
			break;
		case 3:
			imageView.setImageResource(R.drawable.potatoes);
			textView1.setText(R.string.onions);
			textView2.setText("Onions make you cry");
			break;
		case 4:
			imageView.setImageResource(R.drawable.garlic);
			textView1.setText(R.string.garlic);
			textView2.setText("Garlic is very healthy");
			break;
		}
		
	}
}
