/*
package models;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Id;

import java.util.HashSet;
import java.util.Set;

@Entity("Book1")
public class Book1 {

    @Id
    public String isbnNoBook;
    public String titleBook;
    public String sectorBook;
    public String pubDateBook;
    public String authorsBook;
    public String publisherBook;
    public int pagesBook;


    @JsonCreator
    public Book1(
            @JsonProperty("isbnNoBook") String isbnNoBook,
            @JsonProperty("titleBook") String titleBook,
            @JsonProperty("sectorBook") String sectorBook,
            @JsonProperty("pubDateBook") String pubDateBook,

            @JsonProperty("authorsBook") String authorsBook,
            @JsonProperty("publisherBook") String publisherBook,
            @JsonProperty("pagesBook") int pagesBook) {
        this.isbnNoBook = isbnNoBook;
        this.titleBook = titleBook;
        this.sectorBook = sectorBook;
        this.pubDateBook = pubDateBook;
        this.authorsBook = authorsBook;
        this.publisherBook = publisherBook;
        this.pagesBook = pagesBook;
    }

    */
/* public Book(@JsonProperty("isbnNoBook") String isbnNoBook, @JsonProperty("titleBook") String titleBook){
         this.isbnNoBook=isbnNoBook;
         this.titleBook=titleBook;
     }*//*

    @JsonCreator
    public Book1() {
    }

    @Override
    public String toString() {
        return "Book{" +
                "isbnNoBook='" + isbnNoBook + '\'' +
                ", titleBook='" + titleBook + '\'' +
                ", sectorBook='" + sectorBook + '\'' +
                ", pubDateBook='" + pubDateBook + '\'' +
                ", authorsBook='" + authorsBook + '\'' +
                ", publisherBook='" + publisherBook + '\'' +
                ", pagesBook=" + pagesBook +
                '}';
    }


    public String getIsbnNoBook() {
        return isbnNoBook;
    }

    public void setIsbnNoBook(String isbnNoBook) {
        this.isbnNoBook = isbnNoBook;
    }

    public String getTitleBook() {
        return titleBook;
    }

    public void setTitleBook(String titleBook) {
        this.titleBook = titleBook;
    }

    public String getSectorBook() {
        return sectorBook;
    }

    public void setSectorBook(String sectorBook) {
        this.sectorBook = sectorBook;
    }

    public String getPubDateBook() {
        return pubDateBook;
    }

    public void setPubDateBook(String pubDateBook) {
        this.pubDateBook = pubDateBook;
    }

    public String getAuthorsBook() {
        return authorsBook;
    }

    public void setAuthorsBook(String authorsBook) {
        this.authorsBook = authorsBook;
    }

    public String getPublisherBook() {
        return publisherBook;
    }

    public void setPublisherBook(String publisherBook) {
        this.publisherBook = publisherBook;
    }

    public int getPagesBook() {
        return pagesBook;
    }

    public void setPagesBook(int pagesBook) {
        this.pagesBook = pagesBook;
    }
}*/
