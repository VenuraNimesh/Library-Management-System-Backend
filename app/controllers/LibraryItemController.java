package controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientOptions;
import models.*;
//import models.Dvd1;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.Morphia;
//import play.api.libs.json.Json;
import org.mongodb.morphia.query.Query;
import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Result;

import java.time.LocalDateTime;
import java.util.*;

public class LibraryItemController extends Controller{

    WestminsterLibraryManager westminsterLibraryManager= new WestminsterLibraryManager();
    //public static List<LibraryItem> itemList;
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

        List<String> authors = new ArrayList<String>(Arrays.asList(author.split(",")));

        String publisher = innerNode6.asText();
        int noOfPages = innerNode7.asInt();

        System.out.println(ISBN+title+sector+publicationDate+authors+publisher+noOfPages);


        Book book = new Book(ISBN,title,sector,publicationDate,authors,publisher,noOfPages);

        String response=westminsterLibraryManager.addItem(book);

        return ok(Json.toJson(response));
    }


    public Result addDvd()  {

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


        String status = westminsterLibraryManager.addItem(dvd);

        return ok(Json.toJson(status));

    }

    public Result getAllBooks(){
        mongoConncetion();
        List<Book> bookList = datastore.find(Book.class).asList();
        //westminsterLibraryManager.displayList(bookList);
        //itemList.addAll(bookList);
        System.out.println("booklist"+bookList);
        return ok(Json.toJson(bookList));
    }

    public Result getAllDvd() {
        mongoConncetion();
        List<DVD> dvdList = datastore.find(DVD.class).asList();
        //itemList.addAll(dvdList);
        return ok(Json.toJson(dvdList));
    }

    /*public Result getAllBookByIsbn(String bookIsBn){

        mongoConncetion();
        Book book = datastore.find(Book.class).field("isbnNoBook").equal(bookIsBn).get();
        return ok(Json.toJson(book));
    }*/

    public Result deleteItem(String ISBN) {

        String ISBN1 = ISBN;

        WestminsterLibraryManager deleteItem = new WestminsterLibraryManager();
        String status = deleteItem.deleteItem(ISBN1);

        return ok(Json.toJson(status));

    }

    public Result borrowItem() {

        mongoConncetion();
        JsonNode values = request().body().asJson();

        JsonNode innerNode1 = values.get("ISBN");
        JsonNode innerNode2 = values.get("readerId");
        JsonNode innerNode3 = values.get("borrowDate");

        String ISBN = innerNode1.asText();
        String readerId = innerNode2.asText();

        Reader readerDetails = westminsterLibraryManager.findReader(readerId);
       // Reader reader = new Reader(readerDetails);

        String t[]=innerNode3.asText().split("T");
        System.out.println("timeeeee"+innerNode3);
        String a = t[0];
        String b = t[1];

        String borrowDate[] = a.split("-");
        int year = Integer.parseInt(borrowDate[0]);
        int month = Integer.parseInt(borrowDate[1]);
        int day = Integer.parseInt(borrowDate[2]);

        String borrowTime[] = b.split(":");
        int time = Integer.parseInt(borrowTime[0]);

        DateTime dateTime = new DateTime(year,month,day,time);
        System.out.println("datetime"+dateTime.getFullDate());

        String status = westminsterLibraryManager.borrowItem(ISBN,readerDetails,dateTime);

        return ok(Json.toJson(status));
    }

    public Result returnItem() {
        JsonNode values = request().body().asJson();
        System.out.println(values);

        JsonNode innerNode1 = values.get("ISBN");
        JsonNode innerNode2 = values.get("returnDate");

        String ISBN = innerNode1.asText();

        String t[]=innerNode2.asText().split("T");
        String a = t[0];
        String b = t[1];

        String returnDate[] = a.split("-");
        int year = Integer.parseInt(returnDate[0]);
        int month = Integer.parseInt(returnDate[1]);
        int day = Integer.parseInt(returnDate[2]);


        String returnTime[] = b.split(":");
        int time = Integer.parseInt(returnTime[0]);

        DateTime dateTime = new DateTime(year,month,day,time);


       String status = westminsterLibraryManager.returnItem(ISBN,dateTime);


        return ok(Json.toJson(status));    }

/*
    public void libraryList(List<LibraryItem> itemList) {

        List<Book> bookList = datastore.find(Book.class).asList();
        List<DVD> dvdList = datastore.find(DVD.class).asList();
        itemList.addAll(bookList);
        itemList.addAll(dvdList);
        westminsterLibraryManager.displayList(itemList);


    }*/
    public Result generateReport() {
        LocalDateTime systemDate;
        mongoConncetion();

        systemDate= LocalDateTime.now();


        DateTime dateTime = new DateTime(systemDate.getYear(),systemDate.getMonthValue(),systemDate.getDayOfMonth(),systemDate.getHour());
        List response = westminsterLibraryManager.generateReport(dateTime);

       /* *//*Book book = datastore.find(Book.class).filter("currentReader", true).get();
        DateTime bookBorrowedDate = book.getBorrowedDate();*//*

        Book book = datastore.find(Book.class).get();
        Query query = datastore.find(Book.class).filter("borrowedDate",book.getBorrowedDate());

        List<Book> bookList = query.asList();
*/
        return ok(Json.toJson(response));
    }

    public Result listOfItems(){

        List itemList = westminsterLibraryManager.displayList();
        return ok(Json.toJson(itemList));
    }

    public Result bookCount(){
        mongoConncetion();
        int bookCount = (int) datastore.find(Book.class).countAll();
        System.out.println(bookCount);
        return ok(Json.toJson(bookCount));
    }
    public Result dvdCount(){
        mongoConncetion();
        int dvdCount = (int) datastore.find(DVD.class).countAll();
        System.out.println(dvdCount);
        return ok(Json.toJson(dvdCount));
    }
}
