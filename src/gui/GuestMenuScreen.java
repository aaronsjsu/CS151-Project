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

    JButton LoginBtn = new JButton("Make a reservation");
    LoginBtn.setPreferredSize(new Dimension(size/2, size/6));
    LoginBtn.addActionListener(e -> {
      dispose();
      new GuestNewReservation(size, hs, user);
    });

    JButton SignupBtn = new JButton("View / Cancel reservation");
    SignupBtn.setPreferredSize(new Dimension(size/2, size/6));
    SignupBtn.addActionListener(e -> new GuestSignUpScreen(size, hs));

    container.add(LoginBtn);
    container.add(SignupBtn);

    container.setLayout(new BoxLayout(container, BoxLayout.Y_AXIS));
    add(title,BorderLayout.NORTH);
    add(container, BorderLayout.CENTER);
    pack();
    this.setResizable(false);
    this.setVisible(true);
  }
}
