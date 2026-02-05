package user;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Scanner;

public abstract class pay {
    protected double amount;
    public static double amt_after_dis;
    protected String method;
    protected String details;
    protected static int discount;
    public pay(double amount) {
        this.amount = amount;
    }

    // Abstract method (implemented by child classes)
    public abstract boolean processPayment(Connection con) throws Exception;

    // Save payment history
    protected void savePaymentHistory(Connection con) throws Exception {
        String q2 = "INSERT INTO payment_history (method, details, timestamp,amount,discount,amt_after_dis) VALUES (?, ?, CURRENT_TIMESTAMP,?,?,?)";
        try (PreparedStatement pstmt = con.prepareStatement(q2)) {
            pstmt.setString(1, method);
            pstmt.setString(2, details);
            pstmt.setDouble(3,amount);
            pstmt.setInt(4,discount);
            pstmt.setDouble(5,amt_after_dis);
            pstmt.executeUpdate();
        }
    }

    //Deduct from user and add to admin
    protected boolean updateBalances(Connection con, int accNo, double balance) throws Exception {
        if (balance < amt_after_dis) {
            System.out.println("Insufficient balance!");
            return false;
        }

        // Deduct from user
        String q = "UPDATE bank_account SET balance=? WHERE ac_no=?";
        try (PreparedStatement psq = con.prepareStatement(q)) {
            psq.setDouble(1, balance - amt_after_dis);
            psq.setInt(2, accNo);
            psq.executeUpdate();
        }

        // Add to admin account (id=2 for example)
        String adminQ = "UPDATE bank_account SET balance = balance + ? WHERE ac_no = 2";
        try (PreparedStatement psa = con.prepareStatement(adminQ)) {
            psa.setDouble(1, amt_after_dis);
            psa.executeUpdate();
        }

        return true;
    }
}
//class for Debit Card Payment
class DebitCardPayment extends pay {
    private String cardNumber;
    private int enteredPin;
    private String coupon;

    public DebitCardPayment(double amount, String cardNumber, int enteredPin, String coupon) {
        super(amount);
        this.cardNumber = cardNumber;
        this.enteredPin = enteredPin;
        this.coupon = coupon;
        this.method = "DEBIT_CARD";
        this.discount=0;
    }

    @Override
    public boolean processPayment(Connection con) throws Exception {
        con.setAutoCommit(false); //start transaction
        try {
            // Apply coupon
            if (coupon != null && coupon.equalsIgnoreCase("GET20%OFFDEBIT")) {
                discount=20;
                amt_after_dis =amount-(amount * 0.20);
                System.out.println("20% discount applied! New amount: " + amt_after_dis);
            }
            details = cardNumber;

            // Validate card
            String sel = "SELECT ac_no, balance, transaction_pin FROM bank_account WHERE card_number=?";
            PreparedStatement pst = con.prepareStatement(sel);
            pst.setLong(1, Long.parseLong(cardNumber));
            ResultSet rs = pst.executeQuery();

            if (!rs.next()) {
                System.out.println("Invalid card!");
                con.rollback();
                return false;
            }

            int accNo = rs.getInt("ac_no");
            double balance = rs.getDouble("balance");
            int pin = rs.getInt("transaction_pin");

            if (enteredPin != pin) {
                System.out.println("Invalid PIN!");
                con.rollback();
                return false;
            }

            // Deduct & add
            if (!updateBalances(con, accNo, balance)) {
                con.rollback();
                return false;
            }

            savePaymentHistory(con);

            con.commit(); //commit only if all succeed
            System.out.println("Payment successful via Debit Card!");
            return true;

        } catch (Exception e) {
            con.rollback(); //rollback if any error
            System.out.println("Payment failed! " + e.getMessage());
            return false;
        } finally {
            con.setAutoCommit(true); // reset default
        }
    }
}
//class for Credit Card Payment
class CreditCardPayment extends pay {
    private String cardNumber;
    private int enteredPin;
    private String coupon;

    public CreditCardPayment(double amount, String cardNumber, int enteredPin, String coupon) {
        super(amount);
        this.cardNumber = cardNumber;
        this.enteredPin = enteredPin;
        this.coupon = coupon;
        this.method = "CREDIT_CARD";
        this.discount= 0;
    }

    @Override
    public boolean processPayment(Connection con) throws Exception {
        con.setAutoCommit(false);
        try {
            if (coupon != null && coupon.equalsIgnoreCase("GET5%OFFCREDIT")) {
                this.discount=5;
                amt_after_dis = amount * 0.95;
                System.out.println("5% discount applied! New amount: " + amount);
            }
            details = cardNumber;
            amt_after_dis=amount;

            String sel = "SELECT ac_no, balance, transaction_pin FROM bank_account WHERE card_number=?";
            PreparedStatement pst = con.prepareStatement(sel);
            pst.setLong(1, Long.parseLong(cardNumber));
            ResultSet rs = pst.executeQuery();

            if (!rs.next()) {
                System.out.println("Invalid Credit Card!");
                con.rollback();
                return false;
            }

            int accNo = rs.getInt("ac_no");
            double balance = rs.getDouble("balance");
            int pin = rs.getInt("transaction_pin");

            if (enteredPin != pin) {
                System.out.println("Invalid PIN!");
                con.rollback();
                return false;
            }

            if (!updateBalances(con, accNo, balance)) {
                con.rollback();
                return false;
            }

            savePaymentHistory(con);

            con.commit();
            System.out.println("Payment successful via Credit Card!");
            return true;

        } catch (Exception e) {
            con.rollback();
            System.out.println("Payment failed! " + e.getMessage());
            return false;
        } finally {
            con.setAutoCommit(true);
        }
    }
}
//class for UPI Payment Option
class UpiPayment extends pay {
    private String upiId;
    private int enteredPin;
    private String coupon;

