package gui;

import javafx.embed.swing.JFXPanel;
import javafx.geometry.HPos;
import javafx.scene.Scene;
import javafx.scene.control.DateCell;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.util.Callback;
import model.HotelSystem;
import model.Reservation;
import model.Room;
import model.User;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.util.Optional;

/**
 * Gui for guest to choose a available room and confirm the reservation.
 * @author Ryongji Park
 */
public class GuestFindAvailableRoomScreen extends JFrame {
  private DatePicker checkInDatePicker;
  private DatePicker checkOutDatePicker;

  public GuestFindAvailableRoomScreen(
          int size, HotelSystem hs, User user, String roomType, LocalDate start, LocalDate end) {

    JPanel checkBoxPanel = new JPanel();
    JCheckBox ckbox1 = new JCheckBox("Economic");
    JCheckBox ckbox2 = new JCheckBox("Luxurious");
    ActionListener actionListener = new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("Economic")) {
          hs.setAvailableRooms("Economic", checkInDatePicker.getValue(), checkOutDatePicker.getValue());
        } else if (e.getActionCommand().equals("Luxurious")) {
          hs.setAvailableRooms("Luxurious", checkInDatePicker.getValue(), checkOutDatePicker.getValue());
        }
      }
    };

    ckbox1.addActionListener(actionListener);
    ckbox2.addActionListener(actionListener);
    if (roomType.equals("Economic")) ckbox1.setSelected(true);
    else ckbox2.setSelected(true);
    ButtonGroup group = new ButtonGroup();
    group.add(ckbox1);
    group.add(ckbox2);
    checkBoxPanel.add(ckbox1);
    checkBoxPanel.add(ckbox2);
    this.add(checkBoxPanel, BorderLayout.NORTH);

    JFXPanel centerPane = new JFXPanel();
    checkInDatePicker = new DatePicker();
    checkOutDatePicker = new DatePicker();
    checkInDatePicker.setValue(start);
    final Callback<DatePicker, DateCell> dayCellFactory2 =
            new Callback<DatePicker, DateCell>() {
              @Override
              public DateCell call(final DatePicker datePicker) {
                return new DateCell() {
                  @Override
                  public void updateItem(LocalDate item, boolean empty) {
                    super.updateItem(item, empty);
                    if (checkOutDatePicker.getValue().isBefore(checkInDatePicker.getValue())
                            || checkOutDatePicker
                            .getValue()
                            .isAfter(checkInDatePicker.getValue().plusDays(60))) {
                      checkOutDatePicker.setValue(checkInDatePicker.getValue().plusDays(1));
                    }
                    if (item.isBefore(LocalDate.now())) {
                      setDisable(true);
                      setStyle("-fx-background-color: #ffc0cb;");
                    }
                    hs.setAvailableRooms(roomType, checkInDatePicker.getValue(), checkOutDatePicker.getValue());
                  }
                };
              }
            };
    checkInDatePicker.setDayCellFactory(dayCellFactory2);
    checkOutDatePicker.setValue(end);
    final Callback<DatePicker, DateCell> dayCellFactory =
            new Callback<DatePicker, DateCell>() {
              @Override
              public DateCell call(final DatePicker datePicker) {
                return new DateCell() {
                  @Override
                  public void updateItem(LocalDate item, boolean empty) {
                    super.updateItem(item, empty);
                    if (item.isBefore(checkInDatePicker.getValue().plusDays(1))
                            || item.isAfter(checkInDatePicker.getValue().plusMonths(2))) {
                      setDisable(true);
                      setStyle("-fx-background-color: #ffc0cb;");
                    }
                    hs.setAvailableRooms(roomType, checkInDatePicker.getValue(), checkOutDatePicker.getValue());
                  }
                };
              }
            };

    checkOutDatePicker.setDayCellFactory(dayCellFactory);
    VBox vbox = new VBox(10);
    vbox.setStyle("-fx-padding: 10;");
    GridPane gridPane = new GridPane();
    gridPane.setHgap(10);
    gridPane.setVgap(10);
    javafx.scene.control.Label checkInlabel = new javafx.scene.control.Label("Check-In Date:");
    gridPane.add(checkInlabel, 0, 0);
    GridPane.setHalignment(checkInlabel, HPos.LEFT);
    gridPane.add(checkInDatePicker, 1, 0);
    javafx.scene.control.Label checkOutlabel = new Label("Check-Out Date:");
    gridPane.add(checkOutlabel, 0, 1);
    GridPane.setHalignment(checkOutlabel, HPos.LEFT);
    gridPane.add(checkOutDatePicker, 1, 1);
    vbox.getChildren().add(gridPane);

    Scene scene = new Scene(vbox, 400, 400);
    centerPane.setScene(scene);



    JScrollPane sp = new JScrollPane();

    hs.addChangeListener((ChangeEvent event) -> {
      sp.getViewport().setView(new JList(hs.getAvailableRooms()));
    });

    sp.getViewport().setView(new JList(hs.getAvailableRooms()));
    sp.setPreferredSize(new Dimension(200, 80));
    JPanel listPanel = new JPanel();
    JTextField room_number = new JTextField("room number", 20);

    JPanel buttonPanel = new JPanel();

    JButton confirmBtn = new JButton("Confirm");
    JButton reservationBtn = new JButton("More reservation");
    JButton DoneBtn = new JButton("Done");

    confirmBtn.addActionListener(e -> {
      Optional room = hs.getRooms().stream().filter(r -> String.valueOf(r.getNumber()).equals(room_number.getText())).findFirst();
      if (room.isPresent()) {
        user.addReservation(new Reservation(user, (Room) room.get(), checkInDatePicker.getValue(), checkOutDatePicker.getValue()));
        confirmBtn.setText("confirmed");
        confirmBtn.setEnabled(false);
      } else {
        JOptionPane.showMessageDialog(
                null, "Room Number is invalid");
      }


    });

    reservationBtn.addActionListener(e -> {
      dispose();
      new GuestNewReservation(size, hs, user);

    });

    DoneBtn.addActionListener(e -> {
        new ChooseReceiptScreen(size, hs, user);
        dispose();
    });
    listPanel.add(sp);
    listPanel.add(room_number);
    listPanel.setSize( 100, 100);
    buttonPanel.add(confirmBtn);
    buttonPanel.add(reservationBtn);
    buttonPanel.add(DoneBtn);
    buttonPanel.setLayout(new GridLayout(1, 3, 10, 10));
    JPanel gridPanel = new JPanel();
    gridPanel.add(centerPane);
    gridPanel.add(listPanel);
    gridPanel.setLayout(new GridLayout(2, 1, 10, 10));
    this.add(gridPanel);
    this.add(buttonPanel, BorderLayout.SOUTH);
    this.setVisible(true);
    this.pack();
    this.setSize(600, 600);
  }
}
