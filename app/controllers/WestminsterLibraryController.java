/*
package controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientOptions;
import com.sun.org.apache.regexp.internal.RE;
import models.*;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.Morphia;
import org.mongodb.morphia.query.Query;
import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Result;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class WestminsterLibraryController extends Controller{

    Datastore datastore;

    public void mongoConncetion(){

        final Morphia morphia = new Morphia();

        morphia.mapPackage("PackageName");
        MongoClient mongoClient = new MongoClient("127.0.0.1",27017);

        datastore = morphia.createDatastore(mongoClient, "LibrarySystem");
        MongoClientOptions.Builder options = MongoClientOptions.builder();
        options.socketKeepAlive(true);

    }

    public Result addbook()  {

        mongoConncetion();

        JsonNode values = request().body().asJson();

        System.out.println(values);

        JsonNode innerNode1 = values.get("ISBN");

        JsonNode innerNode2 = values.get("title");
        JsonNode innerNode3 = values.get("sector");
        JsonNode innerNode4 = values.get("publicationDate");
        JsonNode innerNode5 = values.get("authors");
        JsonNode innerNode6 = values.get("publisherBook");
        JsonNode innerNode7 = values.get("pagesBook");

        String ISBN = innerNode1.asText();
        String title = innerNode2.asText();
        String sector = innerNode3.asText();
        String publicationDate = innerNode4.asText();
        String author = innerNode5.asText();
        //List<String> authors =author.split(","));

        List<String> authors = new ArrayList<String>(Arrays.asList(author.split(",")));

        String publisher = innerNode6.asText();
        int noOfPages = innerNode7.asInt();

        System.out.println(ISBN+title+sector+publicationDate+authors+publisher+noOfPages);


        Book book = new Book(ISBN,title,sector,publicationDate,authors,publisher,noOfPages);

        datastore.save(book);
        return ok(Json.toJson("Book Added"));


    }

    public Result addDvd() {

       */
/* mongoConncetion();

        JsonNode values = request().body().asJson();

        *//*
*/
/*ObjectMapper objectMapper  = new ObjectMapper();
        DVD dvd = objectMapper.treeToValue(values,DVD.class);*//*
*/
/*

        DVD dvd = (DVD) Json.fromJson(values,DVD.class);
        datastore.save(dvd);
        return ok(Json.toJson("DVD Added"));*//*


        mongoConncetion();

        JsonNode values = request().body().asJson();

        JsonNode innerNode1 = values.get("ISBN");
        JsonNode innerNode2 = values.get("title");
        JsonNode innerNode3 = values.get("sector");
        JsonNode innerNode4 = values.get("publicationDate");
        JsonNode innerNode5 = values.get("language");
        JsonNode innerNode6 = values.get("subtitles");
        JsonNode innerNode7 = values.get("producer");
        JsonNode innerNode8 = values.get("actors");

        String ISBN = innerNode1.asText();
        String title = innerNode2.asText();
        String sector = innerNode3.asText();
        String publicationDate = innerNode4.asText();
        String language = innerNode5.asText();
        List<String> languages = Arrays.asList(language.split(","));

        String subtitles = innerNode6.asText();
        List<String> subtitle =Arrays.asList(subtitles.split(","));

        String producer = innerNode7.asText();

        String actors = innerNode8.asText();
        List<String> actor = Arrays.asList(actors.split(","));

        DVD dvd = new DVD(ISBN,title,sector,publicationDate,languages,subtitle,producer,actor);

        datastore.save(dvd);
        return ok(Json.toJson("DVD Added"));
    }



     public Result deleteItem(String ISBN) {

        mongoConncetion();

        //Query<Book> bookQuery = datastore.find(Book.class).disableValidation().field("id").equal(ISBN).limit(1);
        Query<Book> bookQuery = datastore.createQuery(Book.class).field("_id").equal(ISBN);
        datastore.findAndDelete(bookQuery);

       */
