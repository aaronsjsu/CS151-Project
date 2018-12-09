package model.receipt;

import model.Reservation;

import java.util.ArrayList;
import java.util.List;

/**
 * Models a receipt which details rooms reserved by the user.
 */
public class Receipt
{
    private List<Reservation> reservedRooms;
    private int totalAmount;
    private String userName;
    
    Receipt()
    {
        reservedRooms = new ArrayList<>();
        totalAmount = 0;
        userName = "";
    }

    public void setReservedRooms(List<Reservation> reservedRooms)
    {
        this.reservedRooms = reservedRooms;
    }

    public void setTotalAmount(int totalAmount) {
        this.totalAmount = totalAmount;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }


}
