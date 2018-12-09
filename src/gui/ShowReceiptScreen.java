package gui;

import model.HotelSystem;
import model.ReceiptFactory;
import model.ReceiptFormatter;
import model.User;

public class ShowReceiptScreen {
  public ShowReceiptScreen(int size, HotelSystem hs, User user, String type) {
    ReceiptFactory receiptFactory = new ReceiptFactory();
    ReceiptFormatter receipt = receiptFactory.createReceipt(type);

    

  }
}
