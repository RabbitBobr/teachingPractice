package bobrov.projectBook;

/**
 * Created by пк on 27.07.2016.
 */
public class Book {

    private String name;
    private Author author;
    private int publicationDate;
    private boolean readingStatus;
    private String topic;
    private String URL;

    public Book(String name, Author author, int publicationDate, boolean readingStatus,
                String topic, String URL) {
        this.name = name;
        this.author = author;
        this.publicationDate = publicationDate;
        this.readingStatus = readingStatus;
        this.topic = topic;
        this.URL = URL;
    }

    public Book(String name, Author author, boolean readingStatus, String topic) {
        this.name = name;
        this.author = author;
        this.readingStatus = readingStatus;
        this.topic = topic;
    }

    public Book(String name, boolean readingStatus) {
        this.name = name;
        this.readingStatus = readingStatus;
        this.author = new Author("noname","noname", "noname", "nocountry");
    }
}
