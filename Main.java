import Java.util.Scanner;
import Java.util.Scanner;
import Java.io.FileWriter;
import Java.io.FileReader;
import Java.io.BufferedReader;
import Java.io.BufferedWriter;
import Java.io.IOException;//forgot they have to be out of the public class

public class Main {
public static void DisplayAvailable(){//displays all non checked out books(for admin and students)
//To do:loop to display all books in the available file
}

public static void Checkout(Scanner input){//checks out a book and adds it to the checked out book list/file(preferably a file) with the student's name and due date to return
String book;
	System.out.println("Enter book to checkout (title):");
	book=input.nextLine();
	//to do:search loop to find the book with a catch if the book isn't available and a write back to remove from available and add to checked with student name and due date added to the info
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
	
public static void student(int choice,Scanner input) {
		System.out.println("Welcome Student");
		while(choice!=4) {
		System.out.println("What would you like to do:");
		System.out.println("1.Display available books");
		System.out.println("2.Checkout a book");
		System.out.println("3.Return a book");
		System.out.println("4.Exit");
		choice=input.nextInt();
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
				}
			}
	}
	
	public static void admin(int choice,Scanner input) {
		//should have a text file for admin login info to verify they are an admin, code needed once we have that
			System.out.println("Welcome Admin");//would like to have a way to pull their name from the file and use it in the welcome message
				while(choice!=3) {
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
		}
    }
}
