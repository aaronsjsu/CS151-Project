package model;

import java.util.List;

//Concrete strategy
public class ComprehensiveReceiptFormatter implements ReceiptFormatter {
  @Override
  public Receipt guestReceipt(User user, List<Reservation> reservations) {
    return null;
  }
}
