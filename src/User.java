import java.util.List;
import java.util.ArrayList;

/**
 * Models a user account in the hotel reservation system. Each account keeps track of a user id, 
 * password, username, and all reservations that the user has made (excluding canceled ones). 
 */
public class User {
	private String userID;
	private String password;
	private String username;
	private List<Reservation> reservations;
	
	public User(String userID, String password, String username) {
		this.userID = userID;
		this.password = password;
		this.username = username;
		reservations = new ArrayList<Reservation>();
	}
	
	public void addReservation(Reservation r) {
		reservations.add(r);
	}
	
	public String getUserID() {
		return userID;
	}
	
	public String getPassword() {
		return password;
	}
	
	public String getUsername() {
		return username;
	}
	
	public List<Reservation> getReservations() {
		return reservations;
	}
}
