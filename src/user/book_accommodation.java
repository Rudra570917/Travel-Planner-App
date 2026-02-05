package user;

import java.io.*;
import java.sql.*;
import java.util.Scanner;
import main.reg_login;
import main.valid_input;
import main.Database_Connection;

//class for book_accommodation(HOTEL_BOOKING)
public class book_accommodation {

    //method for book accommodation
    public static void book_accomodation() throws Exception {

        Connection conn = Database_Connection.Connect();
        Scanner sc = new Scanner(System.in);

        conn.setAutoCommit(false);

        //display list of hotels
        String hotels = "SELECT * FROM accommodation_hotels";
        Statement st = conn.createStatement();
        ResultSet rs = st.executeQuery(hotels);
        while (rs.next()) {
            System.out.println("HOTEL ID :" + rs.getInt(1) + "HOTEL NAME :" + rs.getString(2) + " ADDRESS :" + rs.getString(3) + " EXPENSE/DAY :" + rs.getDouble(4) + " EXPENSE/NIGHT :" + rs.getString(5));
        }

        //take hotel_id to book
        System.out.println("ENTER HOTEL ID TO BOOK OR ENTER 0 FOR EXIT");
        int hid = valid_input.getIntInput(sc);
        if (hid == 0) {
            return;
        } else {

            //fetch hotel data from user entered hotel id
            String sel = "SELECT * FROM accommodation_hotels where hotel_id=?";
            PreparedStatement pst = conn.prepareStatement(sel);
            pst.setInt(1, hid);
            ResultSet rs1 = pst.executeQuery();
            int h_id = 0;
            String hotel_name = null;
            String address = null;
            Double expense_fullday = 0.0;
            Double expense_night = 0.0;
            int total_rooms = 0;
            int available_rooms = 0;
            while (rs1.next()) {
                h_id = rs1.getInt(1);
                hotel_name = rs1.getString(2);
                address = rs1.getString(3);
                expense_fullday = rs1.getDouble(4);
                expense_night = rs1.getDouble(5);
                total_rooms = rs1.getInt(6);
                available_rooms = rs1.getInt(7);
            }


            //Take user Data
            System.out.println("ENTER NAME OR KEEP BLANK FOR EXIT");
            String name = valid_input.getValidString();
            if (name == null) {
                return;
            }
            System.out.println("ENTER MOBILE NUMBER OR KEEP BLANK FOR EXIT");
            String mo_no = valid_input.getValidMo_number();
            if (mo_no == null) {
                return;
            }
            System.out.println("ENTER NO OF PERSON TO STAY");
            int no_of_person = valid_input.getIntInput(sc);


            if (no_of_person > (available_rooms * 2)) {
                System.out.println("NOT AVAILABLE ROOMS FOR ALL ");
                System.out.println("AVAILABLE ROOMS :" + available_rooms + " FOR PERSOS :" + available_rooms * 2);
            }


            System.out.println("ENTER NO OF DAYS TO STAY");
            int days=valid_input.getIntInput(sc);

            String stay = null;
            while (true) {
                System.out.println("ENTER STAY FOR FULLDAY/NIGHT OR KEEP BLANK FOR EXIT");
                stay = valid_input.getValidString();
                if (stay == null) {
                    return;
                } else {
                    break;
                }
            }

            double total_expense = 0.0;
            int room_to_book = 0;
            if (no_of_person % 2 == 0) {
                room_to_book = no_of_person / 2;
            } else {
                room_to_book = (no_of_person / 2) + 1;
            }
            if (stay.equalsIgnoreCase("fullday")) {
                total_expense = expense_fullday * room_to_book*days;
            } else if (stay.equalsIgnoreCase("night")) {
                total_expense = expense_night * room_to_book*days;
            }

            // make payment
            System.out.println("PAYABLE AMOUNT :"+total_expense);
            boolean paid = PaymentHandler.makePayment(conn, total_expense);
            if (paid) {
                System.out.println("Booking Confirmed!");
            } else {
                System.out.println("Booking Cancelled!");
            }


                //Update database
                String update = "update accommodation_hotels set available_rooms=? where hotel_id=?";
                PreparedStatement up = conn.prepareStatement(update);
                up.setInt(1, available_rooms - room_to_book);
                up.setInt(2, h_id);
                int n = up.executeUpdate();

                //insert into database
                String insert = "INSERT INTO accommodation_booking_data values(?,?,?,?,?,?,?,?,?)";
                PreparedStatement pt = conn.prepareStatement(insert);
                pt.setInt(1, h_id);
                pt.setString(2, hotel_name);
                pt.setString(3, address);
                pt.setDouble(4, total_expense);
                pt.setInt(5, Integer.parseInt(reg_login.u_idd));
                pt.setInt(6,days);
                pt.setString(7,stay);
                pt.setInt(8,pay.discount);
                pt.setDouble(9,pay.amt_after_dis);
                pt.executeUpdate();


                //display hotel_bill
                System.out.println("---------------" + hotel_name.toUpperCase() + "--------------");
                System.out.println("USER NAME :" + name);
                System.out.println("STAY FOR :" + stay);
                System.out.println("ROOM BOOKED :" + room_to_book);
                System.out.println("TOTAL AMOUNT :" + pay.amt_after_dis);
                System.out.println("ADDRESS :" + address);

                //save hotel bill
                int i = 0;
                i++;
                String file_name = "hotel_bill" + i + ".txt";
                File f = new File(file_name);
                BufferedWriter fw = new BufferedWriter(new FileWriter(f));
                fw.write("---------------" + hotel_name.toUpperCase() + "--------------");
                fw.newLine();
                fw.write("USER NAME :" + name);
                fw.newLine();
                fw.write("STAY FOR :" + stay);
                fw.newLine();
                fw.write("ROOM BOOKED :" + room_to_book);
                fw.newLine();
                fw.write("TOTAL AMOUNT :" +pay.amt_after_dis);
                fw.newLine();
                fw.write("ADDRESS :" + address);
                fw.close();
            }
        }
}
