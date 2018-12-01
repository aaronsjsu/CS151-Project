package model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Models the system itself for the hotel reservation system. This keeps track of 
 * all users, reservations, rooms, etc.
 */
public class HotelSystem
{
	private HashMap<String, User> users;
	private List<Reservation> reservations;
	private List<Room> rooms;
	
    /** Maximum number of rooms available in the hotel at any given time. */
    public static final int MAXIMUM_VACANCY = 20;
    /** Number of luxurious rooms available in the hotel. */
    public static final int LUXURIOUS_ROOMS = 10;
	
	public HotelSystem() {
	  users = new HashMap<>();
	  reservations = new ArrayList<>();
	  rooms = new ArrayList<>();
		
	}

	public void addReservation(Reservation r ) {
	  reservations.add(r);
  }

  public void addRoom(Room r) {
	  rooms.add(r);
  }

  public void addUser(String id,String userName, String password) {
		User newUser = new User(id, userName, password);
	  users.put(newUser.getID(), newUser);
  }

  public Boolean isUserIdExisted(String id) {
	  return this.users.containsKey(id);
  }
  public User getUser(String id, String password) {
		return (!isUserIdExisted(id) || !users.get(id).validatePassword(password)) ? null : users.get(id);


	}

}
