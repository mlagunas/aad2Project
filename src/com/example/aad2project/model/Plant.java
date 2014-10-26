package com.example.aad2project.model;

public class Plant {

	private int id, timeToGrow, number, weather;

	private String description, name;
	
	private Plant next;

	public Plant() {
	}
	
	public Plant(int id, String name) {
		this.id 		 = id;
		this.name        = name;
	}

	public void setId(int id) {
		this.id = id;
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

	public int getWeatherId() {
		return weather;
	}

	public void setWeatherId(int weather) {
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
	
	public void setNumber(int number){
		this.number=number;
	}
	
	public int getNumber(){
		return number;
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