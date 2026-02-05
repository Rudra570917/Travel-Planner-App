package user;
import main.reg_login;
import main.valid_input;

import java.io.*;
import java.sql.*;
import java.sql.Date;
import java.util.*;

public class plane_ticket_booking {

    static void bookflightTicket(Connection conn, Scanner sc, String source, String dest) throws Exception {
        conn.setAutoCommit(false);
        String p = "SELECT * FROM flight_transport where source=? and destination=?";
        PreparedStatement ps = conn.prepareStatement(p);
        ps.setString(1, source);
        ps.setString(2, dest);
        ResultSet allRoutes = ps.executeQuery();
        System.out.println("=>Current Routes :");
        while (allRoutes.next()) {
            System.out.println("->" + allRoutes.getString("source") + " ➝ " + allRoutes.getString("destination"));
        }

        // SQuery for route and seat data
        String routeQuery = "SELECT root_no,seat_no_of_available_seats,seat_no_of_booked_seats,departuretime,arrivaltime,flightDuration,price,flightnumber,airline_name,date,boardingtime FROM flight_transport WHERE LOWER(source) = ? AND LOWER(destination) = ?";
        PreparedStatement pst = conn.prepareStatement(routeQuery);
        pst.setString(1, source.toLowerCase());
        pst.setString(2, dest.toLowerCase());
        ResultSet rs = pst.executeQuery();
        int root_no = -1;
        String availableSeatsCSV = null;
        String bookedSeatsCSV = null;
        Time departuretime = null;
        Time arrivaltime = null;
        String flighDuration = null;
        double price = 0;
        String flight_no = null;
        String date = null;
        String airlinename = null;
        Time flightduration = null;
        Time boardingtime = null;
        if (rs.next()) {
            root_no = rs.getInt("root_no");
            availableSeatsCSV = rs.getString("seat_no_of_available_seats");
            bookedSeatsCSV = rs.getString("seat_no_of_booked_seats");
            departuretime = rs.getTime("departuretime");
            arrivaltime = rs.getTime("arrivaltime");
            price = rs.getDouble("price");
            flight_no = rs.getString("flightnumber");
            date = rs.getString("date");
            airlinename = rs.getString("airline_name");
            flightduration = rs.getTime("flightduration");
            boardingtime = rs.getTime("boardingtime");
        } else {
            System.out.println("-> No route found for given source and destination.");
            return;
        }


        // Step 3: Build seat sets
        Set<String> totalSeats = new LinkedHashSet<>(Arrays.asList(availableSeatsCSV.split(",")));
        Set<String> bookedSeats = new LinkedHashSet<>();
        if (bookedSeatsCSV != null && !bookedSeatsCSV.isEmpty()) {
            bookedSeats.addAll(Arrays.asList(bookedSeatsCSV.split(",")));
        }

        // Step 4: Display seat structure
        System.out.println("\n----- AVAILABLE SEATS ------");
        displaySeats(availableSeatsCSV, bookedSeatsCSV, 5);

        // Step 5: Booking
        System.out.println("\nENTER NUMBER OF TICKETS TO BOOK:");
        int no_of_ticket = 0;
        while (true) {
            if (sc.hasNextInt()) {
                no_of_ticket = sc.nextInt();
                break;
            } else {
                System.out.println("ENTER ONLY NUMBER");
                no_of_ticket = sc.nextInt();
            }
        }
        sc.nextLine();

        ArrayList<String> selectedSeats = new ArrayList<>();
        String seats = "";
        for (int i = 0; i < no_of_ticket; i++) {
            System.out.println("ENTER NAME");
            String name = valid_input.getValidString();
            System.out.println("ENTER PASSPORT NUMBER");
            String passport_no = getValidPassport();
            System.out.println("ENTER MOBILE NUMBER");
            String mobile_no = valid_input.getValidMo_number();
            System.out.println("ENTER SEAT NUMBER TO BOOK (e.g., A1):");
            String seat_no = sc.nextLine().trim().toUpperCase();
            String classtype = null;
            if (seat_no.startsWith("E")) {
                classtype = "ECONOMY";
            } else if (seat_no.startsWith("B")) {
                classtype = "BUSINESS";
            }
            if (!totalSeats.contains(seat_no)) {
                System.out.println("-> Seat " + seat_no + " does not exist.");
                System.out.println("ENTER VALID SEAT NUMBER");
                seat_no = sc.nextLine();
                i--;
            } else if (bookedSeats.contains(seat_no)) {
                System.out.println("-> Seat " + seat_no + " is already booked.");
                System.out.println("ENTER VALID SEAT NUMER");
                seat_no = sc.nextLine();
                i--;
            } else if (selectedSeats.contains(seat_no)) {
                System.out.println("-> You already selected " + seat_no);
                seat_no = sc.nextLine();
                i--;
            } else {
                selectedSeats.add(seat_no);
                seats += seat_no + ",";
            }


            //make payment
            Double total_price = price * no_of_ticket;
            boolean paid = PaymentHandler.makePayment(conn, total_price);
            if (paid) {
                System.out.println("Booking Confirmed!");
            } else {
                System.out.println("Booking Cancelled!");
                return;
            }


                // Step 6: Update seat records
                for (String seat : selectedSeats) {
                    totalSeats.remove(seat);
                    bookedSeats.add(seat);
                }

                String updatedTotalSeatsCSV = String.join(",", totalSeats);
                String updatedBookedSeatsCSV = String.join(",", bookedSeats);

                String booking_update = "INSERT INTO flight_booking_data(passportNo,name,mobile_no,airline_name,flightNumber,class,seat_no,source,destination,departuretime,arrivaltime,flightduration,boardingtime,date,price,u_id) values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
                PreparedStatement psa = conn.prepareStatement(booking_update);
                psa.setString(1, passport_no);
                psa.setString(2, name);
                psa.setString(3, mobile_no);
                psa.setString(4, airlinename);
                psa.setString(5, flight_no);
                psa.setString(6, classtype);
                psa.setString(7, seat_no);
                psa.setString(8, source);
                psa.setString(9, dest);
                psa.setTime(10, departuretime);
                psa.setTime(11, arrivaltime);
                psa.setTime(12, flightduration);
                psa.setTime(13, boardingtime);
                psa.setDate(14, Date.valueOf("2025-07-26"));
                psa.setDouble(15, total_price);
                psa.setInt(16, Integer.parseInt(reg_login.u_idd));
                psa.executeUpdate();
                System.out.println("=> Seats booked successfully: " + selectedSeats);
                String fetch_ticket_no = "SELECT ticket_no from flight_booking_data where name= ? and mobile_no=?";
                PreparedStatement ftno = conn.prepareStatement(fetch_ticket_no);
                ftno.setString(1, name);
                ftno.setString(2, mobile_no);
                ResultSet ftc = ftno.executeQuery();
                int ticket_no = 0;
                if (ftc.next()) {
                    ticket_no = ftc.getInt(1);
                }

                print_ticket(ticket_no, airlinename, classtype, passport_no, name, mobile_no, flight_no, seats, source, dest, departuretime, arrivaltime, flightduration, boardingtime, date, total_price);

                // Step 7: Update DB
                String updateQuery = "UPDATE flight_transport SET seat_no_of_total_seats = ?, seat_no_of_booked_seats = ?, available_seat = ?, seat_booked = ? WHERE root_no = ?";
                PreparedStatement updatePst = conn.prepareStatement(updateQuery);
                updatePst.setString(1, updatedTotalSeatsCSV);
                updatePst.setString(2, updatedBookedSeatsCSV);
                updatePst.setInt(3, totalSeats.size());
                updatePst.setInt(4, bookedSeats.size());
                updatePst.setInt(5, root_no);

                updatePst.executeUpdate();
            }
    }


