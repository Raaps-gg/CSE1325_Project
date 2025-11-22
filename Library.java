import java.util.*;

public class Library {
    private List<Book> books;

    public Library() {
        books = FileUtils.loadBooks();
    }

    public List<Book> getBooks() {
        return books;
    }

    public void addBook(Book newBook) {
        books.add(newBook);
        FileUtils.saveBooks(books);
    }

    public boolean checkoutBook(String title, String studentName, String dueDate) {
        for (Book book : books) {
            if (book.getTitle().equalsIgnoreCase(title.trim())) {
                if (!book.isAvailable()) {
                    System.out.println("That book is already checked out.");
                    return false;
                }
                
                book.setAvailable(false);
                FileUtils.saveBooks(books);
                
                Transaction transaction = new Transaction(book, studentName, 
                    new java.text.SimpleDateFormat("yyyy-MM-dd").format(new java.util.Date()), 
                    dueDate);
                FileUtils.appendTransaction(transaction.toString());
                
                System.out.println("Book checked out successfully to " + studentName + 
                                   " with due date " + dueDate + ".");
                return true;
            }
        }
        System.out.println("Book not found in library.");
        return false;
    }

    public boolean returnBook(String title) {
        for (Book book : books) {
            if (book.getTitle().equalsIgnoreCase(title.trim())) {
                if (book.isAvailable()) {
                    System.out.println("That book is not checked out.");
                    return false;
                }
                
                book.setAvailable(true);
                FileUtils.saveBooks(books);
                System.out.println("Book returned successfully.");
                return true;
            }
        }
        System.out.println("Book not found in library.");
        return false;
    }

    public void displayAvailableBooks() {
        System.out.println("\n=== Available Books ===");
        boolean found = false;
        for (Book book : books) {
            if (book.isAvailable()) {
                System.out.println(book.getTitle() + " by " + book.getAuthor() + 
                                   " (" + book.getYear() + ")");
                found = true;
            }
        }
        if (!found) {
            System.out.println("No available books.");
        }
    }

    public boolean removeBook(String title) {
        Iterator<Book> iterator = books.iterator();
        while (iterator.hasNext()) {
            Book book = iterator.next();
            if (book.getTitle().equalsIgnoreCase(title.trim())) {
                iterator.remove();
                FileUtils.saveBooks(books);
                System.out.println("Book removed successfully.");
                return true;
            }
        }
        System.out.println("Book not found.");
        return false;
    }
}