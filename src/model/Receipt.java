package model;

import java.util.ArrayList;
import java.util.List;

public class Receipt {
  private List<Reservation> reservedRooms;
  private int  totalAmount;
  private String userName;

  Receipt() {
    reservedRooms = new ArrayList<>();
    totalAmount = 0;
    userName = "";
  }

  public void setReservedRooms(List<Reservation> reservedRooms) {
    this.reservedRooms = reservedRooms;
  }

  public void setTotalAmount(int totalAmount) {
    this.totalAmount = totalAmount;
  }

  public void setUserName(String userName) {
    this.userName = userName;
  }


}
