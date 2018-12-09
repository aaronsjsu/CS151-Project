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
	//JLabel availableLabel;
	//JLabel reservedLabel;
	JLabel roomData;
	JPanel roomDataPanel;
	HotelSystem hs;
	Font font;
	Font boldFont;

	public RoomInformationComponent(int size, HotelSystem hs) {
		this.hs = hs;
		Font largeFont = new Font("Serif", Font.BOLD, size/15);
		font = new Font("Serif", Font.PLAIN, size/20);
		boldFont = new Font("Serif", Font.BOLD, size/18);
		
		JLabel title = new JLabel("Room Information");
		title.setFont(largeFont);
		//availableLabel = new JLabel();
		//availableLabel.setFont(font);
		//reservedLabel = new JLabel();
		//reservedLabel.setFont(font);
		JLabel selectedRoom = new JLabel("Selected Room:");
		selectedRoom.setFont(font);
		roomData = new JLabel("No Room Selected");
		roomData.setFont(font);
		
		roomDataPanel = new JPanel();
		roomDataPanel.setLayout(new GridLayout(23, 1, 1, 1));
		//roomDataPanel.add(availableLabel, BorderLayout.NORTH);
		//roomDataPanel.add(reservedLabel, BorderLayout.CENTER);
		JPanel selectedRoomPanel = new JPanel();
		selectedRoomPanel.setLayout(new BorderLayout());
		selectedRoomPanel.add(selectedRoom, BorderLayout.NORTH);
		selectedRoomPanel.add(roomData, BorderLayout.SOUTH);
		this.setLayout(new BorderLayout());
		this.add(title, BorderLayout.NORTH);
		this.add(roomDataPanel, BorderLayout.CENTER);
		this.add(selectedRoomPanel, BorderLayout.SOUTH);
		this.setVisible(true);
		System.out.println(size);
		//this.setPreferredSize(new Dimension(size, size));
		this.fire();
	}
	
	private void setRooms(List<Room> availableRooms, List<Room> reservedRooms) {
		//String available = "Available Rooms:\n";
		roomDataPanel.removeAll();
		JLabel l = new JLabel("Available Rooms (date):");
		l.setFont(boldFont);
		roomDataPanel.add(l);
		for (Room room : availableRooms) {
			//available += room + "\n";
			JLabel label = new JLabel(room.toString());
			label.setFont(font);
			roomDataPanel.add(label);
		}
		//String reserved = "Reserved Rooms:\n";
		for (Room room : reservedRooms) {
			//reserved += room + "\n";
			JLabel label = new JLabel(room.toString());
			label.setFont(font);
			roomDataPanel.add(label);
		}
		l = new JLabel("Reserved Rooms (date):");
		l.setFont(boldFont);
		roomDataPanel.add(l);
		//availableLabel.setText(available);
		//reservedLabel.setText(reserved);
	}

	@Override public void fire() {
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
			// also need to display reservation information
			//information of the selected room including room number, price 
			//and periods of valid reservations of this room (e.g. 11/28/18-
			//11/30/18, 12/25/18-12/26/18).
		}
	}
}
