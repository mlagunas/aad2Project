package com.example.aad2project.adapter;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.List;
import java.util.Locale;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

import com.example.aad2project.R;
import com.example.aad2project.object.TaskPlant;

public class TaskCalendarAdapter extends BaseExpandableListAdapter {

	private List<List<TaskPlant>> mDays;
	private Context context;

	public TaskCalendarAdapter(Context context, List<TaskPlant> mTasks) {
		this.context = context;
		Collections.sort(mTasks);
		mDays = divideTaskPerDays(mTasks);
	}

	private List<List<TaskPlant>> divideTaskPerDays(List<TaskPlant> mTasks) {
		List<List<TaskPlant>> mDays = new ArrayList<List<TaskPlant>>();
		List<TaskPlant> mDayTasks = new ArrayList<TaskPlant>();
		Calendar lastDate = Calendar.getInstance();
		Calendar date = Calendar.getInstance();
		// TaskPlant lastTask = null;
		for (TaskPlant mTask : mTasks) {
			date.setTime(mTask.getDate());
			if (date.get(Calendar.DAY_OF_YEAR) != lastDate
					.get(Calendar.DAY_OF_YEAR)) {// TODO - Properly divide the
													// tasks
				if (mDayTasks.size() > 0) { // into days when the class is fixed
					mDays.add(mDayTasks);
				}
				mDayTasks = new ArrayList<TaskPlant>();
			}
			mDayTasks.add(mTask);
			// lastTask = mTask;
			lastDate.setTime(mTask.getDate());
		}

		if (mDayTasks.size() > 0)
			mDays.add(mDayTasks);
		return mDays;
	}

	@Override
	public int getGroupCount() {
		return mDays.size();
	}

	@Override
	public int getChildrenCount(int groupPosition) {
		return mDays.get(groupPosition).size();
	}

	@Override
	public Object getGroup(int groupPosition) {
		return mDays.get(groupPosition);
	}

	@Override
	public Object getChild(int groupPosition, int childPosition) {
		return mDays.get(groupPosition).get(childPosition);
	}

	@Override
	public long getGroupId(int groupPosition) {
		return groupPosition;
	}

	@Override
	public long getChildId(int groupPosition, int childPosition) {

		return childPosition;
	}

	public void updateTaskList(List<TaskPlant> newList) {
		mDays.clear();
		Collections.sort(newList);
		mDays = divideTaskPerDays(newList);

		notifyDataSetChanged();

	}

	@Override
	public boolean hasStableIds() {
		return false;
	}

	@Override
	public View getGroupView(int groupPosition, boolean isExpanded,
			View convertView, ViewGroup parent) {
		if (convertView == null) {
			LayoutInflater infalInflater = (LayoutInflater) context
					.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			convertView = infalInflater.inflate(R.layout.task_row_layout_group,
					null);
		}

		TextView groupTitle = (TextView) convertView
				.findViewById(R.id.group_title);

		SimpleDateFormat dateFormat = new SimpleDateFormat("EEEE, dd MMMM",
				Locale.ENGLISH);
		if (!mDays.isEmpty())
			groupTitle.setText(dateFormat.format(mDays.get(groupPosition)
					.get(0).getDate()));

		// TODO - Change title for
		// Date
		// once Task class is fixed

		convertView.setLongClickable(false);
		convertView.setFocusable(false);
		convertView.setClickable(true);
		return convertView;
	}

	@Override
	public View getChildView(int groupPosition, int childPosition,
			boolean isLastChild, View convertView, ViewGroup parent) {

		if (convertView == null) {
			LayoutInflater infalInflater = (LayoutInflater) context
					.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			convertView = infalInflater.inflate(R.layout.task_row_layout, null);
		}

		TextView taskDescription = (TextView) convertView

		.findViewById(R.id.task_name);
		TextView taskTarget = (TextView) convertView
				.findViewById(R.id.task_target);

		TaskPlant task = (TaskPlant) getChild(groupPosition, childPosition);
		taskDescription.setText(task.getTask().getDescription());

		return convertView;
	}

	@Override
	public boolean isChildSelectable(int groupPosition, int childPosition) {
		return true;
	}

}
