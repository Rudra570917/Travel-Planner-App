package admin;

import java.sql.*;
import java.util.Scanner;
import main.Database_Connection;
import main.valid_input;
import user.*;

//calss for admin to add trips
public class add_trips {

    //method for add trips
    public static void add_trip_details() throws Exception {
        Scanner sc=new Scanner(System.in);
        Connection conn=Database_Connection.Connect();
        int choice=0;
        do {
            //Interface for add_trip
            System.out.println("1.BUS");
            System.out.println("2.TRAIN");
            System.out.println("3.FLIGHT");
            System.out.println("4.HOLIDAY PLAN");
            System.out.println("5.ENTER HOTEL DETAILS");
            System.out.println("6.EXIT");
            choice=valid_input.getIntInput(sc);

            switch (choice)
            {
                case 1:
                    insert_bus_details(conn,sc);
                    break;
                case 2:
                    add_train_root(conn);
                    break;
                case 3:
                    add_flight(conn);
                    break;
                case 4:
                    holidayplanner.createPlan(conn);
                    break;
                case 5:
                    break;
                case 6:
                    System.out.println("EXTING...");
                default:
                    System.out.println("ENTER VALID CHOICE");
                    break;
            }
        }while (choice!=6);
    }


    //method for insert Bus Routs
    public static void insert_bus_details(Connection conn, Scanner sc) throws Exception {

        //Insert bus_details in database
        String q2 = "INSERT INTO bus_transport VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?)";
        PreparedStatement pst = conn.prepareStatement(q2);

        //TAKE ROUTE NUMBER
        System.out.println("ENTER ROUTE NO");
        int root_no = valid_input.getIntInput(sc);

        pst.setInt(1, root_no);

        // BUS NUMBER VALIDATION
        // Example: AS1670, RJ45, etc.
        String bus_no = "";
        while (true) {
            System.out.println("ENTER BUS NUMBER (e.g., AS-1670): ");
            try {
                bus_no = sc.nextLine();
                if (bus_no.isEmpty()) {
                    System.out.println("BUS NO ISN'T NULL");
                    bus_no = sc.nextLine();
                } else {
                    break;
                }
            }
            catch(Exception e)
            {
                System.out.println(("ENTER VALID BUS NUMBER (e.g :AS-1670)"));
            }
        }
        pst.setString(2, bus_no);

        // SOURCE
        System.out.println("ENTER SOURCE OR KEEP BLANK FOR EXIT");
        String source =valid_input.getValidString();
        if(source==null)
        {
            return;
        }
        pst.setString(3, source);


        // DESTINATION
        System.out.println("ENTER DESTINATION OR KEEP BLANK FOR EXIT");
        String destination =valid_input.getValidString();
        if(destination==null)
        {
            return;
        }
        pst.setString(4, destination);

        String time =valid_input.getValidTime();
        pst.setString(5, time);

        System.out.println("ENTER DATE");
        String date=valid_input.getValidDate();

        pst.setDate(6,java.sql.Date.valueOf(date));

        System.out.println("ENTER TICKET PRICE");
        double price=valid_input.getValidDouble();
        pst.setDouble(7,price);

        // DEFAULT SEATS
        pst.setInt(8, 48);           // total_seat
        pst.setInt(9, 48);            // available_seat
        pst.setInt(10, 0);// seat_booked

        StringBuilder sb = new StringBuilder();
        for (int i = 1; i <= 48; i++) {
            sb.append("A").append(i);
            if (i < 48) {
                sb.append(", ");
            }
        }
        pst.setString(11, sb.toString());        // seat_no_of_total_seats
        pst.setString(12, sb.toString());        // seat_no_of_available_seats
        pst.setString(13, "");       // seat_no_of_booked_seats

        pst.executeUpdate();
        System.out.println("=> Bus added successfully!");
    }


    //method for add Train Routs
    public static void add_train_root(Connection conn) throws Exception {

        //Insert data in database
        String add_train = "INSERT INTO train_transport ( root_no, train_name, source, destination, timing, date, price, " +
                "total_coach, total_seat, available_seat, seat_booked, " +
                "seat_no_of_total_seats, seat_no_of_available_seats, seat_no_of_booked_seats,coaches"
                + ") VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        PreparedStatement pst = conn.prepareStatement(add_train);

        Scanner sc=new Scanner(System.in);

        System.out.println("ENTER ROOT NO");
        int root_no=valid_input.getIntInput(sc);
        pst.setInt(1, root_no); // root_no

        System.out.println("ENTER TRAIN NAME OR KEEP BLANK FOR EXIT");
        String tname=valid_input.getValidString();
        if(tname==null)
        {
            return;
        }
        pst.setString(2, tname); // train_name

        System.out.println("ENTER SOURCE OR KEEP BLANK FOR EXIT");
        String source=valid_input.getValidString();
        if(source==null)
        {
            return;
        }
        pst.setString(3, source); // source

        System.out.println("ENTER DESTINATION OR KEEP BLANK FOR EXIT");
        String destination=valid_input.getValidString();
        if(destination==null)
        {
            return;
        }
        pst.setString(4,destination); // destination

        System.out.println("ENTER TIME (HH:MM:SS)");
        String time=valid_input.getValidTime();
        pst.setTime(5, java.sql.Time.valueOf(time)); // timing

        System.out.println("ENTER DATE (YYYY-MM-DD)");
        String date=valid_input.getValidDate();
        pst.setDate(6, java.sql.Date.valueOf(date)); // date

        System.out.println("ENTER PRICE");
        double price=valid_input.getValidDouble();
        pst.setDouble(7, price); // price

        System.out.println("ENTER TOTAL NO OF COACH");
        int no_of_coach=valid_input.getIntInput(sc);
        pst.setInt(8, no_of_coach); // total_coach

        System.out.println("ENTER TOTAL NO OF SEATS");
        int no_of_seats=valid_input.getIntInput(sc);
        pst.setInt(9, no_of_seats); // total_seat


        pst.setInt(10, no_of_seats); // available_seat
        pst.setInt(11, 0); // seat_booked

        // Loop to generate seats: A1-A10, B1-B10, ..., up to rowCount
        String seats = "";
        for (int i = 1; i <= 20; i++) {
            seats += "A" + i + ",";
        }
        for (int i = 1; i <= 20; i++) {
            seats += "B" + i + ",";
        }
        for (int i = 1; i <= 20; i++) {
            seats += "C" + i + ",";
        }
        for (int i = 1; i <= 20; i++) {
            seats += "D" + i + ",";
        }


        pst.setString(12, seats); // seat_no_of_total_seats
        pst.setString(13, seats); // seat_no_of_available_seats
        pst.setString(14, null);// seat_no_of_booked_seats

        pst.executeUpdate();
    }

