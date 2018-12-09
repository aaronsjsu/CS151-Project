package gui;

import model.HotelSystem;
import model.User;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ChooseReceiptScreen extends JFrame {
  public ChooseReceiptScreen(int size, HotelSystem hs, User user) {

    JFrame frame = this;

    JLabel welcomeLabel = new JLabel("Choose Receipt!");
    welcomeLabel.setHorizontalAlignment(JLabel.CENTER);
    JLabel label = new JLabel("Receipt Type:");

    // Create our buttons
    JButton managerButton = new JButton("Simple");
    managerButton.addActionListener(e -> {
      frame.dispose();
      new ShowReceiptScreen(size, hs, user, "simple");
    });
    managerButton.setPreferredSize(new Dimension(size/2, size/6));
    managerButton.setFocusable(false);
    JButton guestButton = new JButton("Comprehensive");
    guestButton.addActionListener(e -> {
      // Close this frame and open the guest menu frame.
      frame.dispose();
      new ShowReceiptScreen(size, hs, user, "comprehensive");
    });
    guestButton.setPreferredSize(new Dimension(size/2, size/6));
    guestButton.setFocusable(false);

    // Add some components to a new panel for the layout
    JPanel panel = new JPanel();
    panel.add(label);
    panel.add(managerButton);
    panel.add(guestButton);
    panel.setLayout(new GridLayout(1, 3, 10, 10));

    // Add our welcome label and panel to the frame
    this.add(welcomeLabel, BorderLayout.NORTH);
    this.add(panel, BorderLayout.SOUTH);

    // Display the frame
    this.setTitle("Receipt");
    this.setVisible(true);
    this.setDefaultCloseOperation(EXIT_ON_CLOSE);
    this.setSize(3*size/2, 2*size/3);
  }
}
