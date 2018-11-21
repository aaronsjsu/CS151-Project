package model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

/**
 * Models a user account in the hotel reservation system. Each account keeps track of a user id, 
 * password, username, and all reservations that the user has made (excluding canceled ones). 
 */
public final class User implements Serializable
{
    /* Core user credentials. */
	private final String id, username;
	/* Hashed password used to authenticate login. */
	private final byte[] password;
	/* Reservations corresponding to the user. */
	private final List<Reservation> reservations = new ArrayList<>();
	
    /**
     * Constructs a user with the specified credentials.
     * Plaintext passwords are not stored with the user.
     * @param id ID of the user.
     * @param username Username used for login purposes.
     * @param password Password used for login purposes.
     */
	public User(final String id, final String username, final String password)
    {
        this.id = Objects.requireNonNull(id);
        this.username = Objects.requireNonNull(username);
        this.password = Security.hash(Objects.requireNonNull(password));
	}

    /**
     * Adds a reservation to the user's account.
     * @param reservation Reservation to be added.
     */
	public void addReservation(final Reservation reservation)
    {
        reservations.add(Objects.requireNonNull(reservation));
	}

    /**
     * Indicates if a specified password is valid for this user.
     * @param password Password to be validated.
     * @return true if the password matches the user's credentials.
     */
	public boolean validatePassword(final String password)
    {
        return Arrays.equals(Security.hash(Objects.requireNonNull(password)), this.password);
    }

    /**
     * @return ID of the user.
     */
	public String getID()
    {
		return  id;
	}

    /**
     * @return Username of the user.
     */
	public String getUsername()
    {
		return username;
	}

    /**
     * @return Read-only view of the reservations the user has made.
     */
	public List<Reservation> getReservations()
    {
		return Collections.unmodifiableList(reservations);
	}
}
