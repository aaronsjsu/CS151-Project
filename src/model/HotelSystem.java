package model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import model.observable.*;

/**
 * Models the system itself for the hotel reservation system. This keeps track of 
 * all users, reservations, rooms, etc.
 */
public class HotelSystem implements Observable<HotelSystem> {
	private HashMap<String, User> users;
	private List<Reservation> reservations;
	private List<Room> rooms;
	private List<ChangeListener> listeners;
	private LocalDate selectedDate;
	private Room selectedRoom;
	
    /** Maximum number of rooms available in the hotel at any given time. */
    public static final int MAXIMUM_VACANCY = 20;
    /** Number of luxurious rooms available in the hotel. */
    public static final int LUXURIOUS_ROOMS = 10;
	
	public HotelSystem() {
		users = new HashMap<>();
		reservations = new ArrayList<>();
		rooms = new ArrayList<>();
		listeners = new ArrayList<>();
		selectedDate = LocalDate.now();
	}

	public void addReservation(Reservation r ) {
		reservations.add(r);
	}

	public void addRoom(Room r) {
		rooms.add(r);
	}

	public void addUser(String id, String userName, String password) {
		User newUser = new User(id, userName, password);
		users.put(newUser.getID(), newUser);
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

	@Override
	public void addListener(ChangeListener listener) {
		listeners.add(listener);
	}

	@Override
	public boolean removeListener(ChangeListener listener) {
		return listeners.remove(listener);
	}
	
	public void updateListeners() {
		for (ChangeListener l : listeners) {
			l.fire();
		}
	}
}
