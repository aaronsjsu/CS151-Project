package gui;

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
	public ManagerScreen(int size) {
		// Create some fonts
		Font largeFont = new Font("Serif", Font.PLAIN, size/5);
		Font smallFont = new Font("SansSerif", Font.PLAIN, size/12);
		
		// Create some labels
		JLabel welcomeLabel = new JLabel("Welcome Manager!");
		welcomeLabel.setFont(largeFont);
		welcomeLabel.setHorizontalAlignment(JLabel.CENTER);
		
		// Create our buttons
		JButton loadButton = new JButton("Load Existing Reservations");
		loadButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// TODO Load existing reservations from reservations.txt
			}
		});
		loadButton.setPreferredSize(new Dimension(size/2, size/6));
		loadButton.setFont(smallFont);
		JButton viewButton = new JButton("View Information");
		viewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// TODO Displays room information
			}
		});
		viewButton.setPreferredSize(new Dimension(size/2, size/6));
		viewButton.setFont(smallFont);
		JButton saveButton = new JButton("Save Reservations");
		saveButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// TODO Saves data to reservations.txt
			}
		});
		saveButton.setPreferredSize(new Dimension(size/2, size/6));
		saveButton.setFont(smallFont);
		JButton quitButton = new JButton("Quit");
		quitButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// TODO System saves and quits
			}
		});
		quitButton.setPreferredSize(new Dimension(size/2, size/6));
		quitButton.setFont(smallFont);
		
	
		// Add some components to a new panel for the layout
		JPanel panel = new JPanel();
		panel.add(loadButton);
		panel.add(viewButton);
		panel.add(saveButton);
		panel.add(quitButton);
		panel.setLayout(new GridLayout(2, 2, 10, 10));
		
		// Add our welcome label and panel to the frame
		this.add(welcomeLabel, BorderLayout.NORTH);
		this.add(panel, BorderLayout.SOUTH);
		
		// Display the frame
		this.setTitle("Hotel Reservation System");
		this.setVisible(true);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		//this.setSize(3*size/2, 2*size/3);
		this.pack();
	}
}
