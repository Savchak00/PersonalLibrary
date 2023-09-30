package com.danylosavchak.PersonalLibrary.service;

import com.danylosavchak.PersonalLibrary.model.Book;

import java.util.Optional;

public interface BookService {

    Optional<Book> getBook(Integer bookId);

    Boolean editBook(Book book);
}
