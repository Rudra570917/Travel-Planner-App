package main;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Base64;
import java.util.Scanner;

public class valid_input {

    //method for get valid integer input
    public static int getIntInput(Scanner sc) {
        while (true) {
            try {
                return Integer.parseInt(sc.next());
            } catch (NumberFormatException e) {
                System.out.print("--> Invalid number. Enter again: ");
            }
            finally {
                sc.nextLine();
            }
        }
    }


    //method for get valid mobile number
    public static String getValidMo_number() {
        Scanner sc = new Scanner(System.in);
        String mo_no = sc.nextLine();
        while (true) {
            try {
                if (mo_no.isEmpty()) {
                    return null;
                }
                if ((!mo_no.startsWith("9") && !mo_no.startsWith("6")) || mo_no.length() != 10) {
                    System.out.println("ENTER VALID MOBILE NUMBER(STARTS FROM 9 OR 6 AND 10 DIGITS");
                    mo_no = getValidMo_number();
                }
                return mo_no;
            } catch (Exception e) {
                System.out.println("ENTER VALID MOBILE NUMBER");
            }
        }
    }

    //method for get valid date
    public static String getValidDate() {
        Scanner sc = new Scanner(System.in);
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String input;
        while (true) {
            System.out.print("Enter date (yyyy-MM-dd): ");
            input = sc.nextLine().trim();
            try {
                LocalDate.parse(input, dateFormatter);
                return input; // valid
            } catch (DateTimeParseException e) {
                System.out.println("Invalid date! Please enter again.");
            }
        }
    }

    //method for get valid time
    public static String getValidTime() {
        Scanner sc = new Scanner(System.in);
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm");
        String input;
        while (true) {
            System.out.print("Enter time (HH:mm): ");
            input = sc.nextLine().trim();
            try {
                LocalTime.parse(input, timeFormatter);
                return input; //
            } catch (DateTimeParseException e) {
                System.out.println("Invalid time! Please enter again.");
            }
        }
    }



    //method for get valid string
    public static String getValidString() {
        Scanner sc = new Scanner(System.in);
        String name;

        while (true) {
            name = sc.nextLine().trim();

            if (name.isEmpty()) {
                return null;
            }

            // Regex: letters + numbers + space allowed
            if (name.matches("^[a-zA-Z0-9 ]+$")) {
                return name;
            } else {
                System.out.println("ENTER VALID DATA (Only letters, numbers, and spaces allowed):");
            }
        }
    }

    //method for get valid double
    public static double getValidDouble() {
        Scanner sc = new Scanner(System.in);

        while (true) {
            String input = sc.nextLine().trim();

            if (input.isEmpty()) {
                System.out.println("Input cannot be empty. Please enter a number:");
                continue;
            }

            try {
                return Double.parseDouble(input); //  convert string to double
            } catch (NumberFormatException e) {
                System.out.println("ENTER VALID NUMBER (only digits and decimal point allowed):");
            }
            finally {
                sc.nextLine();
            }
        }
    }


        // Generate salt
        public static String getSalt() throws NoSuchAlgorithmException {
        SecureRandom sr = SecureRandom.getInstanceStrong();
        byte[] salt = new byte[16];
        sr.nextBytes(salt);
        return Base64.getEncoder().encodeToString(salt);
    }

        // Hash password with SHA-256 and salt
        public static String hashPassword(String password, String salt) throws Exception {
        MessageDigest md = MessageDigest.getInstance("SHA-256");
        md.update(salt.getBytes()); // add salt
        byte[] hashedPassword = md.digest(password.getBytes());
        return Base64.getEncoder().encodeToString(hashedPassword);
    }

}
