package user;
import main.valid_input;
import main.Database_Connection;

import java.sql.Connection;
import java.util.Scanner;

public class ticket_booking_service {
    //method for ticket booking
    static int choicebook = 0;

    public static void bookTicket() throws Exception {
        Connection conn= Database_Connection.Connect();
        Scanner sc=new Scanner(System.in);
        do {
            System.out.println("---BOOK YOUR TICKETS");
            System.out.println("1.BUS");
            System.out.println("2.TRAIN");
            System.out.println("3.FLIGHT");
            System.out.println("4.EXIT");
            System.out.println("ENTER YOUR CHOICE");
            choicebook=valid_input.getIntInput(sc);

            switch (choicebook) {
                case 1:
                    //Get source & destination
                    System.out.println("\nENTER SOURCE OR KEEP BLANK FOR EXIT:");
                    String source = valid_input.getValidString();
                    if(source==null)
                    {
                        return;
                    }
                    System.out.println("ENTER DESTINATION OR KEEP BLANK FOR EXIT:");
                    String dest =valid_input.getValidString();
                    if(dest==null)
                    {
                        return;
                    }
                    bus_ticket_booking.busbooking(conn, sc, source, dest);
                    break;
                case 2:
                    //Get source & destination
                    System.out.println("\nENTER SOURCE OR KEEP BLANK FOR EXIT:");
                    String source1 =valid_input.getValidString();
                    if(source1==null)
                    {
                        return;
                    }
                    System.out.println("ENTER DESTINATION OR KEEP BLANK FOR EXIT:");
                    String dest1 =valid_input.getValidString();
                    if(dest1==null)
                    {
                        return;
                    }
                    train_ticket_booking.booktrainTicket(conn, sc, source1, dest1);
                    break;
                case 3:
                    //Get source & destination
                    System.out.println("\nENTER SOURCE OR KEEP BLANK FOR EXIT:");
                    String source2 =valid_input.getValidString();
                    if(source2==null)
                    {
                        return;
                    }
                    System.out.println("ENTER DESTINATION OR KEEP BLANK FOR EXIT:");
                    String dest2 =valid_input.getValidString();
                    if(dest2==null)
                    {
                        return;
                    }
                    plane_ticket_booking.bookflightTicket(conn, sc, source2, dest2);
                    break;
                case 4:
                    System.out.println("EXTING...");
                    break;
                default:
                    System.out.println("ENTER VALID CHOICE");
                    break;
            }
        } while (choicebook != 4);
    }
}