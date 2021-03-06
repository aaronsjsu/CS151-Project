package model;

import java.io.Serializable;
import java.util.Objects;

/**
 * Models a hotel room for the hotel reservation system. Each room 
 * keeps track of its number and rate. 
 */
public class Room implements Comparable<Room>, Serializable
{
	/* Room number. */
	private final int number;
	/* Room type classification. */
	private final Type type;

	/**
	 * Constructs a room inside the hotel.
	 * @param number Room number of the hotel.
     * @param type Type of room in the hotel.   
	 */
	public Room(final int number, final Type type)
	{
		if (number < 0) throw new IllegalArgumentException("Room number must be positive!");
		this.number = number;
		this.type = Objects.requireNonNull(type);
	}

	/**
	 * @return Number of the room.
	 */
	public int getNumber()
	{
		return number;
	}

    /**
     * @return Type of the room.
     */
	public Type getType()
    {
        return type;
    }

    /**
     * Compares this object with the specified object for order.
     * 
     * @param o the object to be compared.
     * @return a negative integer, zero, or a positive integer as this object
     * is less than, equal to, or greater than the specified object.
     */
    @Override public int compareTo(final Room o)
    {
        return Integer.compare(number, o.number);
    }

    /**
     * @return String representation of the room.
     */
    @Override public String toString()
    {
        return "Room " + number + ", " + type;
    }

    /**
     * Defines a classification for the types of rooms within the hotel system.
     */
    public enum Type
    {
        LUXURIOUS("Luxurious", 300),
        ECONOMIC("Economic", 100);
        
        /* Name of the type of room. */
        private final String name;
        /* Going rate of the type of room. */
        private final int rate;

        /* Constructs a room type with a specified name and going rate. */
        Type(final String name, final int rate)
        {
            assert name != null;
            assert rate >= 0;
            this.name = name;
            this.rate = rate;
        }

        /**
         * @return Name of the type of room.
         */
        public String getName()
        {
            return name;
        }

        /**
         * @return Going rate of the type of room.
         */
        public int getRate()
        {
            return rate;
        }
    }
}
