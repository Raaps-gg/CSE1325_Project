import java.util.Scanner;
import java.util.Scanner;
import java.io.FileWriter;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        Library library = new Library();
        
        System.out.println("=== Library Management System ===");
        
        while (true) {
            System.out.println("\n1. Login as Student");
            System.out.println("2. Login as Admin");
            System.out.println("3. Exit");
            System.out.print("Choose option: ");
            
            int choice = input.nextInt();
            input.nextLine(); // consume newline
            
            switch (choice) {
                case 1:
                    loginStudent(library, input);
                    break;
                case 2:
                    loginAdmin(library, input);
                    break;
                case 3:
                    System.out.println("Thank you for using the Library Management System!");
                    input.close();
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
    
    private static void loginStudent(Library library, Scanner input) {
        System.out.print("Enter your student ID: ");
        String id = input.nextLine();
        System.out.print("Enter your name: ");
        String name = input.nextLine();
        
        Student student = new Student(id, name);
        student.displayMenu(library, input);
    }
    
    private static void loginAdmin(Library library, Scanner input) {
        System.out.print("Enter admin ID: ");
        String id = input.nextLine();
        System.out.print("Enter admin password: ");
        String password = input.nextLine();
    
        if (FileUtils.verifyAdmin(id, password)) {
            String name = FileUtils.getAdminName(id);
            Admin admin = new Admin(id, name);
            admin.displayMenu(library, input);
        } else {
          System.out.println("Invalid admin credentials!");
        }
    }
}