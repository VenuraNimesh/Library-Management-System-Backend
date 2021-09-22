package models;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientOptions;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.Morphia;
import org.mongodb.morphia.query.Query;
import org.mongodb.morphia.query.UpdateOperations;

import java.util.*;


public class WestminsterLibraryManager implements LibraryManager {
     List<LibraryItem> itemsList = new ArrayList<LibraryItem>();

    String status;
    Datastore datastore;

    //Creating a connection between MongoDB
    public void mongoConncetion(){

        final Morphia morphia = new Morphia();

        morphia.mapPackage("PackageName");
        MongoClient mongoClient = new MongoClient("127.0.0.1",27017);

        datastore = morphia.createDatastore(mongoClient, "LibrarySystem");
        MongoClientOptions.Builder options = MongoClientOptions.builder();
        options.socketKeepAlive(true);

    }



    @Override
    public String addItem(LibraryItem libraryItem) {

        mongoConncetion();
        if (libraryItem instanceof Book) {

            //Counting books
            int bookCount = (int) datastore.find(Book.class).countAll();

            if(bookCount<=100){
                datastore.save(libraryItem);
               // itemsList.add(libraryItem);

                bookCount++;

                status =("You can add " + (100-bookCount) + " books more");

            }else{
                status =("There is no space available");
            }

        }else if (libraryItem instanceof DVD) {

            //Counting dvds
            int dvdCount = (int) datastore.find(DVD.class).countAll();

            if(dvdCount<=50){
                datastore.save(libraryItem);
               // itemsList.add(libraryItem);
                dvdCount++;
                status =("You can add " + (100-dvdCount) + " dvds more");

            }else{
                status = ("There is no space available");
            }
        }
        return status;
    }


    @Override
    public String deleteItem(String ISBN) {
        mongoConncetion();

        Query<Book> bookQuery = datastore.createQuery(Book.class).field("_id").equal(ISBN);
        Query<DVD> dvdQuery = datastore.createQuery(DVD.class).field("_id").equal(ISBN);

        Book book=datastore.find(Book.class).field("_id").equal(ISBN).get();
        DVD dvd=datastore.find(DVD.class).field("_id").equal(ISBN).get();

        if(book!= null || dvd != null){
            if(book!= null){
                //datastore.findAndDelete(bookQuery);

                datastore.delete(bookQuery);
                status = ("Book is deleted");
            }else{
                datastore.delete(dvdQuery);
                status = ("Dvd is deleted");
            }

        }else{
            status = ("Not Found");
        }

        return status;
    }

    public List displayList() {
        mongoConncetion();

        List<Book> bookList = datastore.find(Book.class).asList();
        List<DVD> dvdList = datastore.find(DVD.class).asList();
        itemsList.addAll(bookList);
        itemsList.addAll(dvdList);
        return itemsList;
    }

    @Override
    public String borrowItem(String ISBN, Reader currentReader, DateTime dateTime) {
        String status;
        DateTime borrowedDate;
        DateTime availableDate;

        mongoConncetion();

        Book book=datastore.find(Book.class).field("_id").equal(ISBN).get();
        DVD dvd=datastore.find(DVD.class).field("_id").equal(ISBN).get();

        Query bookquery = datastore.createQuery(Book.class).filter("_id", ISBN);
        Query dvdquery = datastore.createQuery(DVD.class).filter("_id", ISBN);


        if(book!=null || dvd!= null){

            if(book!=null){
                if(book.getCurrentReader()==null){
                    book.setCurrentReader(currentReader);

                    //Finding the book query and update book collection with current readers
                    UpdateOperations<Book> updateOpe1 = datastore.createUpdateOperations(Book.class).set("currentReader", currentReader);

                    //Update the database
                    datastore.update(bookquery, updateOpe1);

                    book.setBorrowedDate(dateTime);
                    borrowedDate = book.getBorrowedDate();
                 /*   dueDate = dateTime.addDays(7);
                    System.out.println("Book due date"+dueDate.getFullDate());*/


                    UpdateOperations<Book> updateOpeDate = datastore.createUpdateOperations(Book.class).set("borrowedDate",borrowedDate);
                    datastore.update(bookquery, updateOpeDate);

                    status = ("Book is borrowed");

                }else {
                    availableDate = book.getBorrowedDate().checkDays(7);
                    status = ("Book is currently unavailable, available date should be "+availableDate.getFullDate());
                }



            }else{
                if(dvd.getCurrentReader()==null){
                    dvd.setCurrentReader(currentReader);

                    UpdateOperations<DVD> updateOpe2 = datastore.createUpdateOperations(DVD.class).set("currentReader", currentReader);
                    datastore.update(dvdquery, updateOpe2);

                    dvd.setBorrowedDate(dateTime);
                    borrowedDate = dvd.getBorrowedDate();
                   /* dueDate = dateTime.addDays(3);
                    System.out.println("DVD due date"+dueDate.getFullDate());*/

                    UpdateOperations<DVD> updateOpeDate = datastore.createUpdateOperations(DVD.class).set("borrowedDate", borrowedDate);
                    datastore.update(dvdquery, updateOpeDate);

                    status = ("Dvd is borrowed");

                }else{
                    availableDate = dvd.getBorrowedDate().checkDays(3);
                    status = ("DVD is currently unavailable, available date should be "+availableDate.getFullDate());
                }

            }
        }else{

            status=("Enter a valid ISBN");}

        return status;
    }

