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
}
