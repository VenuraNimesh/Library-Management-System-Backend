package models;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Id;

@Entity("Reader")
public class Reader {

    @Id
    private String ID;
    private String Name;
    private int mobNo;
    private String email;

    @JsonCreator
    public Reader(
           @JsonProperty("ID") String ID,
           @JsonProperty("name") String name,
           @JsonProperty("mobNo") int mobNo,
           @JsonProperty("email") String email) {
        this.ID = ID;
        this.Name = name;
        this.mobNo = mobNo;
        this.email = email;
    }
    @JsonCreator
    public Reader() {
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public void setName(String name) {
        Name = name;
    }

    public void setMobNo(int mobNo) {
        this.mobNo = mobNo;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getID() {
        return ID;
    }

    public String getName() {
        return Name;
    }

    public int getMobNo() {
        return mobNo;
    }

    public String getEmail() {
        return email;
    }
}

