package user;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
//linked list data structure
public class linked_list {

    //constructor
    private static class Node {
        String data;
        Node next;
        Node(String d) {
            this.data = d;
            this.next = null;
        }
    }

    private Node first = null;

    //method for Insert at end
    public void insert_last(String item) {
        Node n = new Node(item);
        if (first == null) {
            first = n;
            return;
        }
        Node temp = first;
        while (temp.next != null) temp = temp.next;
        temp.next = n;
    }

    // Delete all occurrences of a value (case-insensitive, ignores spaces)
    public boolean delete_Value(String value) {
        if (first == null) {
            System.out.println("LINKED LIST IS EMPTY");
            return false;
        }

        String target = value.trim().toLowerCase();
        boolean deleted = false;

        // Remove all matching nodes from the head
        while (first != null && first.data.trim().toLowerCase().equals(target)) {
            first = first.next;
            deleted = true;
        }

        // Traverse and remove matches from the rest of the list
        Node prev = first;
        if (prev != null) {
            Node curr = first.next;
            while (curr != null) {
                if (curr.data.trim().toLowerCase().equals(target)) {
                    prev.next = curr.next;  // unlink current
                    deleted = true;
                    curr = prev.next;       // move ahead without advancing prev
                } else {
                    prev = curr;
                    curr = curr.next;
                }
            }
        }

        if (deleted) {
            System.out.println("DELETED: " + value);
        } else {
            System.out.println("VALUE NOT FOUND: " + value);
        }

        return deleted;
    }

    //method for display list
    public void display() {
        if (first == null) {
            System.out.println("PACKAGING LIST IS EMPTY");
            return;
        }
        Node temp = first;
        int i = 0;
        System.out.println("     PACKAGING LIST   ");
        System.out.println("     ----------------");
        while (temp.next != null) {
            i++;
            System.out.println(i + " : " + temp.data);
            temp = temp.next;
        }
    }

    //method for save list
    public void save_List() {
        if (first == null) {
            System.out.println("LINKED LIST IS EMPTY");
            return;
        }
        try (BufferedWriter bw = new BufferedWriter(new FileWriter("packingList.txt"))) {
            Node temp = first;
            int i = 0;
            bw.write("     PACKAGING LIST   ");
            bw.newLine();
            bw.write("     ----------------");
            bw.newLine();
            while (temp.next != null) {
                i++;
                bw.write(i + " : " + temp.data);
                bw.newLine();
                temp = temp.next;
            }
            System.out.println("SAVED");
        } catch (IOException e) {
            System.out.println("ERROR WHILE SAVING: " + e.getMessage());
        }
    }
}

