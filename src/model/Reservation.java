package model;

import java.time.LocalDate;

/**
 * Models a user reservation in the hotel reservation system. Each reservation record 
 * knows the User that made the reservation, which room the reservation is for, and 
 * the time period for the reservation.
 */
public class Reservation
{
	private User user;
	private Room room;
	private LocalDate startDate;
	private LocalDate endDate;

    /**
     * The maximum amount of days a reservation can last for.
     */
	public static final int MAXIMUM_DURATION_DAYS = 60;
	
	public Reservation(User user, Room room, LocalDate startDate, LocalDate endDate) {
		this.user = user;
		this.room = room;
		this.startDate = startDate;
		this.endDate = endDate;
	}
	
	public User getUser() {
		return user;
	}
	
	public Room getRoom() {
		return room;
	}
	
	public LocalDate getStartDate() {
		return startDate;
	}
	
	public LocalDate getEndDate() {
		return endDate;
	}
}
