import java.util.List;

/**
 * Models the system itself for the hotel reservation system. This keeps track of 
 * all users, reservations, rooms, etc.
 */
public class HotelSystem {
	public List<User> users;
	public List<Reservation> reservations;
	public List<Room> rooms;
	
	public HotelSystem() {
		
	}
}
