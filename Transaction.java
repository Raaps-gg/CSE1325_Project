import java.text.SimpleDateFormat;
import java.util.Date;

public class Transaction {
    private Book book;
    private String studentName;
    private String checkoutDate;
    private String dueDate;

    public Transaction(Book book, String studentName, String checkoutDate, String dueDate) {
        this.book = book;
        this.studentName = studentName;
        this.checkoutDate = checkoutDate;
        this.dueDate = dueDate;
    }

    public Book getBook() { return book; }
    public String getStudentName() { return studentName; }
    public String getCheckoutDate() { return checkoutDate; }
    public String getDueDate() { return dueDate; }

    public String toString() {
        return book.getId() + "," + book.getTitle() + "," + book.getAuthor() + "," + 
               book.getYear() + "," + studentName + "," + dueDate;
    }
}