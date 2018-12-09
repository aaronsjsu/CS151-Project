package model;

import model.contracts.Observable;
import model.contracts.Savable;

import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Defines a data structure which manages rooms and reservations.
 */
public class HotelSystem implements Savable, Observable
{
	/* Maps user IDs to user accounts. */
	private final Map<String, User> users = new HashMap<>();
	/* Three dimensional map - For each room type, tracks each room's current reservations. */
	private final Map<Room.Type, Map<Room, TreeSet<Reservation>>> roomReservations;
	/* Listeners listening for changes to the hotel system. */
	private final Set<ChangeListener> listeners = new HashSet<>();
	
	/** Read-only map dictating the number of initial vacancies per room-type. */
	public static final Map<Room.Type, Integer> roomTypeVacancies = Stream.of(
			new AbstractMap.SimpleEntry<>(Room.Type.ECONOMIC, 10),
			new AbstractMap.SimpleEntry<>(Room.Type.LUXURIOUS, 10))
			.collect(Collectors.collectingAndThen(
					Collectors.toMap(AbstractMap.SimpleEntry::getKey, AbstractMap.SimpleEntry::getValue),
					Collections::unmodifiableMap));
	/** Total number of initial vacancies of the hotel. */
	public static final int HOTEL_TOTAL_VACANCIES = roomTypeVacancies.entrySet().stream()
			.mapToInt(Map.Entry::getValue)
			.sum();
	
	/* All reservations are saved to the storage medium under this name. */
	private static final String SAVE_FILE_NAME = "Reservations.ser";

	/**
	 * Constructs a default hotel system.
	 */
	public HotelSystem()
	{
		roomReservations = new HashMap<>(roomTypeVacancies.size());
		int roomNumberCounter = 1;
		for (final Map.Entry<Room.Type, Integer> e : roomTypeVacancies.entrySet())
		{
			final Room.Type type = e.getKey();
			final int rooms = e.getValue();
			final Map<Room, TreeSet<Reservation>> res = new LinkedHashMap<>(rooms);
			for (int i = 0; i < rooms; i++)
			{
				final Room r = new Room(roomNumberCounter++, type);
				res.put(r, new TreeSet<>());
			}
		}
	}

	/**
	 * Adds a listener to listen for changes from the observable.
	 *
	 * @param listener Listener to be notified of changes.
	 */
	@Override public void addListener(ChangeListener listener)
	{
		listeners.add(Objects.requireNonNull(listener));
	}

	/**
	 * Removes a listener from the observable.
	 * After the operation, the listener will no longer be notified.
	 *
	 * @param listener Listener to remove.
	 * @return true if the listener was successfully removed.
	 */
	@Override public boolean removeListener(ChangeListener listener)
	{
		return listeners.remove(Objects.requireNonNull(listener));
	}

	/**
	 * Saves this object to the storage medium.
	 * Implementations should call Savable.save and
	 * Savable.load for simplified saving and loading.
	 *
	 * @return true if the object was successfully saved.
	 */
	@Override public boolean save()
	{
		return Savable.save(this, SAVE_FILE_NAME);
	}

	/* DEPRECATED BELOW */

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
