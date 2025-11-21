import java.util.Scanner;
import java.util.Scanner;
import java.io.FileWriter;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;//forgot they have to be out of the public class

public class Main {

	static String currentStudent = ""; // remembers student name

public static void DisplayAvailable() {
    String fileName = "books.txt";

    System.out.println("Available Books:");
    System.out.println("---------------------------------");

    try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
        String line;

        while ((line = br.readLine()) != null) {
            String[] parts = line.split(",");

            if (parts.length == 5) {
                String id = parts[0].trim();
                String title = parts[1].trim();
                String author = parts[2].trim();
                boolean available = Boolean.parseBoolean(parts[3].trim());

                if (available) {
                    System.out.println(id + " | " + title + " by " + author);
                }
            }
        }

    } catch (IOException e) {
        System.out.println("Error reading file: " + e.getMessage());
    }
}

public static void Checkout(Scanner input){//checks out a book and adds it to the checked out book list/file(preferably a file) with the student's name and due date to return
String book;
	System.out.println("Enter book to checkout (title):");
	book=input.nextLine();

	String studentName = currentStudent;
    String searchTitle = book.trim();
	//to do:search loop to find the book with a catch if the book isn't available and a write back to remove from available and add to checked with student name and due date added to the info
try {
        // Read all lines from books.txt
        BufferedReader br = new BufferedReader(new FileReader("books.txt"));
        String line;
        java.util.List<String> lines = new java.util.ArrayList<>();

        boolean found = false;
        boolean available = false;
        int foundIndex = -1;

        // Info about the book we’re checking out
        String id = "";
        String title = "";
        String author = "";
        String year = "";

        while ((line = br.readLine()) != null) {
            lines.add(line);

            String[] parts = line.split(",");
            if (parts.length < 2) {
                continue; // malformed line, skip
            }

            String lineTitle = parts[1].trim();

            if (lineTitle.equalsIgnoreCase(searchTitle)) {
                found = true;

                if (parts.length > 0) id = parts[0].trim();
                if (parts.length > 1) title = parts[1].trim();
                if (parts.length > 2) author = parts[2].trim();
                if (parts.length > 3) year = parts[3].trim();
                if (parts.length > 4) {
                    available = Boolean.parseBoolean(parts[4].trim());
                } else {
                    // if availability field missing, assume available
                    available = true;
                }

                foundIndex = lines.size() - 1;
                // don’t break here if you want to handle duplicates; for now we take first match
                break;
            }
        }
        br.close();

        if (!found) {
            System.out.println("Book not found in library.");
            return;
        }

        if (!available) {
            System.out.println("That book is already checked out.");
            return;
        }

        // Ask extra info for the checkout
        System.out.println("Enter due date (e.g. 2025-12-01):");
        String dueDate = input.nextLine().trim();

        // Update the line in books.txt to mark as not available (false)
        String originalLine = lines.get(foundIndex);
        String[] p = originalLine.split(",");

        // make sure there is an availability field at index 4
        if (p.length < 5) {
            String[] extended = new String[5];
            for (int i = 0; i < p.length; i++) {
                extended[i] = p[i];
            }
            // default missing fields to empty
            for (int i = p.length; i < 5; i++) {
                extended[i] = "";
            }
            p = extended;
        }

        p[4] = "false"; // mark unavailable

        // rebuild updated book line
        StringBuilder updated = new StringBuilder();
        for (int i = 0; i < p.length; i++) {
            if (i > 0) updated.append(",");
            updated.append(p[i].trim());
        }
        lines.set(foundIndex, updated.toString());

        // Write ALL books back to books.txt
        BufferedWriter bw = new BufferedWriter(new FileWriter("books.txt"));
        for (String l : lines) {
            bw.write(l);
            bw.newLine();
        }
        bw.close();

        // id,title,author,year,studentName,dueDate
        BufferedWriter bw2 = new BufferedWriter(new FileWriter("transcations.txt", true));
        bw2.write(id + "," + title + "," + author + "," + year + "," + studentName + "," + dueDate);
        bw2.newLine();
        bw2.close();

        System.out.println("Book checked out successfully to " + studentName + " with due date " + dueDate + ".");

    } catch (IOException e) {
        System.out.println("Error during checkout: " + e.getMessage());
    }
}

}

