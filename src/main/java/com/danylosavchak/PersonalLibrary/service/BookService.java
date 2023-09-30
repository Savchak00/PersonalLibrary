package com.danylosavchak.PersonalLibrary.service;

import com.danylosavchak.PersonalLibrary.model.Book;

public interface BookService {

    Book getBook(Integer bookId);

    Boolean editBook(Book book);
}
