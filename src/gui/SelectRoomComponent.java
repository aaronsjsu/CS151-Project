package gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.*;

import model.HotelSystem;
import model.Room;

/**
 * Allows user to see a list of rooms and to select one of them.
 * @author Aaron Smith
 * @version 1.0 12/5/18
 */
public class SelectRoomComponent extends JComponent {

	public SelectRoomComponent(int size, HotelSystem hs) {
		Font largeFont = new Font("Serif", Font.PLAIN, size/15);
		Font font = new Font("Serif", Font.PLAIN, size/20);
		JLabel title = new JLabel("Select A Room");
		title.setFont(largeFont);
		
		JPanel buttonPanel = new JPanel();
		buttonPanel.setLayout(new GridLayout(4, 5, 0, 0));
		buttonPanel.setVisible(true);
		
		List<Room> rooms = hs.getRooms();
		for (Room r : rooms) {
			JButton button = new JButton();
			button.setText(r.toString());
			button.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					hs.setSelectedRoom(r);
//					hs.updateListeners();
				}
			});
			button.setFont(font);
			button.setFocusable(false);
			//button.setPreferredSize(new Dimension(size/2, size/6));
			buttonPanel.add(button);
		}
		
		this.setLayout(new BorderLayout());
		this.add(title, BorderLayout.NORTH);
		this.add(buttonPanel, BorderLayout.SOUTH);
		this.setVisible(true);
	}
}
