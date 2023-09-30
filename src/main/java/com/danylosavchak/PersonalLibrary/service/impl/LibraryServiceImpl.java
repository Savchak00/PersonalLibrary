package com.danylosavchak.PersonalLibrary.service.impl;

import com.danylosavchak.PersonalLibrary.dao.Dao;
import com.danylosavchak.PersonalLibrary.model.Book;
import com.danylosavchak.PersonalLibrary.service.LibraryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("libraryService")
public class LibraryServiceImpl implements LibraryService {

    private final Dao dao;

    @Autowired
    public LibraryServiceImpl(Dao dao) {
        this.dao = dao;
    }

    public List<Book> getLibrary(Integer userId) {
        return this.dao.getLibrary(userId);
    }

    @Override
    public Boolean addBook(Book book) {
        book.getAuthor().setPersonId(
                this.dao.getPersonId(book.getAuthor().getFirstName().toLowerCase(), book.getAuthor().getLastName().toLowerCase()));

        if (book.getAuthor().getPersonId().isPresent()) {
            return this.dao.addBook(book);
        }
        this.dao.createPerson(book.getAuthor().getFirstName().toLowerCase(), book.getAuthor().getLastName().toLowerCase());
        return this.addBook(book);
    }

    @Override
    public Boolean removeBook(Integer bookId) {
        return this.dao.removeBook(bookId);
    }
}
