package gui;

import java.awt.BorderLayout;
import java.awt.Font;

import javax.swing.*;

/**
 * Allows user to see a list of rooms and to select one of them.
 * @author Aaron Smith
 * @version 1.0 12/5/18
 */
public class SelectRoomComponent extends JComponent {

	public SelectRoomComponent(int size) {
		Font largeFont = new Font("Serif", Font.PLAIN, size/15);
		Font font = new Font("Serif", Font.PLAIN, size/20);
		
		JLabel title = new JLabel("Select A Room");
		title.setFont(largeFont);
		
		this.setLayout(new BorderLayout());
		this.add(title, BorderLayout.NORTH);
		this.setVisible(true);
	}
}
