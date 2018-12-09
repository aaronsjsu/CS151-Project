package gui;

import model.HotelSystem;
import model.User;
import model.receipt.ReceiptFormatter;

import javax.swing.*;

/**
 * Gui for guest to see the receipt.
 * @author Ryongji Park
 */
public class ShowReceiptScreen extends JFrame {
    public ShowReceiptScreen(int size, HotelSystem hs, User user, ReceiptFormatter formatter)
    {
        final JTextArea textArea = new JTextArea(20, 40);
        
        textArea.setText("");
        
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }
}
