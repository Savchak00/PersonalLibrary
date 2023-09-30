package com.danylosavchak.PersonalLibrary.service;

import com.danylosavchak.PersonalLibrary.model.Book;

public interface BookService {

    Book getBook(Integer bookId);

    Boolean editBook(Integer bookId, String title, String authorFirstName, String authorLastName, String isbn, String plot,
                  Integer numberOfFullReads);
}