    //method for add flight details
    public static void add_flight(Connection conn) throws Exception {

        //insert data in database
        String insert = "INSERT INTO flight_transport (" +
                "root_no, airline_name, flightNumber, source, destination, " +
                "departuretime, arrivaltime, flighDuration, boardingtime, date, price, " +
                "classtype, total_seat, available_seat, seat_booked, " +
                "seat_no_of_total_seats, seat_no_of_available_seats, seat_no_of_booked_seats" +
                ") VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        PreparedStatement pst = conn.prepareStatement(insert);

        Scanner sc = new Scanner(System.in);
        System.out.println("ENTER ROOT NO");
        int root_no =valid_input.getIntInput(sc);

        System.out.println("ENTER AIRLINE NAME OR KEEP BLANK FOR EXIT");
        String airline_name = valid_input.getValidString();
        if(airline_name==null)
        {
            return;
        }
        System.out.println("ENTER FLIGHT NO");
        String flightNumber = null;
        while (true) {
            try {
                flightNumber = sc.nextLine();
                if(!flightNumber.isEmpty()) {
                    break;
                }
            } catch (Exception e) {
                System.out.println("ENTER VALID FLIGHT NO");
            }
            System.out.println("ENTER SOURCE OR KEEP BLANK FOR EXIT");
            String source = valid_input.getValidString();
            if(source==null)
            {
                return;
            }

            System.out.println("ENTER DESTINATION OR KEEP BLANK FOR EXIT");
            String destination = valid_input.getValidString();
            if(destination==null)
            {
                return;
            }
            // TAKE DEPARTURE TIME(HH:MM:SS)
            Time depTime = Time.valueOf(valid_input.getValidTime());

            //TAKE ARRIVAL TIME(HH:MM:SS)
            Time arrTime = Time.valueOf(valid_input.getValidTime());

            //TAKE FLIGHT DURATION(HH:MM:SS)
            Time flightDuration = Time.valueOf(valid_input.getValidTime());

            //TAKE BOARDING TIME(HH:MM:SS)")
            Time boardingTime = Time.valueOf(valid_input.getValidTime());

            //TAKE DATE (YYYY-MM-DD)
            Date date = Date.valueOf(valid_input.getValidDate());

            //Take Ticket Price
            System.out.println("ENTER TICKET PRICE");
            double price =valid_input.getValidDouble();

            String flightClass = "Economy/BUSINESS";


            int rowCount = 4;        // A, B, C, D
            int seatsPerRow = 10;    // A1-A10, etc.
            int totalSeats = rowCount * seatsPerRow;
            int availableSeats = totalSeats;
            int bookedSeats = 0;

            String totalSeatsStr = generateSeats(rowCount, seatsPerRow);
            String availableSeatsStr = totalSeatsStr;
            String bookedSeatsStr = "";

            //set values to prepared statement
            pst.setInt(1, root_no);
            pst.setString(2, airline_name);
            pst.setString(3, flightNumber);
            pst.setString(4, source);
            pst.setString(5, destination);
            pst.setTime(6, depTime);
            pst.setTime(7, arrTime);
            pst.setTime(8, flightDuration);
            pst.setTime(9, boardingTime);
            pst.setDate(10, date);
            pst.setDouble(11, price);
            pst.setString(12, flightClass);
            pst.setInt(13, totalSeats);
            pst.setInt(14, availableSeats);
            pst.setInt(15, bookedSeats);
            pst.setString(16, totalSeatsStr);
            pst.setString(17, availableSeatsStr);
            pst.setString(18, bookedSeatsStr);

            pst.executeUpdate();
            System.out.println("Flight inserted successfully.");
            pst.close();
        }
    }
    //method for generate seats
    static String generateSeats ( int rowCount, int seatsPerRow){
        StringBuilder sb = new StringBuilder();
        for (char row = 'E'; row < 'E' + rowCount; row++) {
            for (int seat = 1; seat <= seatsPerRow; seat++) {
                sb.append(row).append(seat).append(",");
            }
        }
        if (sb.length() > 0) sb.setLength(sb.length() - 1); // Remove trailing comma
        return sb.toString();
    }

}
