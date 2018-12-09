package gui;

import model.HotelSystem;
import model.Reservation;
import model.User;

import javax.swing.*;
import java.awt.*;
import java.util.Objects;

/**
 * Gui for guest to view and cancel the reservation.
 * @author Ryongji Park
 */
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

    /**
     * get String[] to add this on GUI to show the reservation.
     * @param user
     * @return String[]
     */
    public String[] getReservationList(User user)
    {
        return Objects.requireNonNull(user).getReservations().stream()
                .map(Reservation::getInterval)
                .map(i -> String.format("%s--%s", i.start.toString(), i.end.toString()))
                .toArray(String[]::new);
    }
}
