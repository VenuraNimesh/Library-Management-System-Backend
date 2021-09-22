package models;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Id;

@Entity("LibraryItem")
public abstract class LibraryItem {


    @Id
    private String ISBN;
    private String title;
    private String sector;
    private String publicationDate;
    private DateTime borrowedDate;
    private Reader currentReader;
   /* private boolean status;
    static Book[] book= new Book[100];
    static DVD[] dvd = new DVD[50];*/


    @JsonCreator
    public LibraryItem(
            @JsonProperty("ISBN") String ISBN,
            @JsonProperty("title") String title,
            @JsonProperty("sector") String sector,
            @JsonProperty("publicationDate") String publicationDate,
            @JsonProperty("borrowedDate") DateTime borrowedDate,
            @JsonProperty("currentReader") Reader currentReader) {
        this.ISBN = ISBN;
        this.title = title;
        this.sector = sector;
        this.publicationDate = publicationDate;
        this.borrowedDate = borrowedDate;
        this.currentReader = currentReader;
    }

    @JsonCreator
    public LibraryItem() {
    }

    public LibraryItem(String ISBN, String title, String sector, String publicationDate) {
        this.ISBN = ISBN;
        this.title = title;
        this.sector = sector;
        this.publicationDate = publicationDate;
    }

    public String getISBN() {
        return ISBN;
    }

    public abstract int rentItemDays();

    public String getTitle() {
        return title;
    }

    public String getSector() {
        return sector;
    }

    public String getPublicationDate() {
        return publicationDate;
    }

    public DateTime getBorrowedDate() {
        return borrowedDate;
    }

    public void setBorrowedDate(DateTime borrowedDate) {
        this.borrowedDate = borrowedDate;
    }

    public Reader getCurrentReader() {
        return currentReader;
    }

    public void setCurrentReader(Reader currentReader) {
        this.currentReader = currentReader;
    }

    public void setTitle(String title) {
        this.title = title;
    }

   /* public static Book[] getBook() {
        return book;
    }

    public static void setBook(Book[] book) {
        LibraryItem.book = book;
    }

    public static DVD[] getDvd() {
        return dvd;
    }

    public static void setDvd(DVD[] dvd) {
        LibraryItem.dvd = dvd;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }*/

    @Override
    public String toString() {
        return "LibraryItem{" +
                "ISBN='" + ISBN + '\'' +
                ", title='" + title + '\'' +
                ", sector='" + sector + '\'' +
                ", publicationDate='" + publicationDate + '\'' +
                ", borrowedDate=" + borrowedDate +
                ", currentReader=" + currentReader +
               /* ", status=" + status +*/
                '}';
    }
}
