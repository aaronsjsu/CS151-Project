package gui;

import model.HotelSystem;
import model.User;

import javax.swing.*;
import java.awt.*;

/**
 * Gui for guest to sign up.
 * @author Ryongji Park
 */
public class GuestSignUpScreen extends JFrame {
    public GuestSignUpScreen(int size, HotelSystem hs) {
        this.setTitle("Sign Up");
        this.setLayout(new BoxLayout(this.getContentPane(), BoxLayout.Y_AXIS));

        JLabel title = new JLabel("Guest Sign Up");
        title.setAlignmentX(CENTER_ALIGNMENT);
        title.setFont(new Font("Serif", Font.PLAIN, size/5));
        title.setPreferredSize(new Dimension(size, size / 3));

        JPanel signUpPanel = new JPanel();
        signUpPanel.setLayout(new GridLayout(3, 2));
        JLabel IdLabel = new JLabel("id:");
        JTextField IdField = new JTextField("123", 20);

        JLabel userNameLabel = new JLabel("User Name:");
        JTextField userNameFireld = new JTextField("John Smith", 20);

        JLabel passwordLabel = new JLabel("Password:");
        JPasswordField passwordField = new JPasswordField( 20);

        signUpPanel.add(IdLabel);
        signUpPanel.add(IdField);
        signUpPanel.add(userNameLabel);
        signUpPanel.add(userNameFireld);
        signUpPanel.add(passwordLabel);
        signUpPanel.add(passwordField);

        JButton signUpButton = new JButton("Sign Up");
        signUpButton.addActionListener(
                e -> {
                    String id = IdField.getText();
                    String userName = userNameFireld.getText();
                    String password = new String(passwordField.getPassword());

                    if (!id.isEmpty() && !userName.isEmpty() && !password.isEmpty()) {
                        if (!hs.userExists(id)) {
                            User newUser = new User(id, userName, password);
                            hs.addUser(newUser);
                            new GuestMenuScreen(size, hs, newUser);
                            this.dispose();
                        } else {
                            JOptionPane.showMessageDialog(
                                    null, "Id is invalid, please use different Id");
                        }
                    } else {
                        JOptionPane.showMessageDialog(
                                null, "Please fill out all fields ");
                    }
                });


        this.add(title, BorderLayout.NORTH);
        this.add(signUpPanel, BorderLayout.CENTER);
        this.add(signUpButton, BorderLayout.SOUTH);
        pack();
        this.setVisible(true);
    }
}
