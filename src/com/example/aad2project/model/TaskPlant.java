package com.example.aad2project.model;

import java.util.Date;

public class TaskPlant {
	private int id,done;
	private Date date;
	private Task task;
	private Plant plant;
	private TaskPlant next;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getDone() {
		return done;
	}
	public void setDone(int done) {
		this.done = done;
	}	
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public Task getTask() {
		return task;
	}
	public void setTask(Task task) {
		this.task = task;
	}
	public Plant getPlant() {
		return plant;
	}
	public void setPlant(Plant plant) {
		this.plant = plant;
	}
	
	public TaskPlant getNext() {
		return next;
	}
	
	public void setNext(TaskPlant next) {
		this.next = next;
	}
	
	public void addTaskPlantToQueue(TaskPlant taskPlant) {
		if (this.next == null) {
			this.next = taskPlant;
		} else {
			this.next.addTaskPlantToQueue(taskPlant);
		}
	}
	
	public TaskPlant getPlantNumber(int i) {
		TaskPlant taskPlant = this;
		
		for (int j = 0; j < i; j++) {
			taskPlant = taskPlant.next;
		}
		
		return taskPlant;
	}
	
	public int size() {
		int j = 0;
		TaskPlant taskPlant = this;
		
		do {
			j++;
		} while(taskPlant.next != null);
		
		return j;
	}
	


}
