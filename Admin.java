import java.util.Scanner;

public class Admin extends User {
    
    public Admin(String id, String name) {
        super(id, name);
    }

    @Override
    public void displayMenu(Library library, Scanner input) {
        int choice = 0;
        System.out.println("Welcome Admin " + getName());
        
        while(choice != 6) {
            System.out.println("\nWhat would you like to do:");
            System.out.println("1. Add a book");
            System.out.println("2. Check due date of checked out book");
            System.out.println("3. Remove book from availability");
            System.out.println("4. Display available books");
            System.out.println("5. Display checked out books");
            System.out.println("6. Exit");
            
            choice = input.nextInt();
            input.nextLine();
            
            switch(choice) {
                case 1:
                    System.out.println("Enter book title:");
                    String title = input.nextLine();
                    System.out.println("Enter author:");
                    String author = input.nextLine();
                    System.out.println("Enter the year written:");
                    String year = input.nextLine();
                    
                    String id = "B" + System.currentTimeMillis();
                    library.addBook(new Book(id, title, author, year, true));
                    System.out.println("Book added successfully.");
                    break;
                case 2:
                    FileUtils.displayTransactions();
                    break;
                case 3:
                    System.out.println("Enter book to remove (title):");
                    String removeTitle = input.nextLine();
                    library.removeBook(removeTitle);
                    break;
                case 4:
                    library.displayAvailableBooks();
                    break;
                case 5:
                    FileUtils.displayCheckedOutBooks();
                    break;
                case 6:
                    System.out.println("Exiting...");
                    break;
                default:
                    System.out.println("Invalid choice.");
            }
        }
    }
}