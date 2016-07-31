package bobrov.projectBook;

/**
 * Created by пк on 27.07.2016.
 */
public class Book {

    private int idBook;
    private String author;
    private String bookName;
    private String tema;
    private boolean status;
    private int data;
    private String link;

    public Book(int idBook, String bookName, String author, int data, String tema, boolean status, String link) {
        this.idBook = idBook;
        this.author = author;
        this.bookName = bookName;
        this.tema = tema;
        this.status = status;
        this.data = data;
        this.link = link;
    }

    public Book(String bookName, String author, int data, String tema, boolean status, String link) {
        this.author = author;
        this.bookName = bookName;
        this.tema = tema;
        this.status = status;
        this.data = data;
        this.link = link;
    }

    public String getLink() {
        return link;
    }



    public int getData() {
        return data;
    }



    public int getIdBook() {
        return idBook;
    }



    public String getAuthor() {
        return author;
    }



    public String getBookName() {
        return bookName;
    }



    public String getTema() {
        return tema;
    }



    public boolean isStatus() {
        return status;
    }



    @Override
    public String toString() {
        return "Book{" +
                "idBook=" + idBook +
                ", author='" + author + '\'' +
                ", bookName='" + bookName + '\'' +
                ", tema='" + tema + '\'' +
                ", status=" + status +
                ", data=" + data +
                ", link='" + link + '\'' +
                '}';
    }
}
