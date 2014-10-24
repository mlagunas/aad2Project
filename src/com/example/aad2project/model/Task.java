package com.example.aad2project.model;

public class Task {
	
	private int id;
	private String description;
	private Task next;
	
	public Task() {
	}
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;

	}
	
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	public Task getNext() {
		return next;
	}
	
	public void setNext(Task next) {
		this.next = next;
	}
	
	public void addTaskToQueue(Task newTask) {
		if (this.next == null) {
			this.next = newTask;
		} else {
			this.next.addTaskToQueue(newTask);
		}
	}
	
	public Task getTaskNumber(int i) {
		Task task = this;
		
		for (int j = 0; j < i; j++) {
			task = task.next;
		}
		
		return task;
	}
	
	public int size() {
		int j = 1;
		Task task = this;
		do {
			j++;
		} while(task.next != null);
		return j;
	}
	
	public String toString(){
		return id+" "+description;
	}
}
