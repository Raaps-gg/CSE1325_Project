public class Book {
    private String id;
    private String title;
    private String author;
    private String year;
    private boolean available;

    public Book(String id, String title, String author, String year, boolean available) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.year = year;
        this.available = available;
    }

    public String getId() { return id; }
    public String getTitle() { return title; }
    public String getAuthor() { return author; }
    public String getYear() { return year; }
    public boolean isAvailable() { return available; }
    public void setAvailable(boolean available) { this.available = available; }

    public String toString() {
        return id + "," + title + "," + author + "," + year + "," + available;
    }

    public static Book fromString(String line) {
        String[] parts = line.split(",");
        if (parts.length < 4) return null;
        
        String id = parts[0].trim();
        String title = parts[1].trim();
        String author = parts[2].trim();
        String year = parts[3].trim();
        boolean available = parts.length > 4 ? Boolean.parseBoolean(parts[4].trim()) : true;
        
        return new Book(id, title, author, year, available);
    }
}