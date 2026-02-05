package main;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

//class for database connection
public class Database_Connection {

     //method for database connection
     public static Connection Connect() throws SQLException {

          //database url
          String db_url = "jdbc:mysql://::1:3306/travel_dbms";
          //database user name
          String db_user = "root12";
          //database password
          String pass = "12345M";

          //call driver for connection
          return DriverManager.getConnection(db_url,db_user,pass);
     }
}