public static void Returned(Scanner input){//removes a book from checkout list/file and adds it to available books
String book;
	System.out.println("Enter book to return (title):");
	book=input.nextLine();
	//to do:search loop to find the book with a catch if the book isn't checked out and a write back to remove the book from checked file and add to available file
}

public static void DueDate(Scanner input){//searches for a checked book and displays due date and student in possession
	String book;
	System.out.println("Enter book to the due date for (title):");
	book=input.nextLine();
	//to do:search loop to find the book with a catch if the book isn't checked out and a display of the information
}

public static void AddBook(Scanner input){//adds a book with author, date written, title, and genre to the file
	String title, author, written, genre;
	System.out.println("Enter book title:");
	title=input.nextLine();
	System.out.println("Enter author:");
	author=input.nextLine();
	System.out.println("Enter the year written:");
	written=input.nextLine();
	System.out.println("Enter genre:");
	genre=input.nextLine();
	//to do:combine each element and add to the file
}

public static void RemoveBook(Scanner input){//removes a book w/o adding it to the checked books list
	String book;
	System.out.println("Enter book to remove (title):");
	book=input.nextLine();
	//to do:a search loop to find the book with a catch to tell the user if the book isn't in either file
}

public static void DisplayChecked(){//displays all checkedout books, due dates, and students in possession
	//to do:loop to print all books in the checked file
}
	
public static void student(int choice, Scanner input) {
    System.out.println("Welcome Student");
    while(choice!=4) {
        System.out.println("What would you like to do:");
        System.out.println("1.Display available books");
        System.out.println("2.Checkout a book");
        System.out.println("3.Return a book");
        System.out.println("4.Exit");

        choice = input.nextInt();
        input.nextLine();

        switch(choice){
            case 1:
                DisplayAvailable();
                break;
            case 2:
                Checkout(input);
                break;
            case 3:
                Returned(input);
                break;
            case 4:
                System.out.println("Exiting student menu...");
                return;
        }
    }
}

	
	public static void admin(int choice,Scanner input) {
		//should have a text file for admin login info to verify they are an admin, code needed once we have that
			System.out.println("Welcome Admin");//would like to have a way to pull their name from the file and use it in the welcome message
				while(choice!=6) {
					System.out.println("What would you like to do:");
					System.out.println("1.Add a book");
					System.out.println("2.Check due date of checked out book");
					System.out.println("3.Remove book from availability");
					System.out.println("4.Display available books");
					System.out.println("5.Display checked out books and who they are with");
					System.out.println("6.Exit");
					choice=input.nextInt();
					input.nextLine();

					switch(choice){
						case 1:
							AddBook(input);
							break;
						case 2:
							DueDate(input);
							break;
						case 3:
							RemoveBook(input);
							break;
						case 4:
							DisplayAvailable();
							break;
						case 5:
							DisplayChecked();
							break;
					}
				}
	}
    public static void main(String[] args) {
        String user;
	int choice=0;
	Scanner input=new Scanner(System.in);

	System.out.println("Welcome to the Maverick Library Database");
	System.out.println("----------------------------------------");
	
	System.out.println("Are you a Student or an Administrator:");
	user=input.nextLine();

	if(user.equalsIgnoreCase("Student")) {

            System.out.println("Enter your name:");
            currentStudent = input.nextLine();   // store student name again here

            student(choice, input);
        }
		
	if(user.equalsIgnoreCase("Student")) {
		student(choice,input);
		}
	else if(user.equalsIgnoreCase("Administrator")) {
		admin(choice,input);
		}
	else {
		System.out.println("Invalid input");
		System.out.println("Are you a Student or an Administrator:");
		user=input.nextLine();
		if(user.equalsIgnoreCase("Student")) {
			student(choice,input);
			}
		else if(user.equalsIgnoreCase("Administrator")) {
			admin(choice,input);
			}
			else { 
				System.out.println("invalid input");	
			}
		}

		input.close();
    }
}
