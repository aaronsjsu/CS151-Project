package gui;

import model.HotelSystem;
import model.User;

import javax.swing.*;
import java.awt.*;

public class GuestLoginScreen extends JFrame {
  public GuestLoginScreen(int size, HotelSystem hs) {
    this.setTitle("Login");
    this.setLayout(new BoxLayout(this.getContentPane(), BoxLayout.Y_AXIS));

    JLabel title = new JLabel("Guest Login");
    title.setAlignmentX(CENTER_ALIGNMENT);
    title.setFont(new Font("Serif", Font.PLAIN, size/5));
    title.setPreferredSize(new Dimension(size, size / 3));

    JPanel loginPanel = new JPanel();
    loginPanel.setLayout(new GridLayout(3, 2));
    JLabel IdLabel = new JLabel("id:");
    JTextField IdField = new JTextField(20);

    JLabel passwordLabel = new JLabel("Password:");
    JPasswordField passwordField = new JPasswordField( 20);

    loginPanel.add(IdLabel);
    loginPanel.add(IdField);
    loginPanel.add(passwordLabel);
    loginPanel.add(passwordField);

    JButton loginButton = new JButton("Login");
    loginButton.addActionListener(
            e -> {
              String id = IdField.getText();
              String password = new String(passwordField.getPassword());
//
              if (!id.isEmpty() && !password.isEmpty()) {
                User user = hs.getUser(id, password);

                if (user != null) {
                  dispose();
                  new GuestMenuScreen(size, hs, user);
                } else {
                  JOptionPane.showMessageDialog(
                          null, "Id or Password is inValid");
                }
              } else {
                JOptionPane.showMessageDialog(
                        null, "Please fill out all fields ");
              }
            });


    this.add(title, BorderLayout.NORTH);
    this.add(loginPanel, BorderLayout.CENTER);
    this.add(loginButton, BorderLayout.SOUTH);
    pack();
    this.setVisible(true);
  }
}
