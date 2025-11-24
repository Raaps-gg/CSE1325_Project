import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        Library library = new Library();
        
        System.out.println("=== Library Management System ===");
        
       while (true) {
    System.out.println("\n1. Login as Student");
    System.out.println("2. Login as Admin");
    System.out.println("3. Sign up as Student");
    System.out.println("4. Sign up as Admin");
    System.out.println("5. Exit");
    System.out.print("Choose option: ");

        if (!input.hasNextInt()) {
            System.out.println("Invalid input. Please enter an option from 1 to 5.");
            input.nextLine();
            continue;
        }

        int choice = input.nextInt();
        input.nextLine();

        switch (choice) {
            case 1:
                loginStudent(library, input);
                break;
            case 2:
                loginAdmin(library, input);
                break;
            case 3:
                signupStudent(input);
                break;
            case 4:
                signupAdmin(input);
                break;
            case 5:
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
        System.out.print("Enter your password: ");
        String password = input.nextLine();
        
        if (FileUtils.verifyStudent(id, password)) {
            String name = FileUtils.getStudentName(id);
            Student student = new Student(id, name);
            student.displayMenu(library, input);
        } else {
            System.out.println("Invalid student credentials!");
        }
    }
    
    private static void loginAdmin(Library library, Scanner input) {
    String id;

    while (true) {
        System.out.print("Enter admin ID: ");
        id = input.nextLine().trim();

        if (id.isEmpty()) {
            System.out.println("ID cannot be empty. Try again.");
        } else if (!id.matches("\\d+")) {
            System.out.println("Invalid ID. Please enter numbers only.");
        } else {
            break;
        }
    }

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

    
    private static void signupStudent(Scanner input) {
        System.out.println("\n=== Student Sign Up ===");
        System.out.print("Enter your name: ");
        String name = input.nextLine();
        System.out.print("Create a student ID: ");
        String id = input.nextLine();
        System.out.print("Create a password: ");
        String password = input.nextLine();
        
        if (FileUtils.registerStudent(id, name, password)) {
            System.out.println("Student account created successfully!");
            System.out.println("You can now login with ID: " + id);
        } else {
            System.out.println("Failed to create account. ID might already exist.");
        }
    }
    
    private static void signupAdmin(Scanner input) {
        System.out.println("\n=== Admin Sign Up ===");
        System.out.print("Enter admin registration code: ");
        String regCode = input.nextLine();
        
        // Simple security measure - only allow admin signup with a code
        if (!regCode.equals("ADMIN2024")) {
            System.out.println("Invalid registration code. Contact system administrator.");
            return;
        }
        
        System.out.print("Enter your name: ");
        String name = input.nextLine();
        System.out.print("Create an admin ID: ");
        String id = input.nextLine();
        System.out.print("Create a password: ");
        String password = input.nextLine();
        
        if (FileUtils.registerAdmin(id, name, password)) {
            System.out.println("Admin account created successfully!");
            System.out.println("You can now login with ID: " + id);
        } else {
            System.out.println("Failed to create account. ID might already exist.");
        }
    }
}