    @Override
    public String returnItem(String ISBN, DateTime dateTime) {
        DateTime dueDate;
        int returnHours,dueHours;
        double fee;
        DateTime dateTime1 = new DateTime();

        mongoConncetion();
        Book book=datastore.find(Book.class).field("_id").equal(ISBN).get();
        DVD dvd=datastore.find(DVD.class).field("_id").equal(ISBN).get();

        Query bookquery = datastore.createQuery(Book.class).filter("_id", ISBN);
        Query dvdquery = datastore.createQuery(DVD.class).filter("_id", ISBN);

        if(book!=null || dvd!= null){
            if(book !=null){
                if(book.getCurrentReader()!=null) {

                    //Unset current reader from the database
                    UpdateOperations<Book> updateOpe1 = datastore.createUpdateOperations(Book.class).unset("currentReader");
                    datastore.update(bookquery, updateOpe1);

                    dueDate = book.getBorrowedDate().checkDays(7);
                    System.out.println("Return date should be"+dueDate.getFullDate());

                    //Finding due date in hours
                    dueHours = dateTime1.convertToHours(dueDate);

                    //Find the return date in hours
                    returnHours = dateTime1.convertToHours(dateTime);

                    //Calculate the corresponding fee
                    fee = calculateFee(dueHours,returnHours);
                    status=("Fee is £"+fee);

                    //Unset the borrow date from the database
                    UpdateOperations<Book> updateOpeDate = datastore.createUpdateOperations(Book.class).unset("borrowedDate");
                    datastore.update(bookquery, updateOpeDate);
                }else {
                    status = ("Book has not been borrowed");
                }
            }
            else{
                if(dvd.getCurrentReader()!=null) {

                    //Unset current reader from the database
                    UpdateOperations<DVD> updateOpe1 = datastore.createUpdateOperations(DVD.class).unset("currentReader");
                    datastore.update(dvdquery, updateOpe1);

                    dueDate = book.getBorrowedDate().checkDays(3);
                    System.out.println("Return date should be"+dueDate.getFullDate());

                    //Finding due date in hours
                    dueHours = dateTime1.convertToHours(dueDate);

                    //Find the return date in hours
                    returnHours = dateTime1.convertToHours(dateTime);

                    //Calculate the corresponding fee
                    fee = calculateFee(dueHours,returnHours);
                    status=("Fee is £"+fee);
                    System.out.println("Fee is £"+fee);

                    //Unset the borrow date from the database
                    UpdateOperations<DVD> updateOpeDate = datastore.createUpdateOperations(DVD.class).unset("borrowedDate");
                    datastore.update(dvdquery, updateOpeDate);
                } else{
                    status = ("Dvd has not been borrowed");
                }
            }
        }else{
            status = ("Enter a valid ISBN");
        }

        return status;
    }


    @Override
    public List generateReport(DateTime dateTime) {
        DateTime dateTime1 = new DateTime();
        int reportDate,dueDate;
        double fee;
        ArrayList<LibraryItem> overdue = new ArrayList<>();
        HashMap<Double,LibraryItem> overdueMap = new HashMap<>();

        mongoConncetion();
        List<Book> bookList = datastore.find(Book.class).asList();
        List<DVD> dvdList = datastore.find(DVD.class).asList();
        itemsList.addAll(bookList);
        itemsList.addAll(dvdList);
        reportDate = dateTime1.convertToHours(dateTime);

        for(LibraryItem items : itemsList){
            if(!items.getCurrentReader().getName().equals("")){
                if(items instanceof Book) {
                    //items.getBorrowedDate().checkDays(7);

                    dueDate = dateTime1.convertToHours(items.getBorrowedDate().checkDays(7));
                    fee = calculateFee(dueDate,reportDate);
                    if(fee>0){
                        overdue.add(items);
                        System.out.println("overdue"+overdue);
                    }
                }else{
                    dueDate = dateTime1.convertToHours(items.getBorrowedDate().checkDays(3));
                    fee = calculateFee(dueDate,reportDate);
                    if(fee>0){
                        overdue.add(items);
                        System.out.println("overdue"+overdue);
                    }
                }
            }
        }




        return bookList;
    }



    public Reader findReader(String readerId){
        Reader readerDetails=null;
        mongoConncetion();
       // Query<Reader> readers = datastore.createQuery(Reader.class).field("_id").equal(readerId);
        List<Reader> readers = datastore.find(Reader.class).asList();
        for(int i=0; i<readers.size(); i++){
           if (readers.get(i).getID().equals(readerId)){
               readerDetails = readers.get(i);
           }

        }

        System.out.println("Reader is"+readers);
        return readerDetails;
    }

    public double calculateFee(int dueHours, int returnHours){
        int hourDifferent;
        double fee;

        if(dueHours<returnHours){
            hourDifferent = (returnHours - dueHours);
            if(hourDifferent < 3*24){
               fee = (hourDifferent * 0.2);
            }
            else{
                fee = (hourDifferent * 0.2) + (hourDifferent * 0.5);
            }

        }else {
            return fee=0;
        }
        return fee;
    }

}
