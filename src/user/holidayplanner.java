package user;

import java.sql.*;
import java.util.Scanner;

import main.valid_input;

//class for holiday planner
public class holidayplanner {

    //method for run planner method
        public static void runPlanner(Connection conn) throws Exception {
            Scanner sc=new Scanner(System.in);
            int choice=0;
            boolean b=true;
            while (b) {
                //interface for holiday planner
                System.out.println("===== HOLIDAY PLANNER =====");
                System.out.println("1. SELECT A HOLIDAY PLAN");
                System.out.println("2. CREATE A NEW HOLIDAY PLAN");
                System.out.println("3. EXIT");
                System.out.print("Enter your choice: ");
                if (sc.hasNextInt()) {
                    choice = sc.nextInt();
                }

                sc.nextLine(); //

                switch (choice) {
                    case 1:
                        selectPlan(conn);
                        break;
                    case 2:
                        createPlan(conn);
                        break;
                    case 3:
                        System.out.println("EXTING...");
                        b=false;
                        break;
                    default:
                        System.out.println("ENTER VALID CHOICE");
                        break;
                }
            }
        }

        //method for select plans
        public static void selectPlan(Connection conn) throws Exception {
            Scanner sc=new Scanner(System.in);
            Statement st=conn.createStatement();

            //display available plans
            ResultSet rs = st.executeQuery("SELECT * FROM holiday_plans");
            System.out.println("Available Holiday Plans:");
            //
            int id=0;
            String packageName=null;
            String source=null;
            String dest=null;
            int days=0;
            int nights=0;
            double expense=0;
            String hotel=null;


            while (rs.next()) {
                id = rs.getInt("id");
                packageName = rs.getString("package_name");
                source = rs.getString("source");
                dest = rs.getString("destination");
                days = rs.getInt("day_of_plan");
                nights = rs.getInt("night_of_plan");
                expense = rs.getDouble("expense");
                hotel = rs.getString("hotel_name");
                System.out.println(id + ". " + packageName + " (" + source + " to " + dest + ") " +
                        days + "D/" + nights + "N - Rs." + expense + " - Hotel: " + hotel);
            }

            int selectedId;
            System.out.println("Enter the ID of the plan you want to book: ");
            selectedId=valid_input.getIntInput(sc);

            //fetch details of selected plan
            rs = st.executeQuery("SELECT * FROM holiday_plans WHERE id = " + selectedId);
            String source1=null;
            String dest1=null;
            String mode=null;
            if (rs.next()) {
                source1=rs.getString("source");
                dest1=rs.getString("destination");
                mode = rs.getString("via_plan");
                System.out.println("Booking tickets for your plan...");



                switch (mode.toLowerCase()) {
                    case "flight" :
                        plane_ticket_booking.bookflightTicket(conn,sc,source1,dest1);
                        if (!sc.hasNextLine() || sc.nextLine().isBlank()) return;
                        plane_ticket_booking.bookflightTicket(conn,sc,dest1,source1);
                        if (!sc.hasNextLine() || sc.nextLine().isBlank()) return;
                        book_accommodation.book_accomodation();
                        if (!sc.hasNextLine() || sc.nextLine().isBlank()) return;
                        break;

                    case "train" :
                        train_ticket_booking.booktrainTicket(conn,sc,source1,dest1);
                        if (!sc.hasNextLine() || sc.nextLine().isBlank()) return;
                        train_ticket_booking.booktrainTicket(conn,sc,dest1,source1);
                        if (!sc.hasNextLine() || sc.nextLine().isBlank()) return;
                        book_accommodation.book_accomodation();
                        if (!sc.hasNextLine() || sc.nextLine().isBlank()) return;
                        break;

                    case "bus" :
                        bus_ticket_booking.busbooking(conn,sc,source1,dest1);
                        if (!sc.hasNextLine() || sc.nextLine().isBlank()) return;
                        bus_ticket_booking.busbooking(conn,sc,dest1,source1);
                        if (!sc.hasNextLine() || sc.nextLine().isBlank()) return;
                        book_accommodation.book_accomodation();
                        if (!sc.hasNextLine() || sc.nextLine().isBlank()) return;
                        break;

                    default :
                        System.out.println("Invalid travel mode.");
                        break;
                }

            } else {
                //System.out.println("Invalid plan ID.");
                System.out.println("ENTER VALID PLAN ID OR ENTER 0 FOR EXIT");
                while (true) {
                    if (sc.hasNextInt()) {
                        selectedId = sc.nextInt();
                        if(selectedId==0)
                        {
                            return;
                        }
                        break;
                    } else {
                        System.out.println("ENTER VALID PLAN ID OR ENTER 0 FOR EXIT");
                        sc.next();
                    }
                }
            }
        }

        //method for create holiday plan
        public static void createPlan(Connection conn) throws Exception {
            Scanner sc=new Scanner(System.in);
            System.out.print("Enter package name: ");
            String name = valid_input.getValidString();

            System.out.print("Enter source: ");
            String source =valid_input.getValidString();

            System.out.print("Enter destination: ");
            String dest =valid_input.getValidString();

            System.out.print("Enter number of days: ");
            int days=valid_input.getIntInput(sc);

            System.out.print("Enter number of nights: ");
            int nights=valid_input.getIntInput(sc);

            System.out.print("Enter travel mode (bus/train/flight): ");
            String mode =valid_input.getValidString();

            System.out.print("Enter hotel name: ");
            String hotel =valid_input.getValidString();

            System.out.print("Enter hotel expense: ");
            double hotelExpense = valid_input.getValidDouble();

            System.out.print("Enter overall package expense: ");
            double expense = valid_input.getValidDouble();

            System.out.print("Enter travel date (YYYY-MM-DD): ");
            String dateStr = valid_input.getValidDate();
            Date sqlDate = Date.valueOf(dateStr);

            //insert data into database
            String insert = "INSERT INTO holiday_plans(package_name, source, destination, day_of_plan, night_of_plan, " +
                    "expense, hotel_name, hotel_expense, via_plan, date) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

            PreparedStatement pst = conn.prepareStatement(insert);
            pst.setString(1, name);
            pst.setString(2, source);
            pst.setString(3, dest);
            pst.setInt(4, days);
            pst.setInt(5, nights);
            pst.setDouble(6, expense);
            pst.setString(7, hotel);
            pst.setDouble(8, hotelExpense);
            pst.setString(9, mode);
            pst.setDate(10, sqlDate);

            int n = pst.executeUpdate();
            if (n > 0) {
                System.out.println("Holiday plan created successfully!");
            } else {
                System.out.println("Failed to create plan.");
            }
            pst.close();
        }
    }


