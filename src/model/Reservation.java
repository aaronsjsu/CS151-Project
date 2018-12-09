package model;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

/**
 * Models a user reservation in the hotel reservation system. Each reservation record 
 * knows the User that made the reservation, which room the reservation is for, and 
 * the time period for the reservation.
 */
public class Reservation implements Serializable
{
	/* User who made the reservation. */
	private final User user;
	/* Room the reservation corresponds to. */
	private final Room room;
	/* Interval in which the reservation will last for. */
	private final TimeInterval interval;
	/* The date in which the reservation was made. */
	private final LocalDate dateCreated = LocalDate.now();

    /**
     * The maximum amount of days a reservation can last for.
     */
	public static final int MAXIMUM_DURATION_DAYS = 60;

	/**
	 * Constructs a reservation with a specified user, room, and time interval.
	 * 
	 * @param user User who owns the reservation.
	 * @param room Room in which the reservation corresponds to.
	 * @param interval Interval of time which the reservation lasts for.
	 */
	public Reservation(final User user, final Room room, final TimeInterval interval)
	{
		this.user = Objects.requireNonNull(user);
		this.room = Objects.requireNonNull(room);
		this.interval = Objects.requireNonNull(interval);
	}

	/**
	 * @return Owner of the reservation.
	 */
	public User getUser()
	{
		return user;
	}

	/**
	 * @return Room of the reservation.
	 */
	public Room getRoom() 
	{
		return room;
	}

	/**
	 * @return Interval of time in which the reservation lasts for.
	 */
	public TimeInterval getInterval()
	{
		return interval;
	}
	
	/**
	 * @return Returns the interval of the reservation as a string.
	 */
	public String toString() {
		return interval.toString();
	}

	/**
	 * @return Date in which the reservation was made.
	 */
	public LocalDate getDateCreated()
	{
		return dateCreated;
	}
}
