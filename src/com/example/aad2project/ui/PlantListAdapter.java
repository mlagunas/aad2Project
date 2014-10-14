package com.example.aad2project.ui;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.aad2project.R;
import com.example.aad2project.model.Plant;

public class PlantListAdapter extends BaseExpandableListAdapter {

	private List<Plant> added;
	private List<Plant> filteredAdded;
	private List<Plant> all;
	private List<Plant> filteredAll;
	private Context context;

	public PlantListAdapter(Context context, List<Plant> added, List<Plant> all) {
		this.context = context;
		this.added = added;		
		this.all = all;
		filteredAdded = added;
		filteredAll = all;
	}

	public void filter(String s) {
		if (s == "") {
			filteredAdded = added;
			filteredAll = all;
		} else {
			filteredAdded = new ArrayList<Plant>();
			filteredAll = new ArrayList<Plant>();
			for (Plant p : added) {
				if (p.getName().toLowerCase().startsWith(s.toLowerCase())){
					filteredAdded.add(p);
				}
			}
			for (Plant p : all) {
				if (p.getName().toLowerCase().startsWith(s.toLowerCase())){
					filteredAll.add(p);
				}
			}
		}
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
		convertView.setLongClickable(false);
		return convertView;
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
		Plant plant = null;
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
		plantReminingTime.setText("2 weeks left"); // TODO - Change text
													// according to remaining
													// time
		plantImage.setImageResource(R.drawable.plant_image); // TODO - Change
																// image
																// according to
																// plant

		return convertView;
	}

	@Override
	public boolean isChildSelectable(int groupPosition, int childPosition) {
		return true;
	}

}
