package com.danylosavchak.PersonalLibrary.service;

import com.danylosavchak.PersonalLibrary.model.Book;

import java.sql.Date;
import java.util.List;

public interface LibraryService {

    List<Book> getLibrary(Integer userId);

    Boolean addBook(String title, String authorFirstName, String authorLastName,
                    String isbn, Date additionDate, String plot,
                    Integer numberOfFullReads, Integer owner_id);

    Boolean removeBook(Integer bookId);
}
