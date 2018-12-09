package gui;

import model.HotelSystem;
import model.ReceiptFactory;
import model.ReceiptFormatter;
import model.User;

import javax.swing.*;

/**
 * Gui for guest to see the receipt.
 * @author Ryongji Park
 */
public class ShowReceiptScreen extends JFrame {
  public ShowReceiptScreen(int size, HotelSystem hs, User user, String type) {
    ReceiptFactory receiptFactory = new ReceiptFactory();
    ReceiptFormatter receipt = receiptFactory.createReceipt(type);
    final JTextArea textArea = new JTextArea(20, 40);



  }
}