    static void displaySeats(String totalSeatsCSV, String bookedSeatsCSV, int seatsPerRow) {
        System.out.println(" E MEANS ECONOMY CLASS AND B MEANS BUSINESS CLASS");
        String[] allSeats = totalSeatsCSV.split(",");
        Set<String> booked = new HashSet<>();

        if (bookedSeatsCSV != null && !bookedSeatsCSV.isEmpty()) {
            booked.addAll(Arrays.asList(bookedSeatsCSV.split(",")));
        }

        for (int i = 0; i < allSeats.length; i++) {
            String seat = allSeats[i];
            if (booked.contains(seat)) {
                System.out.print("❌ ");
            } else {

                System.out.print(seat + " ");
            }

            if ((i + 1) % seatsPerRow == 0) {
                System.out.println();
            }
        }
    }

    static void print_ticket(int ticket_no, String airlinename, String classtype, String passportno, String name, String mobile, String flight_no, String seat_no, String source, String dest, Time departuretime, Time arrivaltime, Time boardingtime, Time flightduration, String date, double price) throws Exception {

        System.out.println("------------------------------------------");
        System.out.println("             FLIGHT TICKET                   ");
        System.out.println("AIRLINE NAME :" + airlinename);
        System.out.println("FLIGHT NO :" + flight_no);
        System.out.println("TICKET NO:" + ticket_no);
        System.out.println("CLASS :" + classtype);
        System.out.println("SEAT NUMBER :" + seat_no);
        System.out.println("FROM :" + source);
        System.out.println("TO :" + dest);
        System.out.println("DEPARTURE TIME :" + departuretime);
        System.out.println("ARRIVAL TIME :" + arrivaltime);
        System.out.println("FLIGH DURATION :" + flightduration);
        System.out.println("BOARDING TIME :" + boardingtime);
        System.out.println("DATE :" + date);
        System.out.println("PRICE :" + price);
        System.out.println("NAME :" + name);
        System.out.println("PASSPORT NUMBER :" + passportno);
        System.out.println("MOBILE No :" + mobile);

        String file_name = "flight_ticket" + ticket_no + ".txt";
        BufferedWriter fw = new BufferedWriter(new FileWriter(file_name));
        fw.write("------------------------------------------");
        fw.newLine();
        fw.write("             FLIGHT TICKET                   ");
        fw.newLine();
        System.out.println("AIR LINE NAME :" + airlinename);
        fw.newLine();
        fw.write("FLIGHT NO :" + flight_no);
        fw.newLine();
        fw.write("TICKET NO:" + ticket_no);
        fw.newLine();
        fw.write("CLASS :" + classtype);
        fw.newLine();
        fw.write("SEAT NUMBER :" + seat_no);
        fw.newLine();
        fw.write("FROM :" + source);
        fw.newLine();
        fw.write("TO :" + dest);
        fw.newLine();
        fw.write("DEPARTURET-TIME :" + departuretime);
        fw.newLine();
        fw.write("ARRIVAL-TIME  :" + arrivaltime);
        fw.newLine();
        fw.write("FLIGH-DURATION :" + flightduration);
        fw.newLine();
        fw.write("BOARDING-TIME :" + boardingtime);
        fw.newLine();
        fw.write("DATE :" + date);
        fw.newLine();
        fw.write("PRICE :" + price);
        fw.newLine();
        fw.write("NAME :" + name);
        fw.newLine();
        fw.write("PASSPORT NUMBER :" + passportno);
        fw.newLine();
        fw.write("MOBILE No :" + mobile);

        fw.close();
        System.out.println("YOUR TICKED SAVED AS " + file_name);
    }

    //validation Method For Passport
    public static String getValidPassport() {
        Scanner sc = new Scanner(System.in);
        String passportNo;

        // Indian: 1 capital letter + 7 digits
        String indianRegex = "^[A-Z][0-9]{7}$";
        // US: 9 digits
        String usRegex = "^[0-9]{9}$";

        while (true) {
            passportNo = sc.nextLine().trim();

            if (passportNo.matches(indianRegex) || passportNo.matches(usRegex)) {
                System.out.println("=> Valid passport number accepted: " + passportNo);
                return passportNo;
            } else {
                System.out.println("-> Invalid! Please enter a valid passport number.");
            }
        }
    }

}

