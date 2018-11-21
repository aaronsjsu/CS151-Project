package model;

/**
 * Models a hotel room for the hotel reservation system. Each room 
 * keeps track of its number and rate. 
 */
public final class Room
{
	/* Number of the room. */
	private final int number;
	/* Rate at which is charged for the room. */
	private final int rate;

	/**
	 * Constructs a room inside the hotel.
	 * @param number Room number of the hotel.
	 * @param rate Rate to charge for the room.
	 */
	public Room(final int number, final int rate)
	{
		if (number < 0) throw new IllegalArgumentException("Room number must be positive!");
		if (rate < 0) throw new IllegalArgumentException("Going rate for the room must be positive!");
		this.number = number;
		this.rate = rate;
	}

	/**
	 * @return Number of the room.
	 */
	public int getNumber()
	{
		return number;
	}

	/**
	 * @return Rate which is charged for the room.
	 */
	public int getRate() 
	{
		return rate;
	}
}
