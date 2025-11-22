import java.util.*;
import java.io.*;

public class FileUtils {
    private static final String BOOKS_FILE = "books.txt";
    private static final String TRANSACTIONS_FILE = "transactions.txt";

    public static boolean verifyAdmin(String id, String password) {
    try (BufferedReader br = new BufferedReader(new FileReader("admins.txt"))) {
        String line;
        while ((line = br.readLine()) != null) {
            String[] parts = line.split(",");
            if (parts.length >= 3) {
                if (parts[0].trim().equals(id) && parts[2].trim().equals(password)) {
                    return true;
                }
            }
        }
    } catch (IOException e) {
        System.out.println("Error reading admin credentials: " + e.getMessage());
    }
    return false;
}

public static String getAdminName(String id) {
    try (BufferedReader br = new BufferedReader(new FileReader("admins.txt"))) {
        String line;
        while ((line = br.readLine()) != null) {
            String[] parts = line.split(",");
            if (parts.length >= 2 && parts[0].trim().equals(id)) {
                return parts[1].trim();
            }
        }
    } catch (IOException e) {
        System.out.println("Error reading admin name: " + e.getMessage());
    }
    return "Admin";
}

    public static List<Book> loadBooks() {
        List<Book> books = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(BOOKS_FILE))) {
            String line;
            while ((line = br.readLine()) != null) {
                if (!line.trim().isEmpty()) {
                    Book book = Book.fromString(line);
                    if (book != null) {
                        books.add(book);
                    }
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("books.txt not found. Creating new file.");
        } catch (IOException e) {
            System.out.println("Error reading books.txt: " + e.getMessage());
        }
        return books;
    }

    public static void saveBooks(List<Book> books) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(BOOKS_FILE))) {
            for (Book b : books) {
                bw.write(b.toString());
                bw.newLine();
            }
        } catch (IOException e) {
            System.out.println("Error writing to books.txt: " + e.getMessage());
        }
    }

    public static void appendTransaction(String transactionDetails) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(TRANSACTIONS_FILE, true))) {
            bw.write(transactionDetails);
            bw.newLine();
        } catch (IOException e) {
            System.out.println("Error writing transaction: " + e.getMessage());
        }
    }

    public static void displayTransactions() {
        System.out.println("\n=== All Transactions ===");
        try (BufferedReader br = new BufferedReader(new FileReader(TRANSACTIONS_FILE))) {
            String line;
            boolean found = false;
            while ((line = br.readLine()) != null) {
                if (!line.trim().isEmpty()) {
                    String[] parts = line.split(",");
                    if (parts.length >= 6) {
                        System.out.println("Book: " + parts[1] + " | Student: " + 
                                           parts[4] + " | Due: " + parts[5]);
                        found = true;
                    }
                }
            }
            if (!found) {
                System.out.println("No transactions found.");
            }
        } catch (FileNotFoundException e) {
            System.out.println("No transactions found.");
        } catch (IOException e) {
            System.out.println("Error reading transactions: " + e.getMessage());
        }
    }

    public static void displayCheckedOutBooks() {
        System.out.println("\n=== Currently Checked Out Books ===");
        try (BufferedReader br = new BufferedReader(new FileReader(TRANSACTIONS_FILE))) {
            String line;
            boolean found = false;
            while ((line = br.readLine()) != null) {
                if (!line.trim().isEmpty()) {
                    String[] parts = line.split(",");
                    if (parts.length >= 6) {
                        System.out.println("Book: " + parts[1] + " by " + parts[2] + 
                                           " | Student: " + parts[4] + " | Due: " + parts[5]);
                        found = true;
                    }
                }
            }
            if (!found) {
                System.out.println("No books currently checked out.");
            }
        } catch (FileNotFoundException e) {
            System.out.println("No books currently checked out.");
        } catch (IOException e) {
            System.out.println("Error reading transactions: " + e.getMessage());
        }
    }
}