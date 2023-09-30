package com.danylosavchak.PersonalLibrary.service;

import com.danylosavchak.PersonalLibrary.model.Book;

import java.util.List;

public interface LibraryService {

    List<Book> getLibrary(Integer userId);

    Boolean addBook(Book book);

    Boolean removeBook(Integer bookId);
}