/* Book book  = bookQuery.get();
        if(book != null){
            datastore.delete(book);

        }*//*



        Query<DVD> dvdQuery = datastore.createQuery(DVD.class).field("_id").equal(ISBN);
            //check null
            datastore.delete(dvdQuery);
            //return not Found if null

        return ok(Json.toJson("Item deleted"));

    }

    public Result getAllBooks(){
        mongoConncetion();
        List<Book> bookList = datastore.find(Book.class).asList();
        return ok(Json.toJson(bookList));
    }

    public Result getAllDvd() {
        mongoConncetion();
        List<DVD> dvdList = datastore.find(DVD.class).asList();
        return ok(Json.toJson(dvdList));
    }


    public void displayList() {


    }


    public void borrowItem(String ISBN, Reader currentReader, DateTime dateTime) {

        DateTime dateTime1 = new DateTime();

 /* System.out.println(book);
        System.out.println(dvd);
        System.out.println(itemsList.size());
*/

/*        for(LibraryItem items : itemsList){
            System.out.println("dfssdf");
            if((book != null)|| (dvd !=null)){

                if(items.getCurrentReader()==null){
                    items.setCurrentReader(currentReader);
                    items.setBorrowedDate(dateTime);
                    if(book != null){
                        dueDate = dateTime.addDays(7);
                        System.out.println("Due date"+dueDate.getFullDate());
                    }


                }else{
                    System.out.println("The library item is not available right now.");
                    items.getBorrowedDate();
                }

            }else{
                System.out.println("Enter a valid ISBN");

            }

        }*/         //   System.out.println("dfssdf");

//getborrowed date list
                    /*Query query = datastore.find(Book.class);
                    List<Book> documentPojos = query.asList();

                    documentPojos.forEach(obj -> {
                        System.out.println("dfsjhd"+obj.getBorrowedDate().getFullDate());
                            }
                    );*/
                   /* Query query = datastore.find(Book.class).filter("borrowedDate",borrowedDate);
                    List<Book> documentPojos = query.asList();
                    System.out.println("fsdfsd"+documentPojos);*/
                   /* documentPojos.forEach(obj -> {
                                System.out.println("dfsjhd"+obj);
                            }
                    );



    }


    public void returnItem(String ISBN, Reader currentReader, DateTime dateTime) {

    }
}
*/
/*
public DateTime addDays(int days){
        if (day+days > 30){
        return new DateTime(year, month +1,(30 - day + days),time);
        }
        else return new DateTime(year,month,day+days,time);
        }*/
/* public int compareTo(DateTime dateTime1) {
        int years =  dateTime1.year - this.year;
        int months = dateTime1.month - this.month;
        int dates = dateTime1.day - this.day;
        int hours = dateTime1.time - this.time;

        return daysToHours( years * 365 + months *30 + dates) + hours;
    }*/

//generate report
/*  double fee;
        System.out.println("Today"+dateTime.getFullDate());

        mongoConncetion();

        Book book = datastore.find(Book.class).get();
        DateTime borrowedDate = book.getBorrowedDate();
        System.out.println("books"+book);

       *//* Query query = datastore.find(Book.class).filter("borrowedDate",borrowedDate);
        List<Book> bookList = query.asList();*//*

        List<Book> bookList = datastore.find(Book.class).asList();
        System.out.println("bookList"+bookList);

        for(int i=0; i<bookList.size(); i++){
           // libraryItemList = bookList.get(i).getBorrowedDate()


            borrowDate = dateTime1.convertToHours(bookList.get(i).getBorrowedDate());
            reportDate = dateTime1.convertToHours(dateTime);
            fee = calculateFee(borrowDate,reportDate);
            System.out.println(fee);
            borrowDateList.add(fee);
        }
      *//*  bookList.forEach(obj -> {
                    System.out.println("dfsjhd" + obj);

                });*//*
 */