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
    public Optional<Book> getBook(Integer bookId) {
        return this.dao.getBook(bookId);
    }

    @Override
    public Boolean editBook(Book book) {
        book.getAuthor().setPersonId(this.dao.getPersonId(book.getAuthor().getFirstName().toLowerCase(), book.getAuthor().getLastName().toLowerCase()));
        if (book.getAuthor().getPersonId().isPresent()) {
            return this.dao.editBook(book);
        }
        this.dao.createPerson(book.getAuthor().getFirstName(), book.getAuthor().getLastName());
        return this.editBook(book);
    }
}
