package com.danylosavchak.PersonalLibrary.service.impl;

import com.danylosavchak.PersonalLibrary.dao.Dao;
import com.danylosavchak.PersonalLibrary.model.Book;
import com.danylosavchak.PersonalLibrary.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service("bookService")
public class BookServiceImpl implements BookService {

    private final Dao dao;

    @Autowired
    public BookServiceImpl(Dao dao) {
        this.dao = dao;
    }

    @Override
    public Book getBook(Integer bookId) {
        return this.dao.getBook(bookId).orElse(null);
    }

    @Override
    public Boolean editBook(Integer bookId, String title, String authorFirstName, String authorLastName,
                         String isbn, String plot, Integer numberOfFullReads) {
        Optional<Integer> authorId = this.dao.getPersonId(authorFirstName, authorLastName);
        if (authorId.isPresent()) {
            return this.dao.editBook(bookId, title, authorId.get(),
                    isbn, plot, numberOfFullReads);
        }
        this.dao.createPerson(authorFirstName, authorLastName);
        return this.editBook(bookId, title, authorFirstName, authorLastName, isbn, plot, numberOfFullReads);
    }
}
