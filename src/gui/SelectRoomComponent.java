package gui;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Set;

import javax.swing.*;

import model.HotelSystem;
import model.Room;

/**
 * Allows user to see a list of rooms and to select one of them.
 * @author Aaron Smith
 * @version 1.0 12/5/18
 */
public class SelectRoomComponent extends JComponent {

	/**
	 * Constructor for the component. All the work is done in here,
	 * i.e. buttons are added, layout is created, etc.
	 * @param size The size for the component in pixels. Used to scale it.
	 * @param hs HotelSysem object used to access hotel data. 
	 */
	public SelectRoomComponent(int size, HotelSystem hs) {
		Font largeFont = new Font("Serif", Font.PLAIN, size/15);
		Font font = new Font("Serif", Font.PLAIN, size/20);
		JLabel title = new JLabel("Select A Room");
		title.setFont(largeFont);
		
		JPanel buttonPanel = new JPanel();
		buttonPanel.setLayout(new GridLayout(5, 4, 0, 0));
		buttonPanel.setVisible(true);
		
		Set<Room> rooms = hs.getRooms();
		for (Room r : rooms) {
			JButton button = new JButton();
			button.setText("" + r.getNumber());
			button.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					hs.setSelectedRoom(r);
					System.out.println(r);
				}
			});
			button.setFont(font);
			button.setFocusable(false);
			buttonPanel.add(button);
		}
		
		this.setLayout(new BorderLayout());
		this.add(title, BorderLayout.NORTH);
		this.add(buttonPanel, BorderLayout.SOUTH);
		this.setVisible(true);
	}
}
