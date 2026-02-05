package admin;
import main.valid_input;

import java.sql.Connection;
import java.util.Scanner;
//class for admin to add trips,view routes,holiday plans and payment history
public class admin_menu
{
       public static void admin_menu(Scanner sc, Connection conn) throws Exception{
        int choice=0;
        do {
            System.out.println("1.ADD TRIPS(ROUTS)");
            System.out.println("2.VIEW ROUTS,HOLIDAY PLAN AND PAYMENT HISTORY ");
            System.out.println("3.EXIT");
            System.out.println("ENTER YOUR CHOICE");
            choice=valid_input.getIntInput(sc);

            switch (choice)
            {
                case 1:
                    add_trips.add_trip_details();
                    break;
                case 2:
                    view_database.view_database();
                    break;
                case 3:
                    System.out.println("EXTING...");
                    break;
                default:
                    System.out.println("ENTER VALID CHOICE(1 TO 3)");
                    break;
            }
        }while (choice!=3);
    }
}