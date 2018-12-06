package gui;

import java.awt.*;
import java.time.LocalDate;
import javax.swing.*;
import model.HotelSystem;
import model.Reservation;
import model.Room;
import model.observable.ChangeListener;
import java.util.List;
import java.util.ArrayList;

/**
 * Component to view hotel room information.
 * @author Aaron Smith
 * @version 1.0 12/5/18 
 */
public class RoomInformationComponent extends JComponent implements ChangeListener {
	JLabel availableLabel;
	JLabel reservedLabel;
	JLabel roomData;
	HotelSystem hs;

	public RoomInformationComponent(int size, HotelSystem hs) {
		this.hs = hs;
		Font largeFont = new Font("Serif", Font.PLAIN, size/15);
		Font font = new Font("Serif", Font.PLAIN, size/20);
		
		JLabel title = new JLabel("Room Information");
		title.setFont(largeFont);
		availableLabel = new JLabel();
		availableLabel.setFont(font);
		reservedLabel = new JLabel();
		reservedLabel.setFont(font);
		JLabel selectedRoom = new JLabel("Selected Room:");
		selectedRoom.setFont(font);
		roomData = new JLabel("No Room Selected");
		roomData.setFont(font);
		
		JPanel roomDataPanel = new JPanel();
		roomDataPanel.setLayout(new BorderLayout());
		roomDataPanel.add(availableLabel, BorderLayout.NORTH);
		roomDataPanel.add(reservedLabel, BorderLayout.CENTER);
		JPanel selectedRoomPanel = new JPanel();
		selectedRoomPanel.setLayout(new BorderLayout());
		selectedRoomPanel.add(selectedRoom, BorderLayout.NORTH);
		selectedRoomPanel.add(roomData, BorderLayout.SOUTH);
		this.setLayout(new BorderLayout());
		this.add(title, BorderLayout.NORTH);
		this.add(roomDataPanel, BorderLayout.CENTER);
		this.add(selectedRoomPanel, BorderLayout.SOUTH);
		this.setVisible(true);
		this.fire();
	}
	
	private void setRooms(List<Room> availableRooms, List<Room> reservedRooms) {
		String available = "Available Rooms:\n";
		for (Room room : availableRooms) {
			available += room + "\n";
		}
		String reserved = "Reserved Rooms:\n";
		for (Room room : reservedRooms) {
			reserved += room + "\n";
		}
		availableLabel.setText(available);
		reservedLabel.setText(reserved);
	}

	@Override
	public void fire() {
		LocalDate selectedDate = hs.getSelectedDate();
		List<Reservation> reservations = hs.getReservations();
		List<Room> reservedRooms = new ArrayList<>();
		List<Room> availableRooms = hs.getRooms();
		for (Reservation r : reservations) {
			if (r.getStartDate().compareTo(selectedDate) >= 0 && r.getEndDate().compareTo(selectedDate) <= 0) {
				reservedRooms.add(r.getRoom());
			}
		}
		availableRooms.removeAll(reservedRooms);
		this.setRooms(availableRooms, reservedRooms);
		Room selectedRoom = hs.getSelectedRoom();
		if (selectedRoom != null) {
			roomData.setText(selectedRoom.toString());
		}
	}
}
