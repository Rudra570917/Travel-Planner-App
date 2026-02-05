package main;

import java.sql.*;
import java.util.*;

//Main class
public class main {
    public static void main(String[] args) throws Exception{
        try {
            Connection conn = Database_Connection.Connect();
        }
        catch (SQLException e)
        {
            System.out.println("CONNECTION FAILURE!");
            return;
        }

        Scanner sc=new Scanner(System.in);

        //login/registration menu
        System.out.println("-------------------------");
        System.out.println("WELCOME TO TRAVEL PLANNER");
        System.out.println("-------------------------");
        int choice = 0;
        do {
            System.out.println("1.REGISTRATION");
            System.out.println("2.LOGIN");
            System.out.println("3.EXIT");
            System.out.println("ENTER YOUR CHOICE");
            System.out.print("=>");
            choice=valid_input.getIntInput(sc);

            switch (choice) {
                case 1:
                    reg_login.registration();
                    break;
                case 2:
                    reg_login.login();
                    break;
                case 3:
                    System.out.println("EXITING....");
                    break;
                default:
                    System.out.println("ENTER VALID CHOICE FROM(1-3) ");
                    break;
            }
        } while (choice!=3);
    }
}








