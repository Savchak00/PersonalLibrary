package com.danylosavchak.PersonalLibrary.api;

import com.danylosavchak.PersonalLibrary.model.Book;
import com.danylosavchak.PersonalLibrary.service.LibraryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController("libraryController")
@RequestMapping("api/library")
public class LibraryController {

    private final LibraryService service;

    @Autowired
    public LibraryController(@Qualifier("libraryService") LibraryService service) {
        this.service = service;
    }

    @GetMapping
    public List<Book> getLibrary(Integer userId) {
        return this.service.getLibrary(userId);
    }
}
