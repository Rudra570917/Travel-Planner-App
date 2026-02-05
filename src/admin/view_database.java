package admin;

import main.Database_Connection;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;
//class for viewing database
public class view_database {
    public static void view_database() throws Exception {
        Scanner sc=new Scanner(System.in);
        Connection conn= Database_Connection.Connect();
        int choice=0;
        do {
            System.out.println("----VIEW DETAILS----");
            System.out.println("1.BUS ROOTS");
            System.out.println("2.BUS BOOKING DATA");
            System.out.println("3.TRAIN ROOTS");
            System.out.println("4.TRAIN BOOKING DATA");
            System.out.println("5.FLIGHT ROOTS");
            System.out.println("6.FLIGHT BOOKING DATA");
            System.out.println("7.HOTEL DETAILS");
            System.out.println("8.HOTEL BOOKING DATA");
            System.out.println("9 HOLIDAY PLANS");
            System.out.println("10.PAYMENT HISTORY");
            System.out.println("11.EXIT");
            System.out.println("ENTER YOUR CHOICE");
            if(sc.hasNextInt())
            {
                choice=sc.nextInt();
            }

            Statement st=conn.createStatement();
            switch (choice)
            {
                case 1:
                    String sel1="SELECT * FROM bus_transport";
                    Statement st1=conn.createStatement();
                    ResultSet rs1=st1.executeQuery(sel1);
                    System.out.println("\nRootNo    BusNo     Source      Destination   Timing          Date        Price    TotalSeat     AvailSeat    BookedSeat");
                    System.out.println("----------------------------------------------------------------------------------------------------------------------------------------------------------------------");


                    while (rs1.next()) {
                        System.out.println(rs1.getInt(1)+"   "+rs1.getString(2)+"      "+rs1.getString(3)+"      "+rs1.getString(4)+"      "+
                                rs1.getString(5)+"       "+rs1.getString(6)+"      "+rs1.getDouble(7)+"       "+rs1.getInt(8)+"       "+
                                rs1.getInt(9)+"       "+rs1.getInt(10));
                    }

                    break;
                case 2:
                    String sel2="SELECT * FROM bus_booking_data";
                    ResultSet rs2=st.executeQuery(sel2);
                    System.out.println("TicketNo     Name       MobileNo       BusNo     SeatNo          Source        Destination        Timing         Date        Price");
                    System.out.println("----------------------------------------------------------------------------------------------------------------------------------");

                    while (rs2.next()) {
                        System.out.println(rs2.getInt(1)+"     "+rs2.getString(2)+"       "+rs2.getString(3)+"        "+rs2.getString(4)+"         "+
                                rs2.getString(5)+"          "+rs2.getString(6)+"         "+rs2.getString(7)+"          "+rs2.getString(8)
                                +"        "+rs2.getString(9)+"        "+rs2.getDouble(10));
                    }
                    break;
                case 3:
                    String sel3="SELECT * FROM train_transport";
                    ResultSet rs3=st.executeQuery(sel3);
                    System.out.println("ID     Train         Source         Destination       Time       Date       Price     Coaches" +
                            "     TotalSeat     AvailSeat     Booked");
                    System.out.println("-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");

                    while (rs3.next()) {
                        System.out.println(rs3.getInt(1)+"     "+rs3.getString(2)+"       "+rs3.getString(3)+"       "+rs3.getString(4)+"       "
                                +rs3.getString(5)+"       "+rs3.getString(6)+"      "+rs3.getDouble(7)+"     "+rs3.getInt(8)+"     "+
                                rs3.getInt(9)+"     "+rs3.getInt(10)+"     "+rs3.getInt(11));
                    }
                    break;
                case 4:
                    String sel4="SELECT * FROM train_booking_data";
                    ResultSet rs4=st.executeQuery(sel4);
                    System.out.println("ID         Name              Mobile              Train            Coach       Seat          Source           Destination          Time         Date      Price");
                    System.out.println("--------------------------------------------------------------------------------------------------------------------------------------------------------------");

                    while (rs4.next()) {
                        System.out.println(rs4.getInt(1)+"     "+rs4.getString(2)+"           "+rs4.getString(3)+"          "+rs4.getString(4)+"        "+
                                rs4.getString(5)+"            "+rs4.getString(6)+"           "+rs4.getString(7)+"           "+rs4.getString(8)+"           "+rs4.getString(9)
                                +"       "+ rs4.getString(10)+"      "+rs4.getDouble(11));
                    }
                    break;
                case 5:
                    String sel5="SELECT * FROM flight_transport";
                    ResultSet rs5=st.executeQuery(sel5);
                    System.out.println("RootNo      Airline          FlightNo          Source          Destination        " +
                            "Depart           Arrival          Duration          Boarding          Date       " +
                            "Price        Class     Total       Avail         Booked");
                    System.out.println("--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");

                    while (rs5.next()) {
                        System.out.println(rs5.getInt(1)+"     "+rs5.getString(2)+"          "+rs5.getString(3)+"          "+rs5.getString(4)
                                +"         "+rs5.getString(5)+"         "+rs5.getString(6)+"          "+rs5.getString(7)+"          "+rs5.getString(8)+"         "+rs5.getString(9)
                                +"         "+rs5.getString(10)+"         "+rs5.getDouble(11)+"          "+rs5.getString(12)+"          "+rs5.getInt(13)
                                +"      "+rs5.getInt(14)+"      "+rs5.getInt(15));
                    }
                    break;
                case 6:
                    String sel6="SELECT * FROM flight_booking_data";
                    ResultSet rs6=st.executeQuery(sel6);
                    System.out.println("TicketNo        PassportNo          Name           MobileNo          Airline          FlightNo        " +
                            "Class       SeatNo           Source            Destination         " +
                            "         Depart         Arrival         Duration        Boarding       Date        Price");
                    System.out.println("-------------------------------------------------------------------------------------------" +
                            "------------------------------------------------------------------------------------------------------------------------------------------");


                    while (rs6.next()) {
                        System.out.println(rs6.getInt(1)+"      "+rs6.getString(2)+"         "+rs6.getString(3)+"           "+rs6.getString(4)
                                +"        "+rs6.getString(5)+"          "+rs6.getString(6)+"        "+rs6.getString(7)+"          "+rs6.getString(8)+"          "+rs6.getString(9)
                                +"           "+rs6.getString(10)+"           "+rs6.getString(11)+"            "+rs6.getString(12)+"        "+rs6.getString(13)
                                +"       "+rs6.getString(14)+"         "+rs6.getString(15)+"         "+rs6.getDouble(16));
                    }

                    break;
                case 7:
                    String sel7="SELECT * FROM accommodation_hotels";
                    ResultSet rs7=st.executeQuery(sel7);
                    System.out.println("HotelID      Hotel_Name          Address                            Expense_FULLDAY        Expense_Night         TotalRooms       AvailRooms");
                    System.out.println("----------------------------------------------------------------------------------------------------------------------------------------------------");
                    while (rs7.next()) {
                        System.out.println(rs7.getInt(1)+"       "+rs7.getString(2)+"       "  +rs7.getString(3)+"                           "+rs7.getDouble(4)
                                +"       "+rs7.getDouble(5)+"         "+rs7.getInt(6)+"     "+rs7.getInt(7));
                    }
                    break;
                case 8:
                    String sel8="SELECT * FROM accommodation_booking_data";
                    ResultSet rs8=st.executeQuery(sel8);
                    System.out.println("Hotel_ID       Hotel_Name           Address                        Total_Expense        UID         UserName");
                    System.out.println("--------------------------------------------------------------------------------------------------------------");
                    while (rs8.next()) {
                        System.out.println(rs8.getInt(1)+"       "+rs8.getString(2)+"        "+rs8.getString(3)+"                      "+rs8.getDouble(4)
                                +"           "+rs8.getInt(5)+"          "+rs8.getString(6));
                    }
                    break;
                case 9:
                    String sel9="SELECT * FROM holiday_plans";
                    ResultSet rs9=st.executeQuery(sel9);
                    System.out.println("ID      PackageName            Source             Destination          Days     Nights     Expense          " +
                            "HotelName              HotelExp           Via         Date");
                    System.out.println("----------------------------------------------------------------------------------------------------------------------------");

                    while (rs9.next()) {
                        System.out.println(rs9.getInt(1)+"      "+rs9.getString(2)+"          "+rs9.getString(3)+"          "+rs9.getString(4)+"            "+rs9.getInt(5)+"              "+rs9.getInt(6)+"    "+
                                rs9.getDouble(7)+"           "+rs9.getString(8)+"           "+rs9.getDouble(9)+"          "+rs9.getString(10)+"            "+rs9.getString(11));
                    }
                case 10:
                    String sel10="SELECT * FROM payment_history";
                    ResultSet rs10=st.executeQuery(sel10);
                    System.out.println("ID      Method        Details      Timestamp");
                    System.out.println("--------------------------------------------");

                    while (rs10.next()) {
                        System.out.println(rs10.getInt(1)+"      "+rs10.getString(2)+"         "+rs10.getString(3)+"          "+rs10.getString(4));
                    }
                    break;
                case 11:
                    System.out.println("EXTING...");
                    break;
                default:
                    System.out.println("ENTER VALID CHOICE");
                    break;

            }

        }while (choice!=11);
    }
}
