package user;
import main.reg_login;
import main.valid_input;
import java.io.*;
import java.sql.*;
import java.sql.Date;
import java.util.*;

//class for bus ticket booking
public class bus_ticket_booking {

    //method for bus booking
    public static void busbooking(Connection conn, Scanner sc,String source,String dest)throws Exception {
        conn.setAutoCommit(false);

        //display available routes for source and destination
        String p = "SELECT * FROM bus_transport where source=? and destination=?";
        PreparedStatement ps = conn.prepareStatement(p);
        ps.setString(1, source);
        ps.setString(2, dest);
        ResultSet allRoutes = ps.executeQuery();
        System.out.println("=>Current Routes :");
        while (allRoutes.next()) {
            System.out.println("->" + allRoutes.getString("source") + " ➝ " + allRoutes.getString("destination")+"\nPRICE :"+allRoutes.getDouble("Price"));
        }

        // Query for route and seat data
        String routeQuery = "SELECT root_no,seat_no_of_available_seats,seat_no_of_booked_seats,timing,price,bus_no,date FROM bus_transport WHERE LOWER(source) = ? AND LOWER(destination) = ?";
        PreparedStatement pst = conn.prepareStatement(routeQuery);
        pst.setString(1, source.toLowerCase());
        pst.setString(2, dest.toLowerCase());
        ResultSet rs = pst.executeQuery();
        int root_no = -1;
        String availableSeatsCSV = null;
        String bookedSeatsCSV = null;
        String time = null;
        double price = 0;
        String bus_no = null;
        String date = null;
        if (rs.next()) {
            root_no = rs.getInt("root_no");
            availableSeatsCSV = rs.getString("seat_no_of_available_seats");
            bookedSeatsCSV = rs.getString("seat_no_of_booked_seats");
            time = rs.getString("timing");
            price = rs.getDouble("price");
            bus_no = rs.getString("bus_no");
            date = rs.getString("date");
        } else {
            System.out.println("No route found for given source and destination.");
            return;
        }


        //take traveler data
        System.out.println("ENTER NAME OR KEEP BLANK FOR EXIT");
        String name = valid_input.getValidString();
        if(name==null)
        {
            return;
        }
        System.out.println("ENTER MOBILE NUMBER OR KEEP BLANK FOR EXIT");
        String mobile_no = valid_input.getValidMo_number();
        if(mobile_no==null)
        {
            return;
        }

        //Build seat sets
        ArrayList<String> totalSeats=new ArrayList<>(Arrays.asList(availableSeatsCSV.split(",")));
        //Set<String> totalSeats = new LinkedHashSet<>(Arrays.asList(availableSeatsCSV.split(",")));
        ArrayList<String> bookedSeats=new ArrayList<>();
        //Set<String> bookedSeats = new LinkedHashSet<>();
        if (bookedSeatsCSV != null && !bookedSeatsCSV.isEmpty()) {
            bookedSeats.addAll(Arrays.asList(bookedSeatsCSV.split(",")));
        }

        //Display seat structure
        System.out.println("\n----- AVAILABLE SEATS ------");
        displaySeats(availableSeatsCSV, bookedSeatsCSV, 5);

        //Booking
        System.out.println("\nENTER NUMBER OF TICKETS TO BOOK:");
        int no_of_ticket =valid_input.getIntInput(sc);

        ArrayList<String> selectedSeats = new ArrayList<>();
        String seats = "";
        for (int i = 0; i < no_of_ticket; i++) {
            System.out.println("ENTER SEAT NUMBER TO BOOK (e.g., A1): OR KEEP BLANK FOR EXIT");
            String seat_no = sc.nextLine().trim().toUpperCase();
            //if(seat_no.startsWith())
            if(seat_no.isEmpty())
            {
                return;
            }
            else {
                if (!totalSeats.contains(seat_no)) {
                    System.out.println("-->Seat " + seat_no + " does not exist.");
                    i--;
                } else if (bookedSeats.contains(seat_no)) {
                    System.out.println("-->Seat " + seat_no + " is already booked.");
                    i--;
                } else if (selectedSeats.contains(seat_no)) {
                    System.out.println("-->You already selected " + seat_no);
                    i--;
                } else {
                    selectedSeats.add(seat_no);
                    seats += seat_no + ",";
                }
            }
        }

        //make payment
        Double total_price = price * no_of_ticket;
        boolean paid = PaymentHandler.makePayment(conn, total_price);
        if (paid) {
            System.out.println("Booking Confirmed!");
        } else {
            System.out.println("Booking Cancelled!");
        }


        //Update seat records
        for (String seat : selectedSeats) {
            totalSeats.remove(seat);
            bookedSeats.add(seat);
        }
        String updatedTotalSeatsCSV = String.join(",", totalSeats);
        String updatedBookedSeatsCSV = String.join(",", bookedSeats);

        //Update Database
        String updateQuery = "UPDATE bus_transport SET seat_no_of_total_seats = ?, seat_no_of_booked_seats = ?, available_seat = ?, seat_booked = ? WHERE root_no = ?";
        PreparedStatement updatePst = conn.prepareStatement(updateQuery);
        updatePst.setString(1, updatedTotalSeatsCSV);
        updatePst.setString(2, updatedBookedSeatsCSV);
        updatePst.setInt(3, totalSeats.size());
        updatePst.setInt(4, bookedSeats.size());
        updatePst.setInt(5, root_no);

        updatePst.executeUpdate();

        //insert user details in database
        String booking_update = "INSERT INTO bus_booking_data(name,mobile_no,bus_no,seat_no,source,destination,timing,date,price,u_id) values(?,?,?,?,?,?,?,?,?,?)";
        PreparedStatement psa = conn.prepareStatement(booking_update);
        psa.setString(1, name);
        psa.setString(2, mobile_no);
        psa.setString(3, bus_no);
        psa.setString(4, seats);
        psa.setString(5, source);
        psa.setString(6, dest);
        psa.setString(7, time);
        psa.setDate(8, Date.valueOf("2025-07-26"));
        psa.setDouble(9, pay.amt_after_dis);
        psa.setInt(10, Integer.parseInt(reg_login.u_idd));
        psa.executeUpdate();


        System.out.println("=> Seats booked successfully: " + selectedSeats);
        String fetch_ticket_no = "SELECT ticket_no from bus_booking_data where name= ? and mobile_no=?";
        PreparedStatement ftno = conn.prepareStatement(fetch_ticket_no);
        ftno.setString(1, name);
        ftno.setString(2, mobile_no);
        ResultSet ftc = ftno.executeQuery();
        int ticket_no = 0;
        if (ftc.next()) {
            ticket_no = ftc.getInt(1);
        }

        //print ticket& save ticket
        print_ticket(ticket_no, name, mobile_no, bus_no, seats, source, dest, time, date, total_price);
    }

