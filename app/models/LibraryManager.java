package models;

import java.util.List;

public interface LibraryManager {


    String addItem(LibraryItem libraryItem);
    String deleteItem(String ISBN);
    List displayList();
    String borrowItem(String ISBN, Reader currentReader, DateTime dateTime);
    String returnItem(String ISBN, DateTime dateTime);
    List generateReport(DateTime dateTime);

}
