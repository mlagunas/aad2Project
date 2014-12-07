package com.example.aad2project.adapter;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.aad2project.R;
import com.example.aad2project.object.Green;

public class PlantManagerAdapter extends BaseExpandableListAdapter {

	private List<Green> added;
	private List<Green> filteredAdded;
	private List<Green> all;
	private List<Green> filteredAll;
	private Context context;

	public PlantManagerAdapter(Context context, List<Green> added,
			List<Green> all) {
		this.context = context;
		this.added = added;
		this.all = all;
		filteredAdded = added;
		filteredAll = all;
	}

	public boolean filter(String s) {
		if (s == "") {
			filteredAdded = added;
			filteredAll = all;
		} else {
			filteredAdded = new ArrayList<Green>();
			filteredAll = new ArrayList<Green>();
			for (Green p : added) {
				if (p.getName().toLowerCase().startsWith(s.toLowerCase())) {
					filteredAdded.add(p);
				}
			}
			for (Green p : all) {
				if (p.getName().toLowerCase().startsWith(s.toLowerCase())) {
					filteredAll.add(p);
				}
			}
		}
		notifyDataSetChanged();
		return true;
	}

	public List<Green> getFilteredAdded(){
		return filteredAdded;
	}
	
	public List<Green> getFilteredAll(){
		return filteredAll;
	}
	
	@Override
	public int getGroupCount() {
		return 2;
	}

	@Override
	public int getChildrenCount(int groupPosition) {
		switch (groupPosition) {
		case 0:
			if (filteredAdded.isEmpty())
				return 0;
			else
				return filteredAdded.size();
		case 1:
			if (filteredAll.equals(null))
				return 0;
			else
				return filteredAll.size();

		}
		return 0;
	}

	@Override
	public Object getGroup(int groupPosition) {
		switch (groupPosition) {
		case 0:
			return "Your Plants";
		case 1:
			return "All Plants";
		}
		return null;
	}

	@Override
	public Object getChild(int groupPosition, int childPosition) {
		switch (groupPosition) {
		case 0:
			return filteredAdded.get(childPosition);
		case 1:
			return filteredAll.get(childPosition);
		}
		return null;
	}

	@Override
	public long getGroupId(int groupPosition) {
		return groupPosition;
	}

	@Override
	public long getChildId(int groupPosition, int childPosition) {
		return childPosition;
	}

	@Override
	public boolean hasStableIds() {
		return true;
	}

	@Override
	public View getGroupView(int groupPosition, boolean isExpanded,
			View convertView, ViewGroup parent) {
		if (convertView == null) {
			LayoutInflater infalInflater = (LayoutInflater) context
					.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			convertView = infalInflater.inflate(
					R.layout.plant_row_layout_group, null);
		}

		TextView groupTitle = (TextView) convertView
				.findViewById(R.id.group_title);

		groupTitle.setText((String) getGroup(groupPosition));

		// Disable clicks on groups
		convertView.setLongClickable(false);
		convertView.setFocusable(false);
		convertView.setClickable(true);
		return convertView;
	}

	public void updatePlantList(List<Green> newAdd, List<Green> newAll) {
		added.clear();
		all.clear();
		added.addAll(newAdd);
		all.addAll(newAll);
		filteredAdded = added;
		filteredAll = all;
		this.notifyDataSetChanged();
	}

	@Override
	public View getChildView(int groupPosition, int childPosition,
			boolean isLastChild, View convertView, ViewGroup parent) {

		if (convertView == null) {
			LayoutInflater infalInflater = (LayoutInflater) context
					.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			convertView = infalInflater
					.inflate(R.layout.plant_row_layout, null);
		}

		TextView plantName = (TextView) convertView
				.findViewById(R.id.plant_name);
		TextView plantReminingTime = (TextView) convertView
				.findViewById(R.id.plant_remaining_time);
		ImageView plantImage = (ImageView) convertView
				.findViewById(R.id.plant_image);
		Green plant = null;
		switch (groupPosition) {
		case 0:
			plant = filteredAdded.get(childPosition);
			convertView.setTag(true);
			break;
		case 1:
			plant = filteredAll.get(childPosition);
			convertView.setTag(false);
			break;
		}

		plantName.setText(plant.getName());
		plantReminingTime.setText(R.string.app_name); // TODO - Change text
													// according to remaining
													// time
		Log.d("TAG",plant.getExistingId()+" "+plant.getId());
		plantImage.setImageResource(getImageResource(plant.getName()));
		return convertView;
	}

	@Override
	public boolean isChildSelectable(int groupPosition, int childPosition) {
		return true;
	}
	
private int getImageResource(String name){
		
		if (name.equalsIgnoreCase("potatoes")){
			return R.drawable.ic_potatoes;
		} else if (name.equalsIgnoreCase("carrots")){
			return R.drawable.ic_carrot;
		} else if (name.equalsIgnoreCase("tomatoes")){
			return R.drawable.ic_tomatoes;
		} else if (name.equalsIgnoreCase("lettuces")){
			return R.drawable.ic_lettuce;
		} else if (name.equalsIgnoreCase("sweet peas")){
			return R.drawable.ic_sweat_pea;
		} else {
			return R.drawable.ic_lillies;
		}
	}

}
