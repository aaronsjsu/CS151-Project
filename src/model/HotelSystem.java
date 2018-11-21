package model;

import java.util.List;

/**
 * Models the system itself for the hotel reservation system. This keeps track of 
 * all users, reservations, rooms, etc.
 */
public class HotelSystem
{
	public List<User> users;
	public List<Reservation> reservations;
	public List<Room> rooms;
	
    /** Maximum number of rooms available in the hotel at any given time. */
    public static final int MAXIMUM_VACANCY = 20;
    /** Number of luxurious rooms available in the hotel. */
    public static final int LUXURIOUS_ROOMS = 10;
	
	public HotelSystem() {
		
	}
}
