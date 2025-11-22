import java.util.Scanner;

public class Student extends User {
    
    public Student(String id, String name) {
        super(id, name);
    }

    @Override
    public void displayMenu(Library library, Scanner input) {
        int choice = 0;
        System.out.println("Welcome " + getName());
        
        while(choice != 4) {
            System.out.println("\nWhat would you like to do:");
            System.out.println("1. Display available books");
            System.out.println("2. Checkout a book");
            System.out.println("3. Return a book");
            System.out.println("4. Exit");
            
            choice = input.nextInt();
            input.nextLine();
            
            switch(choice) {
                case 1:
                    library.displayAvailableBooks();
                    break;
                case 2:
                    System.out.println("Enter book to checkout (title):");
                    String checkoutTitle = input.nextLine();
                    System.out.println("Enter due date (e.g. 2025-12-01):");
                    String dueDate = input.nextLine();
                    library.checkoutBook(checkoutTitle, getName(), dueDate);
                    break;
                case 3:
                    System.out.println("Enter book to return (title):");
                    String returnTitle = input.nextLine();
                    library.returnBook(returnTitle);
                    break;
                case 4:
                    System.out.println("Exiting...");
                    break;
                default:
                    System.out.println("Invalid choice.");
            }
        }
    }
}