package user;

import main.reg_login;
import main.valid_input;

import java.sql.Connection;
import java.util.Scanner;
//class showing user menu
public class user_menu {
   public static void user_menu(Scanner sc, Connection conn) throws Exception {
        int choice2 = 0;
        System.out.println("------------------------------------");
        System.out.println("       | WELCOME " + reg_login.u_name.toUpperCase() + " |");
        System.out.println("------------------------------------");
        while (choice2 != 8) {
            System.out.println("---------------------");
            System.out.println("1.HOLIDAY PACKAGES");
            System.out.println("2.BOOK YOUR TICKET");
            System.out.println("3.PACKING LIST GENERATOR");
            System.out.println("4.BOOK ACCOMMODATION(HOTEL BOOKING)");
            System.out.println("5.MANAGE BUDGET");
            System.out.println("6.VIEW TRIP SUMMARY");
            System.out.println("7.LOGOUT");
            System.out.println("---------------------");
            System.out.println("ENTER YOUR CHOICE");
            choice2= valid_input.getIntInput(sc);


            switch (choice2) {
                case 1:
                    holidayplanner.runPlanner(conn);
                    break;
                case 2:
                    ticket_booking_service.bookTicket();
                    break;
                case 3:
                    createPackaingList.cratePackagingList(conn);
                    break;
                case 4:
                    book_accommodation.book_accomodation();
                    break;
                case 5:
                    budget_manager.manage_budget();
                    break;
                case 6:
                    view_trip_summary.view_trip_summary(conn, reg_login.u_idd);
                    break;
                case 7:
                    System.out.println("LOGOUT...");
                    return;
                default:
                    System.out.println("----------------------");
                    System.out.println("ENTER VALID CHOICE");
            }
        }
    }
}
