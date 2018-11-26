package model;

import java.util.*;
import java.io.*;
import java.time.LocalDate;

/**
 * Utility class used to read and write from reservations.txt.
 * @author Aaron Smith
 * @version 1.0 11/25/2018
 */
public class ReservationReaderWriter {
	private ReservationReaderWriter() { }
	
	/**
	 * Reads from reservations.txt, parses the data, creates the 
	 * relevant Reservation objects and returns a list with all of them.
	 * @return
	 */
	public static List<Reservation> readReservations() {
		List<Reservation> reservations = new ArrayList<Reservation>();
		try {
			File file = new File("..//..//res//reservations.txt");
			Scanner scanner = new Scanner(file);
			while (scanner.hasNextLine()) {
				String line = scanner.nextLine();
				if (line.charAt(0) == '#') { // Skip over lines with that symbol
					continue;
				}
				String[] fields = line.split(",");
				User user = new User(fields[0], fields[1], fields[2]); // id, username, password
				Room.Type type;
				if (fields[4].equals("ECONOMIC")) {
					type = Room.Type.ECONOMIC;
				} else if (fields[4].equals("LUXURIOUS")) {
					type = Room.Type.LUXURIOUS;
				} else {
					throw new IOException("Room type isn't specified correctly in reservations.txt");
				}
				Room room = new Room(Integer.parseInt(fields[3]), type);
				LocalDate startDate = LocalDate.of(Integer.parseInt(fields[5]), 
										Integer.parseInt(fields[6]), Integer.parseInt(fields[7]));
				LocalDate endDate = LocalDate.of(Integer.parseInt(fields[8]), 
										Integer.parseInt(fields[9]), Integer.parseInt(fields[10]));
				reservations.add(new Reservation(user, room, startDate, endDate));
			}
			scanner.close();
		} catch(Exception x) {
			System.out.println(x.getMessage());
		}
		return reservations;
	}
	
	public static void writeReservations(List<Reservation> reservations) {
		try {
			FileWriter fw = new FileWriter(new File("..//..//res//reservations.txt"));
			fw.write("#id,username,password,roomNumber,roomType,startYear,"
					+ "startMonth,startDay,endYear,endMonth,endDay\n");
			for (Reservation reservation : reservations) {
				String line = "";
				User user = reservation.getUser();
				Room room = reservation.getRoom();
				LocalDate startDate = reservation.getStartDate();
				LocalDate endDate = reservation.getEndDate();
				//TODO how to get password of a user?
				line += user.getID() + "," + user.getUsername() + "," + "passwordHere" + ",";
				line += room.getNumber() + "," + room.getType() + ",";
				line += startDate.getYear() + "," + startDate.getMonthValue() + "," + startDate.getDayOfMonth() + ",";
				line += endDate.getYear() + "," + endDate.getMonthValue() + "," + endDate.getDayOfMonth();
				fw.write(line + "\n");
			}
			fw.close();
		} catch(Exception x) {
			System.out.println(x.getMessage());
		}
	}
}
