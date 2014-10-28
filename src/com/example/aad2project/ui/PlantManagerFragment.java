package com.example.aad2project.ui;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.EditText;
import android.widget.ExpandableListView;
import android.widget.ExpandableListView.OnChildClickListener;
import android.widget.Toast;

import com.example.aad2project.R;
import com.example.aad2project.adapter.PlantManagerAdapter;
import com.example.aad2project.model.PlantDao;
import com.example.aad2project.model.PlantsLoader;
import com.example.aad2project.object.Plant;

/**
 * A simple {@link Fragment} subclass.
 * 
 */
public class PlantManagerFragment extends Fragment implements LoaderManager.LoaderCallbacks<List<List<Plant>>> {

	private PlantDao plants;

	private ExpandableListView mList;
	private PlantManagerAdapter mAdapter;

	private EditText mFilter;

	private OnPlantManagerFragmentInteractionListener mListener;

	public PlantManagerFragment() {
		// Required empty public constructor
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		plants = new PlantDao(getActivity());

		// Inflate the layout for this fragment
		View view = inflater.inflate(R.layout.fragment_plant_manager,
				container, false);

		// Get View references
		mList = (ExpandableListView) view.findViewById(R.id.list);
		mFilter = (EditText) view.findViewById(R.id.filter);

		// Create mAdapter for the ListView
		mAdapter = new PlantManagerAdapter(getActivity(),
				plants.getAddedPlants(), plants.getAllPlants());

		return view;
	}

	public void refresh() {
		//mAdapter.updatePlantList(plants.getAddedPlants(), plants.getAllPlants());
	}

	@Override
	public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
		// Set ListView Adapter
		mList.setAdapter(mAdapter);

		// Expand groups and hide indicator
		mList.setGroupIndicator(null);
		for (int i = 0; i < mAdapter.getGroupCount(); i++) {
			mList.expandGroup(i);
		}

		// Filter when text in the EditText changes
		mFilter.addTextChangedListener(new TextWatcher() {
			@Override
			public void onTextChanged(CharSequence s, int start, int before,
					int count) {
				mAdapter.filter(s.toString());
			}

			@Override
			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) {
			}

			@Override
			public void afterTextChanged(Editable s) {
			}
		});

		// Handle Child clicks
		mList.setOnChildClickListener(new OnChildClickListener() {

			@Override
			public boolean onChildClick(ExpandableListView parent, View v,
					int groupPosition, int childPosition, long id) {

				Toast.makeText(getActivity(), "Short click", Toast.LENGTH_SHORT)
						.show();
				// Notify the activity and send the id of the clicked plant
				int  plantId = ((Plant) mAdapter.getChild(groupPosition,
						childPosition)).getId();
				boolean upperGroup = groupPosition == 0;
				onItemPressed(plantId, upperGroup);

				return false;
			}
		});

		mList.setOnItemLongClickListener(new OnItemLongClickListener() {

			@Override
			public boolean onItemLongClick(AdapterView<?> parent, View view,
					int position, long id) {
				// Create dialog

				Plant p;

				// Put boolean to show Add or Delete
				Bundle bundle = new Bundle();

				if ((Boolean) view.getTag()) {
					ArrayList<Plant> data = plants.getAddedPlants();
					p = data.get(position - 1);
				} else {
					ArrayList<Plant> data = plants.getAllPlants();
					p = data.get(position - plants.getAddedPlants().size() - 2);
				}

				if (mListener != null) {
					mListener.onLongClickedPlantFragmentInteraction(p,
							(Boolean) view.getTag());
				}

				return true;
			}
		});
		super.onViewCreated(view, savedInstanceState);
	}
	
	

	@Override
	public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        getLoaderManager().initLoader(0, null, this);

		super.onActivityCreated(savedInstanceState);
	}

	public void onItemPressed(int id, boolean upperGroup) {
		if (mListener != null) {
			mListener.onPlantManagerFragmentInteraction(id, upperGroup);
		}
	}

	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);
		try {
			mListener = (OnPlantManagerFragmentInteractionListener) activity;
		} catch (ClassCastException e) {
			throw new ClassCastException(
					activity.toString()
							+ " must implement OnPlantManagerFragmentInteractionListener");
		}
	}

	@Override
	public void onDetach() {
		super.onDetach();
		mListener = null;
	}

	public interface OnPlantManagerFragmentInteractionListener {
		public void onPlantManagerFragmentInteraction(int id, boolean upperGroup);

		public void onLongClickedPlantFragmentInteraction(Plant plant,
				boolean added);
	}

	@Override
	public Loader<List<List<Plant>>> onCreateLoader(int arg0, Bundle arg1) {
		return new PlantsLoader(getActivity());
	}

	@Override
	public void onLoadFinished(Loader<List<List<Plant>>> loader,
			List<List<Plant>> data) {
		mAdapter.updatePlantList(data.get(0), data.get(1));
	}

	@Override
	public void onLoaderReset(Loader<List<List<Plant>>> arg0) {
		
	}
}
