package model;

/**
 * Models a hotel room for the hotel reservation system. Each room 
 * keeps track of its number and rate. 
 */
public class Room {
	private int number;
	private int rate;
	
	public Room(int number, int rate) {
		this.number = number;
		this.rate = rate;
	}
	
	public int getNumber() {
		return number;
	}
	
	public int getRate() {
		return rate;
	}
}
