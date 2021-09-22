package models;

import java.util.List;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.mongodb.morphia.annotations.Entity;

@Entity("DVD")
public class DVD extends LibraryItem{

    private static int dvdCount;
    private List<String> language;
    private List<String> subtitles;
    private String producer;
    private List<String> actors;


    @JsonCreator
    public DVD(
           @JsonProperty("ISBN") String ISBN,
           @JsonProperty("title") String title,
           @JsonProperty("sector") String sector,
           @JsonProperty("publicationDate") String publicationDate,
           @JsonProperty("language") List<String> language,
           @JsonProperty("subtitles") List<String> subtitles,
           @JsonProperty("producer") String producer,
           @JsonProperty("actors") List<String> actors) {
        super(ISBN, title, sector, publicationDate);
        this.language = language;
        this.subtitles = subtitles;
        this.producer = producer;
        this.actors = actors;
    }


    @JsonCreator
    public DVD() {
    }

    public static void setDvdCount(int dvdCount) {
        DVD.dvdCount = dvdCount;
    }

    public void setLanguage(List<String> language) {
        this.language = language;
    }

    public void setSubtitles(List<String> subtitles) {
        this.subtitles = subtitles;
    }

    public void setProducer(String producer) {
        this.producer = producer;
    }

    public void setActors(List<String> actors) {
        this.actors = actors;
    }

    public static int getDvdCount() {
        return dvdCount;
    }

    public List<String> getLanguage() {
        return language;
    }

    public List<String> getSubtitles() {
        return subtitles;
    }

    public String getProducer() {
        return producer;
    }

    public List<String> getActors() {
        return actors;
    }


    @Override
    public int rentItemDays() {
        return 3;
    }

    @Override
    public String toString() {
        return "DVD{" +
                "language=" + language +
                ", subtitles=" + subtitles +
                ", producer='" + producer + '\'' +
                ", actors=" + actors +
                '}';
    }
}

