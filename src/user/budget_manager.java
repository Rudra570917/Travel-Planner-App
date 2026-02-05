package user;

import main.valid_input;

import java.util.Scanner;

//class for budget manage
public class budget_manager {

    //method for budget manager
    public static void manage_budget() {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter your total budget: ");
        double budget = sc.nextDouble();
        double remaining = budget;

        while (true) {
            System.out.print("Enter expense name (or type 'done' to finish): ");
            String expenseName=null;
            while (true) {
                try {
                    expenseName = sc.next();
                    break;
                } catch (Exception e) {
                    System.out.println("ENTER VALID EXPENSE NAME");
                }
            }
            if (expenseName.equalsIgnoreCase("done")) {
                break;
            }

            System.out.print("Enter amount for " + expenseName + ": ");
            double amount = valid_input.getValidDouble();

            if (amount > remaining) {
                System.out.println("=> Not enough budget! Remaining = " + remaining);
            } else {
                remaining -= amount;
                System.out.println("=> Expense added! Remaining budget = " + remaining);
            }
        }

        //display budget summary
        System.out.println("\n--- Budget Summary ---");
        System.out.println("Total Budget : " + budget);
        System.out.println("Remaining    : " + remaining);
        System.out.println("Spent        : " + (budget - remaining));
    }

}
