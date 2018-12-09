package model.receipt;

import model.Reservation;
import model.User;

import java.util.List;

public class SimpleReceiptFormatter implements ReceiptFormatter
{
  @Override
  public Receipt guestReceipt(User user, List<Reservation> reservations) {
    Receipt receipt = new Receipt();

    receipt.setUserName(user.getUsername());
    receipt.setReservedRooms(reservations);

    return receipt;
  }
}
