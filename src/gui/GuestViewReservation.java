package gui;

import model.HotelSystem;
import model.Reservation;
import model.User;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class GuestViewReservation extends JFrame {
  public GuestViewReservation(int size, HotelSystem hs, User user) {
    JScrollPane sp = new JScrollPane();
    JList jList = new JList(getReservationList(user));
    jList.setSelectedIndex(0);
    sp.getViewport().setView(jList);
    sp.setPreferredSize(new Dimension(200, 80));
    JPanel listPanel = new JPanel();
    listPanel.add(sp);


    JButton cancell = new JButton("Cancel");

    cancell.addActionListener(e -> {
      int index = jList.getSelectedIndex();

      user.cancelReservation(index);

      new GuestViewReservation(size, hs, user);
      dispose();
    });


    listPanel.add(cancell);
    listPanel.setLayout(new GridLayout(1, 2, 10, 10));
    listPanel.setSize(200, 200);
    this.add(listPanel);
    this.setVisible(true);
    this.pack();

  }

  public String[] getReservationList(User user) {
    List<Reservation> user_reservations = user.getReservations();

    String[] array = new String[user_reservations.size()];

    for (int i=0; i < array.length; i++) {
      array[i] = user_reservations.get(i).getStartDate().toString() + "--" + user_reservations.get(i).getEndDate().toString();
    }

    return array;
  }
}
