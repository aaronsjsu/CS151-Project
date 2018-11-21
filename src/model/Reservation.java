package model;

import java.time.LocalDate;

/**
 * Models a user reservation in the hotel reservation system. Each reservation record 
 * knows the User that made the reservation, which room the reservation is for, and 
 * the time period for the reservation.
 */
public class Reservation {
	private User user;
	private Room room;
	private LocalDate startTime;
	private LocalDate endTime;
	
	public Reservation() {
		
	}
	
	public User getUser() {
		return user;
	}
	
	public Room getRoom() {
		return room;
	}
	
}
