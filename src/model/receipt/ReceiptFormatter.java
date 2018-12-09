package model.receipt;

import model.Reservation;
import model.User;

import java.util.List;

public interface ReceiptFormatter
{
    Receipt guestReceipt(User user, List<Reservation> reservations);
}
