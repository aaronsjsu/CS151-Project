package gui;

import model.HotelSystem;
import model.User;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class GuestNewReservation extends JFrame{
  static SimpleDateFormat fmt = new SimpleDateFormat("dd-MMM-yyyy");

  public GuestNewReservation(int size, HotelSystem hs, User user) {
    Date startDate = Calendar.getInstance().getTime();
//    String startDay = gregorianToString(startDate);
    private JXDatePicker datePicker;

    JPanel checkBoxPanel = new JPanel();

    JCheckBox ckbox1 = new JCheckBox("Luxurious");
    JCheckBox ckbox2 = new JCheckBox("Economic");
    ButtonGroup group = new ButtonGroup();
    group.add(ckbox1);
    group.add(ckbox2);
    checkBoxPanel.add(ckbox1);
    checkBoxPanel.add(ckbox2);

    Container contentPane = getContentPane();
    add(checkBoxPanel, BorderLayout.CENTER);

    SpinnerModel model =
            new SpinnerDateModel(startDate, startDate, null,  Calendar.DAY_OF_MONTH);
    JSpinner spinner = new JSpinner(model);
    JSpinner.DateEditor editor = new JSpinner.DateEditor(spinner, "yyyy.MM.dd");
    spinner.setEditor(editor);
    spinner.setBounds(100,110,200,30);
    add(spinner);
    pack();
    this.setResizable(false);
    this.setVisible(true);

  }



  public static String gregorianToString(GregorianCalendar date) {

    fmt.setCalendar(date);
    String dateFormatted = fmt.format(date.getTime());
    System.out.println(dateFormatted);
    return dateFormatted;
  }
}

