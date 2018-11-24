package gui;

import model.HotelSystem;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Creates a window to handle guest functionality.
 */
public class GuestScreen extends JFrame {

  public GuestScreen(int size, HotelSystem hs)
  {
    this.setTitle("Guest Screen");

    JPanel container = new JPanel();

    JLabel title = new JLabel("Welcome Guest");
    title.setFont(new Font("Serif", Font.PLAIN, size/5));
//    title.setPreferredSize(new Dimension(size/2, size/6));

    JButton LoginBtn = new JButton("Login");
    LoginBtn.setPreferredSize(new Dimension(size/2, size/6));
    LoginBtn.addActionListener(e -> {
      dispose();
      new GuestLoginScreen();
    });

    JButton SignupBtn = new JButton("Sign Up");
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
