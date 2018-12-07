package gui;

import model.HotelSystem;
import model.User;

import javax.swing.*;

import java.awt.*;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;

public class GuestNewReservation extends JFrame{
  static SimpleDateFormat fmt = new SimpleDateFormat("dd-MMM-yyyy");

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
    this.add(checkBoxPanel, BorderLayout.CENTER);


    Date initDate = java.sql.Date.valueOf(LocalDate.now());
    Date maxDate = java.sql.Date.valueOf(LocalDate.now().plusMonths(2));
    SpinnerModel model =
            new SpinnerDateModel(initDate, initDate, null, Calendar.DAY_OF_MONTH);
    SpinnerModel model2 =
            new SpinnerDateModel(initDate, initDate, maxDate, Calendar.DAY_OF_MONTH);
    JSpinner spinner = new JSpinner(model);
    JSpinner endDate = new JSpinner(model2);
    JSpinner.DateEditor editor = new JSpinner.DateEditor(spinner, "yyyy/MM/dd");
    JSpinner.DateEditor editor2 = new JSpinner.DateEditor(endDate, "yyyy/MM/dd");
    spinner.setEditor(editor);
    spinner.setBounds(100,110,200,30);
    endDate.setEditor(editor2);
    add(spinner);
    add(endDate);


    this.add(spinner);
    this.pack();
    this.setResizable(false);
    this.setVisible(true);

  }



}

