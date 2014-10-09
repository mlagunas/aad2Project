package com.example.aad2project.model;

public class Plant {

	private int id, timeToGrow;

	private String description, name;
	private Weather weather;
	
	private Plant next;

	public Plant() {
	}

	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDescription() {
		return description;
	}

	public int getTimeToGrow() {
		return timeToGrow;
	}

	public void setTimeToGrow(int timeToGrow) {
		this.timeToGrow = timeToGrow;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Weather getWeatherId() {
		return weather;
	}

	public void setWeatherId(Weather weather) {
		this.weather = weather;
	}

	public Plant getNext() {
		return next;
	}
	
	public void setNext(Plant next) {
		this.next = next;
	}
	
	public void addPlantToQueue(Plant newPlant) {
		if (this.next == null) {
			this.next = newPlant;
		} else {
			this.next.addPlantToQueue(newPlant);
		}
	}
	
	public Plant getPlantNumber(int i) {
		Plant plant = this;
		
		for (int j = 0; j < i; j++) {
			plant = plant.next;
		}
		
		return plant;
	}
	
	public int size() {
		int j = 1;
		Plant plant = this;
		do {
			j++;
		} while(plant.next != null);
		return j;
	}
}