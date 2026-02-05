package user;



import main.valid_input;

import java.sql.Connection;
import java.util.Scanner;

//class for create packaging list
public class createPackaingList {
    //method for packagingList
    public static void cratePackagingList(Connection conn) throws Exception
    {
        linked_list d=new linked_list();
        int choice=0;
        do {

            //interface for create packing list
            Scanner sc = new Scanner(System.in);
            System.out.println("1.ADD ITEM");
            System.out.println("2.DELETE ITEM");
            System.out.println("3.DISPLAY LIST");
            System.out.println("4.SAVE LIST");
            System.out.println("5.EXIT");
            System.out.println("ENTER YOUR CHOICE");
            if (sc.hasNextInt()) {
                choice = sc.nextInt();
            }
            sc.nextLine();

            String list = "";
            int i = 0;
            switch (choice) {
                case 1:
                    String item = "";
                    while (!item.equalsIgnoreCase("DONE")) {
                        i++;
                        System.out.println("ENTER ITEM NAME OR IF DONE ENTER DONE OR KEEP BLANK FOR EXIT");
                        item = valid_input.getValidString();
                        if(item==null)
                        {
                            return;
                        }
                        list += i + " " + item + ",";
                        d.insert_last(item);
                    }
                    break;

                case 2:
                    System.out.println("ENTER ITEM NAME TO DELETE OR KEEP BLANK FOR EXIT");
                    String itemname = valid_input.getValidString();
                    if(itemname==null)
                    {
                        return;
                    }
                    d.delete_Value(itemname);
                    break;

                case 3:
                    d.display();
                    break;
                case 4:
                    d.save_List();
                    break;
                case 5:
                    System.out.println("EXTING...");
                    break;

                default:
                    System.out.println("ENTER VALID CHOICE");
                    break;
            }
        }while (choice!=5);
    }

}
