package tests;

import gui.InitialScreen;
import model.HotelSystem;

/**
 * Launches the initial screen.
 */
public class Launcher
{
	public static void main(String args[])
	{
		final int SCREEN_SIZE = 750;
		HotelSystem hs = new HotelSystem();
		InitialScreen screen = new InitialScreen(SCREEN_SIZE, hs);
	}
}