    //method for display seats
    static void displaySeats (String totalSeatsCSV, String bookedSeatsCSV,int seatsPerRow){
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

                System.out.print(seat + "  ");
            }

            if ((i + 1) % seatsPerRow == 0) {
                System.out.println();
            }
        }
    }

    //display for print and save ticket
    static void print_ticket ( int ticket_no, String name, String mobile, String bus_no, String seat_no, String
            source, String dest, String timing, String date,double price) throws Exception
    {
        //print ticket
        System.out.println("------------------------------------------");
        System.out.println("               BUS TICKET                 ");
        System.out.println("------------------------------------------");
        System.out.println("BUS NO :" + bus_no);
        System.out.println("TICKET NO:" + ticket_no);
        System.out.println("SEAT NUMBER :" + seat_no);
        System.out.println("FROM :" + source);
        System.out.println("TO :" + dest);
        System.out.println("TIME :" + timing);
        System.out.println("DATE :" + date);
        System.out.println("PRICE :" + pay.amt_after_dis);
        System.out.println("NAME :" + name);
        System.out.println("MOBILE No :" + mobile);

        //save ticket
        String file_name = "bus_ticket" + ticket_no + ".txt";
        BufferedWriter fw = new BufferedWriter(new FileWriter(file_name));
        fw.write("------------------------------------------");
        fw.newLine();
        fw.write("             BUS TICKET                   ");
        fw.newLine();
        fw.write("------------------------------------------");
        fw.newLine();
        fw.write("BUS NO :" + bus_no);
        fw.newLine();
        fw.write("TICKET NO:" + ticket_no);
        fw.newLine();
        fw.write("SEAT NUMBER :" + seat_no);
        fw.newLine();
        fw.write("FROM :" + source);
        fw.newLine();
        fw.write("TO :" + dest);
        fw.newLine();
        fw.write("TIME :" + timing);
        fw.newLine();
        fw.write("DATE :" + date);
        fw.newLine();
        fw.write("PRICE :" + pay.amt_after_dis);
        fw.newLine();
        fw.write("NAME :" + name);
        fw.newLine();
        fw.write("MOBILE No :" + mobile);

        fw.close();
        System.out.println("=> YOUR TICKED SAVED AS " + file_name);
    }
}





