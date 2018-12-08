package gui;

import model.HotelSystem;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/**
 * Creates a window to handle manager functionality.
 * @author Aaron Smith
 * @version 1.0 11/21/2018
 */
public class ManagerScreen extends JFrame {
	
	/**
	 * Constructor for the manager screen. All the work is done here.
	 * @param size The dimension of the screen in pixels.
	 */
	public ManagerScreen(int size, HotelSystem hs) {
		// Reference to be used within anonymous ActionListener classes
		JFrame frame = this;
		JPanel dataPanel = new JPanel();
		
		// Create some fonts
		Font largeFont = new Font("Serif", Font.PLAIN, size/5);
		Font smallFont = new Font("SansSerif", Font.PLAIN, size/15);
		
		// Create some labels
		JLabel welcomeLabel = new JLabel("Welcome Manager!");
		welcomeLabel.setFont(largeFont);
		welcomeLabel.setHorizontalAlignment(JLabel.CENTER);
		
		// Create our buttons
		JButton loadButton = new JButton("Load Existing Reservations");
		loadButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// TODO Load existing reservations from reservations.txt
				loadButton.setEnabled(false);
			}
		});
		loadButton.setPreferredSize(new Dimension(size/2, size/6));
		loadButton.setFont(smallFont);
		loadButton.setFocusable(false);
		JButton viewButton = new JButton("View Information");
		viewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// TODO Displays room information
				// Why don't these fit?
				dataPanel.add(new CalendarComponent(size, hs), BorderLayout.NORTH);
				dataPanel.add(new RoomInformationComponent(size, hs), BorderLayout.EAST);
				dataPanel.add(new SelectRoomComponent(size, hs), BorderLayout.CENTER);
				frame.pack();
				viewButton.setEnabled(false);
			}
		});
		viewButton.setPreferredSize(new Dimension(size/2, size/6));
		viewButton.setFont(smallFont);
		viewButton.setFocusable(false);
		JButton saveButton = new JButton("Save Reservations");
		saveButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// TODO Saves data to reservations.txt
			}
		});
		saveButton.setPreferredSize(new Dimension(size/2, size/6));
		saveButton.setFont(smallFont);
		saveButton.setFocusable(false);
		JButton quitButton = new JButton("Back/Quit");
		quitButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// TODO System saves and quits
				frame.dispose();
				new InitialScreen(size, hs);
			}
		});
		quitButton.setPreferredSize(new Dimension(size/2, size/6));
		quitButton.setFont(smallFont);
		quitButton.setFocusable(false);
		
	
		// Add some components to a new panel for the layout
		JPanel panel = new JPanel();
		panel.add(loadButton);
		panel.add(viewButton);
		panel.add(saveButton);
		panel.add(quitButton);
		panel.setLayout(new GridLayout(2, 2, 10, 10));
		
		// Add our welcome label and panel to the frame
		this.add(welcomeLabel, BorderLayout.NORTH);
		this.add(panel, BorderLayout.CENTER);
		this.add(dataPanel, BorderLayout.SOUTH);
		
		// Display the frame
		this.setTitle("Hotel Reservation System");
		this.setVisible(true);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		//this.setSize(3*size/2, size);
		this.pack();
	}
}
