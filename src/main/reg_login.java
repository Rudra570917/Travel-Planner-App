package main;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Scanner;
import user.user_menu;
import admin.admin_menu;


//class for reg_login methos
public class reg_login {

    //method for registration
    public static void registration() throws Exception {
        Scanner sc=new Scanner(System.in);
        Connection conn= Database_Connection.Connect();

        //REGISTRATION INTERFACE
        System.out.println("---------------------");
        System.out.println("|  NEW REGISTRATION |");
        System.out.println("---------------------");
        System.out.println("ENTER YOUR NAME ELSE KEEP BLANK FOR EXIT");
        String name =valid_input.getValidString();
        if (name == null) {
            return;
        }
        System.out.println("ENTER YOUR MOBILE NUMBER OR KEEP BLANK FOR EXIT");
        String mo_no =valid_input.getValidMo_number();
        if (mo_no == null) {
            return;
        }
        System.out.println("CREATE PASSWORD OF 8 DIGIT/CHAR");
        String u_pass = sc.nextLine();
        while (u_pass.length() != 8 || u_pass.isEmpty()) {
            System.out.println("PASSWORD MUST BE 8 DIGIT/CHAR OR NOT NULL");
            u_pass = sc.nextLine();
        }


        //Insert User Data Into Database
        String q2 = "INSERT INTO user_data(name,mobile_no,pass,role) values(?,?,?,?)";
        PreparedStatement pst = conn.prepareStatement(q2);
        pst.setString(1, name);
        pst.setString(2, mo_no);
        pst.setString(3, u_pass);
        pst.setString(4,"USER");
        int n = pst.executeUpdate();
        String qu = "select user_id from user_data where mobile_no=?";
        PreparedStatement pst1 = conn.prepareStatement(qu);
        pst1.setString(1, mo_no);
        ResultSet rst = pst1.executeQuery();
        String u_id = null;
        if (rst.next()) {
            u_id = rst.getString(1);
        }
        if (n > 0) {
            System.out.println("REGISTERED SUCCESSFULLY");
            System.out.println("YOUR USER ID :" + u_id);
        }

    }

    //method for login
    public static String u_idd = null;
    public  static String u_name = null;
    public static void login() throws Exception {

        Scanner sc=new Scanner(System.in);
        Connection conn= Database_Connection.Connect();

        //login interface
        System.out.println("----------------");
        System.out.println("|     LOGIN     |");
        System.out.println("----------------");
        System.out.println("ENTER MOBILE NO ");
        String u_mo_no =valid_input.getValidMo_number();
        String check1 = "SELECT user_id from user_data where mobile_no=?";
        PreparedStatement pstc = conn.prepareStatement(check1);
        pstc.setString(1, u_mo_no);
        ResultSet rs1 = pstc.executeQuery();
        if (rs1.next()) {
            u_idd = rs1.getString(1);
        }
        if (u_idd == null) {
            System.out.println("INVALID MOBILE NUMBER TRY AGAIN");
            return;
        } else {
            System.out.println("ENTER YOUR PASSWORD (must be at least 8 characters):");
            String passw = null;

            //loop for create password
            while (true) {
                try {
                    passw = sc.nextLine().trim();  // take input

                    if (passw.isEmpty()) {
                        throw new IllegalArgumentException("Password cannot be empty.");
                    }

                    if (passw.length() < 8) {
                        throw new IllegalArgumentException("Password must be at least 8 characters long.");
                    }

                    break;

                } catch (IllegalArgumentException e) {
                    // Validation error → ask again
                    System.out.println("ERROR: " + e.getMessage());
                    System.out.print("Please re-enter your password: ");
                } catch (Exception e) {
                    // Any unexpected issue → ask again
                    System.out.println("UNEXPECTED ERROR: " + e.getMessage());
                    System.out.print("Please re-enter your password: ");
                }
            }


            String check2 = "SELECT pass FROM USER_DATA WHERE MOBILE_No=?";
            PreparedStatement pstc1 = conn.prepareStatement(check2);
            pstc1.setString(1, u_mo_no);
            ResultSet rs2 = pstc1.executeQuery();
            String pw = null;
            if (rs2.next()) {
                pw = rs2.getString(1);
            }

            //loop for password verification
            Boolean entry = false;
            if (passw.equals(pw)) {
                entry = true;
            } else if (!passw.equals(pw)) {
                int attemps = 2;
                while (attemps > 0) {
                    System.out.println("ENTER CORRECT PASSWORD");
                    passw = sc.nextLine();
                    attemps--;
                    if (passw.equals(pw)) {
                        entry = true;
                        break;
                    }
                }
                if (attemps == 0) {
                    System.out.println("TOO MANY WRONG ATTEMPTS TRY AGAIN");
                }
            }


            //conform login
            String role=null;
            if (entry) {
                String quname = "SELECT name,role FROM user_data where mobile_no=?";
                PreparedStatement psfn = conn.prepareStatement(quname);
                psfn.setString(1, u_mo_no);
                ResultSet rsn = psfn.executeQuery();
                if (rsn.next()) {
                    u_name=rsn.getString("name");
                    role=rsn.getString("role");
                }
                if(role.equalsIgnoreCase("ADMIN"))
                {
                    admin_menu.admin_menu(sc,conn);
                }else
                {
                    user_menu.user_menu(sc,conn);
                }


            }
        }
    }
}