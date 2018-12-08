package model;

import java.util.List;

public interface ReceiptFormatter {
  public abstract Receipt guestReceipt(User user, List<Reservation> reservations);
}