    public UpiPayment(double amount, String upiId, int enteredPin, String coupon) {
        super(amount);
        this.upiId = upiId;
        this.enteredPin = enteredPin;
        this.coupon = coupon;
        this.method = "UPI";
        this.discount=0;
    }

    @Override
    public boolean processPayment(Connection con) throws Exception {
        con.setAutoCommit(false);
        try {
            if (coupon != null && coupon.equalsIgnoreCase("GET15%OFFUPI")) {
                this.discount=15;
                amt_after_dis = amount * 0.85;
                System.out.println("15% discount applied! New amount: " + amount);
            }
            details = upiId;
            amt_after_dis=amount;
            String sel = "SELECT ac_no, balance, transaction_pin FROM bank_account WHERE upi_id=?";
            PreparedStatement pst = con.prepareStatement(sel);
            pst.setString(1, upiId);
            ResultSet rs = pst.executeQuery();

            if (!rs.next()) {
                System.out.println("Invalid UPI ID!");
                con.rollback();
                return false;
            }

            int accNo = rs.getInt("ac_no");
            double balance = rs.getDouble("balance");
            int pin = rs.getInt("transaction_pin");

            if (enteredPin != pin) {
                System.out.println("Invalid PIN!");
                con.rollback();
                return false;
            }

            if (!updateBalances(con, accNo, balance)) {
                con.rollback();
                return false;
            }

            savePaymentHistory(con);

            con.commit();
            System.out.println("Payment successful via UPI!");
            return true;

        } catch (Exception e) {
            con.rollback();
            System.out.println("Payment failed! " + e.getMessage());
            return false;
        } finally {
            con.setAutoCommit(true);
        }
    }
}

//Class for payment handler
class PaymentHandler {

    public static boolean makePayment(Connection con, double amount) {
        Scanner sc = new Scanner(System.in);
        boolean success = false;

        while (!success) {
            try {
                System.out.println("PAYABLE AMOUNT :"+amount);
                System.out.println("\n=== Select Payment Method ===");
                System.out.println("1. Debit Card");
                System.out.println("2. Credit Card");
                System.out.println("3. UPI");
                System.out.println("4. EXIT");
                System.out.print("Enter choice: ");

                int choice = Integer.parseInt(sc.nextLine().trim());

                switch (choice) {
                    case 1: {

                        System.out.print("Enter Coupon (or press Enter to skip): ");
                        String coupon = sc.nextLine().trim();
                        if (coupon.isEmpty()) coupon = null;
                        System.out.println("PAYABLE AMOUNT AFTER DISCOUNT :"+ pay.amt_after_dis);

                        System.out.print("Enter Debit Card Number: ");
                        String cardNo = sc.nextLine().trim();

                        System.out.print("Enter Transaction PIN: ");
                        int pin = Integer.parseInt(sc.nextLine().trim());


                        DebitCardPayment debit = new DebitCardPayment(amount, cardNo, pin,coupon);
                        success = debit.processPayment(con);
                        break;
                    }

                    case 2: {
                        System.out.print("Enter Coupon (or press Enter to skip): ");
                        String coupon = sc.nextLine().trim();
                        if (coupon.isEmpty()) coupon = null;

                        System.out.print("Enter Credit Card Number: ");
                        String cardNo = sc.nextLine().trim();

                        System.out.print("Enter Transaction PIN: ");
                        int pin = Integer.parseInt(sc.nextLine().trim());

                        CreditCardPayment credit = new CreditCardPayment(amount, cardNo, pin, coupon);
                        success = credit.processPayment(con);
                        break;
                    }

                    case 3: {
                        System.out.print("Enter UPI ID: ");
                        String upiId = sc.nextLine().trim();

                        System.out.print("Enter Transaction PIN: ");
                        int pin = Integer.parseInt(sc.nextLine().trim());

                        System.out.print("Enter Coupon (or press Enter to skip): ");
                        String coupon = sc.nextLine().trim();
                        if (coupon.isEmpty()) coupon = null;

                        UpiPayment upi = new UpiPayment(amount, upiId, pin, coupon);
                        success = upi.processPayment(con);
                        break;
                    }
                    case 4:
                        System.out.println("EXITING...");
                        return false;
                    default:
                        System.out.println("Invalid choice! Please select 1, 2, or 3.");
                }

                if (!success) {
                    System.out.println("\n⚠ Payment failed. Try again!\n");
                }

            } catch (NumberFormatException e) {
                System.out.println("Invalid number input! Please try again.");
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
        return success;
    }
}
