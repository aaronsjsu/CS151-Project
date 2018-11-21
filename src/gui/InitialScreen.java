package gui;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/**
 * Creates the initial window for the program. Prompts the user to either
 * log in as a guest or a manager, then directs them accordingly.
 * @author Aaron Smith
 * @version 1.0 11/21/2018
 */
public class InitialScreen extends JFrame {
	
	/**
	 * Constructor for the initial screen. All the work is done here.
	 * @param size The dimension of the screen in pixels.
	 */
	public InitialScreen(int size) {
		// Reference to be used within anonymous ActionListener classes
		JFrame frame = this;
		
		// Create some fonts
		Font largeFont = new Font("Serif", Font.PLAIN, size/5);
		Font smallFont = new Font("SansSerif", Font.PLAIN, size/12);
		
		// Create some labels
		JLabel welcomeLabel = new JLabel("Welcome!");
		welcomeLabel.setFont(largeFont);
		welcomeLabel.setHorizontalAlignment(JLabel.CENTER);
		JLabel label = new JLabel("Sign in as:");
		label.setFont(smallFont);
		
		// Create our buttons
		JButton managerButton = new JButton("Manager");
		managerButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Close this frame and open the manager menu frame.
				frame.dispose();
				new ManagerScreen(size);
			}
		});
		managerButton.setPreferredSize(new Dimension(size/2, size/6));
		managerButton.setFont(smallFont);
		JButton guestButton = new JButton("Guest");
		guestButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Close this frame and open the guest menu frame.
				frame.dispose();
				new GuestScreen();
			}
		});
		guestButton.setPreferredSize(new Dimension(size/2, size/6));
		guestButton.setFont(smallFont);
	
		// Add some components to a new panel for the layout
		JPanel panel = new JPanel();
		panel.add(label);
		panel.add(managerButton);
		panel.add(guestButton);
		panel.setLayout(new GridLayout(1, 3, 10, 10));
		
		// Add our welcome label and panel to the frame
		this.add(welcomeLabel, BorderLayout.NORTH);
		this.add(panel, BorderLayout.SOUTH);
		
		// Display the frame
		this.setTitle("Hotel Reservation System");
		this.setVisible(true);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setSize(3*size/2, 2*size/3);
	}
}
