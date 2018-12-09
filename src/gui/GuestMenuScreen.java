package gui;

import model.HotelSystem;
import model.User;

import javax.swing.*;
import java.awt.*;

public class GuestMenuScreen extends JFrame {
  public GuestMenuScreen(int size, HotelSystem hs, User user) {
    this.setTitle("Guest Screen");

    JPanel container = new JPanel();

    JLabel title = new JLabel("Welcome Guest");
    title.setFont(new Font("Serif", Font.PLAIN, size/5));
//    title.setPreferredSize(new Dimension(size/2, size/6));

    JButton newReservationBtn = new JButton("Make a reservation");
    newReservationBtn.setPreferredSize(new Dimension(size/2, size/6));
    newReservationBtn.addActionListener(e -> {
      dispose();
      new GuestNewReservation(size, hs, user);
    });

    JButton viewReservationBtn = new JButton("View / Cancel reservation");
    viewReservationBtn.addActionListener(e -> {
      new GuestViewReservation(size, hs, user);
      dispose();

    });
    viewReservationBtn.setPreferredSize(new Dimension(size/2, size/6));

    container.add(newReservationBtn);
    container.add(viewReservationBtn);

    container.setLayout(new BoxLayout(container, BoxLayout.Y_AXIS));
    add(title,BorderLayout.NORTH);
    add(container, BorderLayout.CENTER);
    pack();
    this.setResizable(false);
    this.setVisible(true);
  }
}
