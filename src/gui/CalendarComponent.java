package gui;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.time.*;

/**
 * Gui calendar component that allows user to view and select dates
 * on a calendar.
 * @author Aaron Smith
 * @version 1.0 11/21/2018
 */
public class CalendarComponent extends JComponent {
	private DefaultTableModel dataTable;
	private LocalDate currentDate;
	
	/**
	 * Constructor does most the work.
	 * @param size Used to properly scale the component in pixels.
	 */
	public CalendarComponent(int size) {
		Font font = new Font("Serif", Font.PLAIN, size/20);
		
		// Default calendar to current month
		currentDate = LocalDate.of(LocalDate.now().getYear(), LocalDate.now().getMonthValue(), 1);
		
		// Create a label to display the selected month
		JLabel monthLabel = new JLabel(currentDate.getMonth().name() + " " + currentDate.getYear());
		monthLabel.setFont(font);
		monthLabel.setHorizontalAlignment(JLabel.CENTER);
		
		// Create some buttons to view different months/years in the calendar
		JButton previousMonth = new JButton("<");
		previousMonth.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				currentDate = currentDate.minusMonths(1);
				monthLabel.setText(currentDate.getMonth().name() + " " + currentDate.getYear());
				updateCalendar();
			}
		});
		previousMonth.setPreferredSize(new Dimension(size/10, size/15));
		previousMonth.setFont(font);
		previousMonth.setFocusable(false);
		JButton previousYear = new JButton("<<");
		previousYear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				currentDate = currentDate.minusMonths(12);
				monthLabel.setText(currentDate.getMonth().name() + " " + currentDate.getYear());
				updateCalendar();
			}
		});
		previousYear.setPreferredSize(new Dimension(size/8, size/15));
		previousYear.setFont(font);
		previousYear.setFocusable(false);
		JButton nextMonth = new JButton(">");
		nextMonth.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				currentDate = currentDate.plusMonths(1);
				monthLabel.setText(currentDate.getMonth().name() + " " + currentDate.getYear());
				updateCalendar();
			}
		});
		nextMonth.setPreferredSize(new Dimension(size/10, size/15));
		nextMonth.setFont(font);
		nextMonth.setFocusable(false);
		JButton nextYear = new JButton(">>");
		nextYear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				currentDate = currentDate.plusMonths(12);
				monthLabel.setText(currentDate.getMonth().name() + " " + currentDate.getYear());
				updateCalendar();
			}
		});
		nextYear.setPreferredSize(new Dimension(size/8, size/15));
		nextYear.setFont(font);
		nextYear.setFocusable(false);
		
		// Create a TableModel for our JTable. This will be used to display the calendar.
		dataTable = new DefaultTableModel();
		updateCalendar(); // Updates the dataTable
		
		// Now create a JTable using our TableModel
		JTable table = new JTable(dataTable);
		table.setFont(new Font("Serif", Font.PLAIN, size/20));
		table.setRowHeight(size/18);
		table.setRowSelectionAllowed(true);
		table.setColumnSelectionAllowed(true);
		table.setDefaultEditor(Object.class, null); // Disables user editing of the table
		// Add a mouse listener to detect when the user clicks on a date
		table.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				int row = table.getSelectedRow();
				int column = table.getSelectedColumn();
				Object cellValue = table.getValueAt(row, column);
				if (cellValue != null && cellValue instanceof Integer) {
					System.out.println(cellValue);
					// TODO Display reservations on the specified date
				}
			 }
		});
		
		// Add all of our items to be displayed in the component
		JPanel panel = new JPanel();
		panel.setLayout(new GridLayout(1, 5, 0, 0));
		panel.add(previousYear);
		panel.add(previousMonth);
		panel.add(monthLabel);
		panel.add(nextMonth);
		panel.add(nextYear);
		this.setLayout(new BorderLayout());
		this.add(panel, BorderLayout.NORTH);
		this.add(table, BorderLayout.CENTER);
		this.setVisible(true);
	}
	
	/**
	 * Updates the dataTable to display accurate information of the selected month.
	 */
	private void updateCalendar() {
		String[] days = { "Sun", "Mon", "Tue", "Wed", "Thu", "Fri", "Sat" };
		int startingDayOfWeek = currentDate.getDayOfWeek().getValue(); // getValue() returns a number 1-7.
		if (startingDayOfWeek == 7) { startingDayOfWeek = 0; } // Shift variable so that Sunday = 0 instead of 7.
		// startingDayOfWeek is now 0 if Sunday, 6 if Saturday, or anything in between.
		
		dataTable.setRowCount(0); // Resets the row count
		dataTable.setRowCount((startingDayOfWeek + currentDate.lengthOfMonth() - 1)/7 + 2); // Maximum number of rows needed.
		dataTable.setColumnCount(7); // 7 days in a week
		for (int i = 0; i < days.length; i++) {
			dataTable.setValueAt(days[i], 0, i);
		}
		
		// Now fill in the rest of the rows with numbers for each date
	    for(int day = 1; day <= currentDate.lengthOfMonth(); day++){
	    	dataTable.setValueAt(day, startingDayOfWeek/7 + 1, startingDayOfWeek%7);
	    	startingDayOfWeek++;
	    }		
	}
	
}
