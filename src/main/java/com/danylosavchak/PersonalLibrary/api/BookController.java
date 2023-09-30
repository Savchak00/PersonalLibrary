package com.danylosavchak.PersonalLibrary.api;

import com.danylosavchak.PersonalLibrary.model.Book;
import com.danylosavchak.PersonalLibrary.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController("bookController")
@RequestMapping("api/book")
public class BookController {

    private final BookService service;

    @Autowired
    public BookController(BookService service) {
        this.service = service;
    }

    @GetMapping("{bookId}")
    public Book getBook(@PathVariable("bookId") Integer bookId) {
        return this.service.getBook(bookId);
    }

    @PutMapping("/edit")
    public Boolean editBook(@RequestBody Map<String, String> json) {
        Integer bookId = new Integer(json.get("bookId"));
        String title = json.get("title");
        String authorFirstName = json.get("authorFirstName").toLowerCase();
        String authorLastName = json.get("authorLastName").toLowerCase();
        String isbn = json.get("isbn");
        String plot = json.get("plot");
        Integer numberOfFullReads = new Integer(json.get("numberOfFullReads"));
        return this.service.editBook(bookId, title, authorFirstName, authorLastName, isbn, plot, numberOfFullReads);
    }
}
