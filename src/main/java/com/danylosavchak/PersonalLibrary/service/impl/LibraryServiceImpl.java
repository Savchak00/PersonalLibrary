package com.danylosavchak.PersonalLibrary.service.impl;

import com.danylosavchak.PersonalLibrary.dao.Dao;
import com.danylosavchak.PersonalLibrary.model.Book;
import com.danylosavchak.PersonalLibrary.service.LibraryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

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
    public Boolean addBook(String title, String authorFirstName, String authorLastName, String isbn,
                           Date additionDate, String plot,
                           Integer numberOfFullReads, Integer owner_id) {
        Optional<Integer> authorId = this.dao.getPersonId(authorFirstName, authorLastName);
        if (authorId.isPresent()) {
            return this.dao.addBook(title, authorId.get(), isbn,
                    additionDate, plot, numberOfFullReads, owner_id);
        }
        this.dao.createPerson(authorFirstName, authorLastName);
        return this.addBook(title, authorFirstName, authorLastName,
                    isbn, additionDate, plot,numberOfFullReads, owner_id);
    }

    @Override
    public Boolean removeBook(Integer bookId) {
        return this.dao.removeBook(bookId);
    }
}
