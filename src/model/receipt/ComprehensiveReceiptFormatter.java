package model.receipt;

import model.Reservation;
import model.User;

import java.util.List;

//Concrete strategy
public class ComprehensiveReceiptFormatter implements ReceiptFormatter
{
  @Override
  public Receipt guestReceipt(User user, List<Reservation> reservations) {
    return null;
  }
}
