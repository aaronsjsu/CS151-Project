package gui;

import java.awt.*;
import javax.swing.*;

/**
 * Component to view hotel room information.
 * @author Aaron Smith
 * @version 1.0 12/5/18 
 */
public class RoomInformationComponent extends JComponent {

	public RoomInformationComponent(int size) {
		Font largeFont = new Font("Serif", Font.PLAIN, size/15);
		Font font = new Font("Serif", Font.PLAIN, size/20);
		
		JLabel title = new JLabel("Room Information");
		title.setFont(largeFont);
		JLabel availableRooms = new JLabel("Available Rooms:");
		availableRooms.setFont(font);
		JLabel reservedRooms = new JLabel("Reserved Rooms:");
		reservedRooms.setFont(font);
		JLabel selectedRoom = new JLabel("Selected Room:");
		selectedRoom.setFont(font);
		JLabel roomData = new JLabel("No Room Selected");
		roomData.setFont(font);
		
		JPanel roomDataPanel = new JPanel();
		roomDataPanel.add(availableRooms, BorderLayout.NORTH);
		roomDataPanel.add(reservedRooms, BorderLayout.CENTER);
		JPanel selectedRoomPanel = new JPanel();
		selectedRoomPanel.add(selectedRoom, BorderLayout.NORTH);
		selectedRoomPanel.add(roomData, BorderLayout.SOUTH);
		this.setLayout(new BorderLayout());
		this.add(title, BorderLayout.NORTH);
		this.add(roomDataPanel, BorderLayout.CENTER);
		this.add(selectedRoomPanel, BorderLayout.SOUTH);
		this.setVisible(true);
	}
}
