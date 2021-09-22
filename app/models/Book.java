package models;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.mongodb.morphia.annotations.Entity;

import java.util.List;

@Entity("Book")
public class Book extends LibraryItem{

    private static int bookCount;
    private List<String> author;
    private String publisher;
    private int noOfPages;

    @JsonCreator
    public Book(
           @JsonProperty("ISBN") String ISBN,
           @JsonProperty("title") String title,
           @JsonProperty("sector") String sector,
           @JsonProperty("publicationDate") String publicationDate,
           @JsonProperty("author") List<String> author,
           @JsonProperty("publisher") String publisher,
           @JsonProperty("noOfPages")int noOfPages) {

        super(ISBN, title, sector, publicationDate);
        this.author = author;
        this.publisher = publisher;
        this.noOfPages = noOfPages;
    }

    @JsonCreator
    public Book() {
    }

    public Book(String ISBN, String title, String sector, String publicationDate, DateTime borrowedDate, Reader currentReader, List<String> author, String publisher, int noOfPages) {
        super(ISBN, title, sector, publicationDate, borrowedDate, currentReader);
        this.author = author;
        this.publisher = publisher;
        this.noOfPages = noOfPages;
    }

    public Book(List<String> author, String publisher, int noOfPages) {
        this.author = author;
        this.publisher = publisher;
        this.noOfPages = noOfPages;
    }

    public static void setBookCount(int bookCount) {
        Book.bookCount = bookCount;
    }

    public void setAuthor(List<String> author) {
        this.author = author;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public void setNoOfPages(int noOfPages) {
        this.noOfPages = noOfPages;
    }

    public static int getBookCount() {
        return bookCount;
    }


    public List<String> getAuthor() {
        return author;
    }

    public String getPublisher() {
        return publisher;
    }

    public int getNoOfPages() {
        return noOfPages;
    }




    @Override
    public int rentItemDays() {
        return 7;
    }

    @Override
    public String toString() {
        return "Book{" +
                "author=" + author +
                ", publisher='" + publisher + '\'' +
                ", noOfPages=" + noOfPages +
                ", borrowedDate=" + getBorrowedDate() +
                ", currentReader=" + getCurrentReader() +
                '}';
    }

}
