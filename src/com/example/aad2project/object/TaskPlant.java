package com.example.aad2project.object;

import java.util.Comparator;
import java.util.Date;

public class TaskPlant implements Comparable<TaskPlant> {
	private int done;
	private Date date;
	private Task task;
	private Green plant;
	private TaskPlant next;
	private int idPlant;
	private int idTask;
	private int id;

	public TaskPlant() {
	}

	public TaskPlant(Green plant, Task task) {
		this.plant = plant;
		this.task = task;
	}

	public int getIdPlant() {
		return idPlant;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getId() {

		return id;
	}

	public int getIdTask() {
		return idTask;
	}

	public void setIdPlant(int id) {
		this.idPlant = id;
	}

	public void setIdTask(int id) {
		this.idTask = id;
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

	public Green getPlant() {
		return plant;
	}

	public void setPlant(Green plant) {
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

	public TaskPlant getTaskPlantNumber(int i) {
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
		} while (taskPlant.next != null);

		return j;
	}

	/*public String toString() {
		return plant.getId() + " " + plant.getName() + " " + task.getId()
				+ task.getDescription() + " " + date.toString() + " " + done
				+ '\n';
	}*/

	@Override
	public int compareTo(TaskPlant o) {
		return getDate().compareTo(o.getDate()) == 1 ? 0 : 1;
	}

}
