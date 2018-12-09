package tests;

import gui.InitialScreen;
import model.HotelSystem;
import model.Room;

/**
 * Launches the initial screen.
 */
public class Launcher {
	public static void main(String args[]) {
		int screenSize = 750;
		HotelSystem hs = new HotelSystem();
		// add 10 economic rooms and 10 luxurious rooms
		for (int i = 1; i <= 20; i++) {
			Room.Type type = Room.Type.ECONOMIC;
			if (i > 10) { type = Room.Type.LUXURIOUS; }
			hs.addRoom(new Room(i, type));
		}
		InitialScreen screen = new InitialScreen(screenSize, hs);
		
	}
}
