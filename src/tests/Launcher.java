package tests;

import gui.InitialScreen;
import model.HotelSystem;

/**
 * Launches the initial screen.
 */
public class Launcher {
	public static void main(String args[]) {
		int screenSize = 300;
		InitialScreen screen = new InitialScreen(300, new HotelSystem());
		
	}
}
