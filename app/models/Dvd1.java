/*
package models;

import java.util.List;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Id;

@Entity("Dvd1")
public class Dvd1 {

    @Id
    private String ISBN;
    private String title;
    private String sector;
    private String publicationDate;
    private String borrowedDate;
    private String currentReader;

    private String language;
    private String subtitles;
    private String producer;
    private String actors;


    @JsonCreator
    public Dvd1(
            @JsonProperty("ISBN") String ISBN,
            @JsonProperty("title") String title,
            @JsonProperty("sector") String sector,
            @JsonProperty("publicationDate") String publicationDate,
            @JsonProperty ("borrowDate")String borrowedDate,
            @JsonProperty ("currentReader")String currentReader,

            @JsonProperty("language") String language,
            @JsonProperty("subtitles") String subtitles,
            @JsonProperty("producer") String producer,
            @JsonProperty("actors") String actors) {


        this.ISBN = ISBN;
        this.title = title;
        this.sector = sector;
        this.publicationDate = publicationDate;
        this.borrowedDate = borrowedDate;
        this.currentReader = currentReader;

        this.language = language;
        this.subtitles = subtitles;
        this.producer = producer;
        this.actors = actors;
    }
    @JsonCreator
    public Dvd1() {
    }

    public String getISBN() {
        return ISBN;
    }



    public void setISBN(String ISBN) {
        this.ISBN = ISBN;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSector() {
        return sector;
    }

    public void setSector(String sector) {
        this.sector = sector;
    }

    public String getPublicationDate() {
        return publicationDate;
    }

    public void setPublicationDate(String publicationDate) {
        this.publicationDate = publicationDate;
    }

 public String getBorrowedDate() {
        return borrowedDate;
    }

    public void setBorrowedDate(String borrowedDate) {
        this.borrowedDate = borrowedDate;
    }

    public String getCurrentReader() {
        return currentReader;
    }

    public void setCurrentReader(String currentReader) {
        this.currentReader = currentReader;
    }


    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getSubtitles() {
        return subtitles;
    }

    public void setSubtitles(String subtitles) {
        this.subtitles = subtitles;
    }

    public String getProducer() {
        return producer;
    }

    public void setProducer(String producer) {
        this.producer = producer;
    }

    public String getActors() {
        return actors;
    }

    public void setActors(String actors) {
        this.actors = actors;
    }

    @Override
    public String toString() {
        return "Dvd1{" +
                "ISBN='" + ISBN + '\'' +
                ", title='" + title + '\'' +
                ", sector='" + sector + '\'' +
                ", publicationDate='" + publicationDate + '\'' +
                ", borrowedDate='" + borrowedDate + '\'' +
                ", currentReader='" + currentReader + '\'' +
                ", language='" + language + '\'' +
                ", subtitles='" + subtitles + '\'' +
                ", producer='" + producer + '\'' +
                ", actors='" + actors + '\'' +
                '}';
    }
}
*/
