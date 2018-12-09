package model;

import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.time.LocalDate;
import java.util.*;

/**
 * Models the system itself for the hotel reservation system. This keeps track of 
 * all users, reservations, rooms, etc.
 */
public class HotelSystem
{
	/* Maps user IDs to user accounts. */
	private final Map<String, User> users;
	/* Three dimensional map - For each room type, tracks each room's current reservations. */
	private final Map<Room.Type, Map<Room, TreeSet<Reservation>>> roomReservations;
	/* Listeners listening for changes to the hotel system. */
	private final Set<ChangeListener> listeners = new HashSet<>();
	
    /** Maximum number of rooms available in the hotel at any given time. */
    public static final int MAXIMUM_VACANCY = 20;
    /** Number of luxurious rooms available in the hotel. */
    public static final int LUXURIOUS_ROOMS = 10;
	
	public HotelSystem()
	{
		users = new HashMap<>();
		
		reservations = new ArrayList<>();
		rooms = new ArrayList<>();
		listeners = new ArrayList<>();
		selectedDate = LocalDate.now();
		availableRooms = new String[MAXIMUM_VACANCY];
	}

	public void addReservation(Reservation r ) {
		reservations.add(r);
	}

	public void addRoom(Room r) {
		rooms.add(r);
	}

  public void addUser(User user) {
	  users.put(user.getID(), user);
  }

	public Boolean isUserIdExisted(String id) {
		return this.users.containsKey(id);
	}
	
	public User getUser(String id, String password) {
		return (!isUserIdExisted(id) || !users.get(id).validatePassword(password)) ? null : users.get(id);
	}

	public List<Room> getRooms() {
		return rooms;
	}
	
	public List<Reservation> getReservations() {
		return reservations;
	}
	
	public void setSelectedDate(LocalDate date) {
		selectedDate = date;
	}
	
	public LocalDate getSelectedDate() {
		return selectedDate;
	}
	
	public void setSelectedRoom(Room r) {
		selectedRoom = r;
	}
	
	public Room getSelectedRoom() {
		return selectedRoom;
	}

//	@Override
//	public void addListener(ChangeListener listener) {
//		listeners.add(listener);
//	}
//
//	@Override
//	public boolean removeListener(ChangeListener listener) {
//		return listeners.remove(listener);
//	}
//
//	public void updateListeners() {
//		for (ChangeListener l : listeners) {
//			l.fire();
//		}
//	}

	// Kevin I want you add find available rooms method here.
	public void setAvailableRooms(String roomType, LocalDate start, LocalDate end) {

		this.availableRooms[0] = "12141";
		if(roomType != "Economic") this.availableRooms[1] = "1111";
		else this.availableRooms[0] = "22222";
		ChangeEvent event = new ChangeEvent(this);
		for (ChangeListener listener : listeners)
			listener.stateChanged(event);
	}

	public void addChangeListener(ChangeListener listener)
	{
		listeners.add(listener);
	}



	public String[] getAvailableRooms() {
		return availableRooms;
	}

	public void setAvailableRooms(String[] availableRooms) {
		this.availableRooms = availableRooms;
	}
}
