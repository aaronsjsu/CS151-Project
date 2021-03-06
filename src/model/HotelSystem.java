package model;

import model.contracts.Observable;
import model.contracts.Savable;
import model.receipt.Receipt;

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
	private final Map<Room.Type, Map<Room, TreeMap<TimeInterval, Reservation>>> roomReservations;
	/* Listeners listening for changes to the hotel system. */
	private final transient Set<ChangeListener> listeners = new HashSet<>();
	
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

	private String[] availableRooms;
	private LocalDate selectedDate;
	private Room selectedRoom;
	public Receipt receipt;
	
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
			final Map<Room, TreeMap<TimeInterval, Reservation>> res = new LinkedHashMap<>(rooms);
			for (int i = 0; i < rooms; i++)
			{
				final Room r = new Room(roomNumberCounter++, type);
				res.put(r, new TreeMap<>());
			}
			roomReservations.put(type, res);
		}
		availableRooms = new String[roomTypeVacancies.size()];
		selectedDate = LocalDate.now();
	}

	/**
	 * Adds a reservation to the hotel system.
	 * Notifies all listeners of a change to the hotel system.
	 * 
	 * @param reservation Reservation to add.
	 */
	public void addReservation(final Reservation reservation)
	{
		final Room r = Objects.requireNonNull(reservation.getRoom());
		final User u = reservation.getUser();
		final TimeInterval ti = reservation.getInterval();
		if (!roomIsAvailable(r, ti))
			throw new IllegalArgumentException("Room cannot be reserved!");
		final Map<Room, TreeMap<TimeInterval, Reservation>> res = roomReservations.get(r.getType());
		final Map<TimeInterval, Reservation> schedule = res.get(r);
		schedule.put(ti, reservation);
		/* Inform the user he has just made a reservation. */
		u.addReservation(reservation);
		/* Notify listeners that a reservation was added. */
		listeners.forEach(l -> l.stateChanged(new ChangeEvent(this)));
		save();
	}
	
	public String[] getAvailableRooms() {
		return availableRooms;
	}
	
	/**
	 * Retrieves all rooms of a specified type which are available in a specified time interval.
	 * 
	 * @param type Type of room to search for.
	 * @param interval Time interval in which the room must be available.
	 * @return Set of all available rooms.
	 */
	public void setAvailableRooms(final Room.Type type, final TimeInterval interval)
	{
		final Map<Room, TreeMap<TimeInterval, Reservation>> roomRes = 
				roomReservations.get(Objects.requireNonNull(type));
		assert roomRes != null;
		Objects.requireNonNull(interval);
		final Set<Room> output = new LinkedHashSet<>();
		for (final Map.Entry<Room, TreeMap<TimeInterval, Reservation>> e : roomRes.entrySet())
		{
			final Room r = e.getKey();
			if (roomIsAvailable(r, interval))
				output.add(r);
		}
		
		availableRooms = output.stream().map(o -> String.valueOf(o.getNumber())).toArray(String[]::new);

		ChangeEvent changeEvent = new ChangeEvent(this);
		for (ChangeListener listener : listeners) {
			listener.stateChanged(changeEvent);
		}

	}

	/**
	 * @return Set of all rooms in the hotel system.
	 */
	public Set<Room> getRooms()
	{
		return roomReservations.values().stream()
				.map(Map::keySet)
				.flatMap(Set::stream)
				.collect(Collectors.toCollection(LinkedHashSet::new));
	}
	
	/*
	 * @return Set of all reservations in the hotel system.
	 */
	public Set<Reservation> getReservations() {
		//TODO
		return null;
	}

	/**
	 * Determines if a specified room is available during a specified time interval.
	 * 
	 * @param room Room to check vacancy of.
	 * @param interval Time interval which is checked.
	 * @return true if the room is available during the time interval.
	 */
	public boolean roomIsAvailable(final Room room, final TimeInterval interval)
	{
		final Room.Type t = Objects.requireNonNull(room).getType();
		final Map<Room, TreeMap<TimeInterval, Reservation>> roomRes =
				roomReservations.get(Objects.requireNonNull(t));
		assert roomRes != null;
		final TreeMap<TimeInterval, Reservation> schedule = roomRes.get(room);
		final TimeInterval floorI = schedule.floorKey(Objects.requireNonNull(interval));
		if (floorI != null && !floorI.end.isBefore(interval.start))
			return false;
		final TimeInterval ceilI = schedule.ceilingKey(interval);
		return ceilI == null || ceilI.start.isAfter(interval.end);
	}

	/**
	 * Adds a user to the hotel system.
	 * 
	 * @param user User to be added.
	 */
	public void addUser(final User user)
	{
		final String id = Objects.requireNonNull(user).getID();
		if (userExists(id))
			throw new IllegalStateException("The specified user already exists!");
		users.put(id, user);
		save();
	}

	/**
	 * @param id ID to check.
	 * @return true if a user exists with the specified ID.
	 */
	public boolean userExists(final String id)
	{
		return users.containsKey(Objects.requireNonNull(id));
	}

	/**
	 * Retrieves a user account with the specified credentials.
	 * 
	 * @param id ID of the user.
	 * @param password Password of the user.
	 * @return User account with the specified credentials, or null if none exist.
	 */
	public User getUser(final String id, final String password)
	{
		final User u = users.get(Objects.requireNonNull(id));
		return u != null && u.validatePassword(Objects.requireNonNull(password)) ? u : null;
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
	 * Implementations should call Savable.save andse
	 * Savable.load for simplified saving and loading.
	 *
	 * @return true if the object was successfully saved.
	 */
	@Override public boolean save()
	{
		return Savable.save(this, SAVE_FILE_NAME);
	}

	/**
	 * Loads this hotel system from the storage medium.
	 * 
	 * @return Hotel system from the storage medium, or null.
	 */
	public static HotelSystem load()
	{
		return Savable.load(SAVE_FILE_NAME);
	}

	/**
	 * Sets the currently selected date. Used for mvc pattern.
	 * @param date The date to set the selected date to.
	 */
	public void setSelectedDate(LocalDate date) {
		this.selectedDate = date;
		listeners.forEach(l -> l.stateChanged(new ChangeEvent(this)));
	}

	/**
	 * Returns the currently selected date.
	 * @return The currently selected date.
	 */
	public LocalDate getSelectedDate() {
		return selectedDate;
	}
	
	/**
	 * Sets the currently selected room. Used for mvc pattern.
	 * @param r The room to assign to the selected room.
	 */
	public void setSelectedRoom(Room r) {
		this.selectedRoom = r;
		listeners.forEach(l -> l.stateChanged(new ChangeEvent(this)));
	}

	/**
	 * Returns the currently selected room.
	 * @return The currently selected room.
	 */
	public Room getSelectedRoom() {
		return selectedRoom;
	}
}
