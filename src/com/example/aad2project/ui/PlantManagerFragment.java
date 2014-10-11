package com.example.aad2project.ui;

import java.util.ArrayList;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.ArrayAdapter;
import android.widget.ExpandableListView;
import android.widget.Toast;

import com.example.aad2project.R;
import com.example.aad2project.model.DaoBase;
import com.example.aad2project.model.DatabaseHandler;
import com.example.aad2project.model.Plant;
import com.example.aad2project.model.PlantDao;

/**
 * A simple {@link Fragment} subclass.
 * 
 */
public class PlantManagerFragment extends Fragment {

	ArrayList<String> data;
	ArrayAdapter<String> lvAdapter;

	private ExpandableListView list;
	public PlantManagerFragment() {
		// Required empty public constructor
	}

	/**
	 * This method return all the data stored in the Database regarding to the
	 * user who is using the application
	 * 
	 * @return
	 */
	private ArrayList<Plant> getPlants() {
		// Need the data from the database to initialize the Array
		// So we invent various plant objects and their parameters
		ArrayList<Plant> plants = new ArrayList<Plant>();
		/*Plant p1 = new Plant();
		p1.setName("potatoes");
		Plant p2 = new Plant();
		p2.setName("tomatoes");
		Plant p3 = new Plant();
		p3.setName("onions");
		Plant p4 = new Plant();
		p4.setName("garlics");
		plants.add(p1);
		plants.add(p2);
		plants.add(p3);
		plants.add(p4);
		return plants; */
		
	
		DatabaseHandler supp = new DatabaseHandler(getActivity(),"try.db", null, 1);
		SQLiteDatabase db = supp.getReadableDatabase();
		Cursor c = db.rawQuery("SELECT * FROM Plant",null);
		//Nos aseguramos de que existe al menos un registro
		if (c.moveToFirst()) {
		     //Recorremos el cursor hasta que no haya más registros
		     do {
		         Plant p = new Plant();
		         p.setName(c.getString(1));
		    	 plants.add(p);
		     } while(c.moveToNext());
		}
		db.close();
		return plants;
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// Inflate the layout for this fragment
		View view = inflater.inflate(R.layout.fragment_plant_manager,
				container, false);

		list = (ExpandableListView) view.findViewById(R.id.list);

		PlantListAdapter adapter = new PlantListAdapter(getActivity(),
				getPlants(), getPlants());

		list.setAdapter(adapter);
		list.expandGroup(0);
		return view;
	}

	@Override
	public void onActivityCreated(Bundle savedState) {
		super.onActivityCreated(savedState);

		list.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				Toast.makeText(getActivity(), "Short click", Toast.LENGTH_SHORT)
						.show();

			}
		});

		list.setOnItemLongClickListener(new OnItemLongClickListener() {

			@Override
			public boolean onItemLongClick(AdapterView<?> parent, View view,
					int position, long id) {
				if (id < 0) {
					// Create dialog
					DialogFragment dialog = new LongClickDialogFragment();

					// Put boolean to show Add or Delete
					Bundle bundle = new Bundle();
					bundle.putBoolean("added", (Boolean) view.getTag());
					dialog.setArguments(bundle);

					// Show dialog
					dialog.show(getFragmentManager(), "longClickDialog");

					Toast.makeText(getActivity(), "On long click listener",
							Toast.LENGTH_LONG).show();
				}
				return true;

			}
		});
	}
}
