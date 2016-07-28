package bobrov.projectBook;

/**
 * Created by пк on 27.07.2016.
 */
public class Book {

    private int id;
    private String author;
    private String bookName;
    private String tema;
    private boolean status;

    public Book(int id, String author, String bookName, String tema, boolean status) {
        this.id = id;
        this.author = author;
        this.bookName = bookName;
        this.tema = tema;
        this.status = status;
    }

    public Book(String author, String bookName, String tema, boolean status) {
        this.author = author;
        this.bookName = bookName;
        this.tema = tema;
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public String getTema() {
        return tema;
    }

    public void setTema(String tema) {
        this.tema = tema;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
}
