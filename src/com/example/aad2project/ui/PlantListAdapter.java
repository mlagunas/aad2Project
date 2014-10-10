package com.example.aad2project.ui;

import java.util.List;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

import com.example.aad2project.R;
import com.example.aad2project.model.Plant;

public class PlantListAdapter extends BaseExpandableListAdapter {
	
	private List<Plant> added;
	private List<Plant> all;
	private Context context;

	public PlantListAdapter(Context context, List<Plant> added, List<Plant> all) {
		this.context = context;
		this.added = added;
		this.all = all;
	}

	@Override
	public int getGroupCount() {
		return 2;
	}

	@Override
	public int getChildrenCount(int groupPosition) {
		switch (groupPosition) {
		case 0:
			return added.size();
		case 1:
			return all.size();
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
			return added.get(childPosition);
		case 1:
			return all.get(childPosition);
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
		Plant plant = null;
		switch (groupPosition) {
		case 0:
			plant = added.get(childPosition);
			convertView.setTag(true);
			break;
		case 1:
			plant = all.get(childPosition);
			convertView.setTag(false);
			break;
		}

		plantName.setText(plant.getName());

		return convertView;
	}

	@Override
	public boolean isChildSelectable(int groupPosition, int childPosition) {
		return true;
	}
	
}
