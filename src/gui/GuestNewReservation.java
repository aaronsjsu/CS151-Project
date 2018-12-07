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
import model.User;

import javax.swing.*;
import java.awt.*;
import java.text.SimpleDateFormat;
import java.time.LocalDate;

public class GuestNewReservation extends JFrame {
  static SimpleDateFormat fmt = new SimpleDateFormat("dd-MMM-yyyy");
  private DatePicker checkInDatePicker;
  private DatePicker checkOutDatePicker;

  public GuestNewReservation(int size, HotelSystem hs, User user) {
    this.setTitle("Sign Up");
    this.setLayout(new BoxLayout(this.getContentPane(), BoxLayout.Y_AXIS));

    JPanel checkBoxPanel = new JPanel();
    JCheckBox ckbox1 = new JCheckBox("Economic");
    JCheckBox ckbox2 = new JCheckBox("Luxurious");
    ckbox1.setSelected(true);
    ButtonGroup group = new ButtonGroup();
    group.add(ckbox1);
    group.add(ckbox2);
    checkBoxPanel.add(ckbox1);
    checkBoxPanel.add(ckbox2);
    this.add(checkBoxPanel, BorderLayout.NORTH);
    JButton searchBtn = new JButton("View available Room");

    JFXPanel centerPane = new JFXPanel();
    checkInDatePicker = new DatePicker();
    checkOutDatePicker = new DatePicker();
    checkInDatePicker.setValue(LocalDate.now());
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
              }
            };
          }
        };
    checkInDatePicker.setDayCellFactory(dayCellFactory2);
    checkOutDatePicker.setValue(checkInDatePicker.getValue().plusDays(1));
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
              }
            };
          }
        };

    checkOutDatePicker.setDayCellFactory(dayCellFactory);
    VBox vbox = new VBox(20);
    vbox.setStyle("-fx-padding: 10;");

    GridPane gridPane = new GridPane();
    gridPane.setHgap(10);
    gridPane.setVgap(10);
    Label checkInlabel = new Label("Check-In Date:");
    gridPane.add(checkInlabel, 0, 0);
    GridPane.setHalignment(checkInlabel, HPos.LEFT);
    gridPane.add(checkInDatePicker, 0, 1);
    Label checkOutlabel = new Label("Check-Out Date:");
    gridPane.add(checkOutlabel, 0, 2);
    GridPane.setHalignment(checkOutlabel, HPos.LEFT);
    gridPane.add(checkOutDatePicker, 0, 3);
    vbox.getChildren().add(gridPane);

    Scene scene = new Scene(vbox, 400, 400);
    centerPane.setScene(scene);

    searchBtn.addActionListener(
        e -> {
          String roomType = ckbox1.isSelected() ? ckbox1.getText() : ckbox2.getText();
          LocalDate start = checkInDatePicker.getValue();
          System.out.println(start.toString());
          // limit the period of stay
          if (1 == 1) {
          } else {
            JOptionPane.showMessageDialog(null, "Please fill out all fields ");
          }
        });

    this.add(centerPane);
    this.add(searchBtn, BorderLayout.SOUTH);
    this.setResizable(true);
    this.setVisible(true);
    this.pack();
    this.setSize(400, 300);
  }
}
