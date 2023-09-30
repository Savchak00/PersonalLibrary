package com.danylosavchak.PersonalLibrary.api;

import com.danylosavchak.PersonalLibrary.model.Book;
import com.danylosavchak.PersonalLibrary.response.ResponseHandler;
import com.danylosavchak.PersonalLibrary.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
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
    public ResponseEntity<Object> getBook(@PathVariable("bookId") Integer bookId) {
        Book book =  this.service.getBook(bookId);
        Map<String, Book> responseMap = new HashMap<>();
        responseMap.put("book", null);
        if (book != null) {
            responseMap.put("book", book);
            return ResponseHandler.responseBuilder("Book is returned.", HttpStatus.OK, responseMap);
        }
        return ResponseHandler.responseBuilder("Book is not returned.",
                HttpStatus.INTERNAL_SERVER_ERROR, responseMap);
    }

    @PutMapping("/edit")
    public ResponseEntity<Object> editBook(@RequestBody Map<String, String> json) {
        Integer bookId = new Integer(json.get("bookId"));
        String title = json.get("title");
        String authorFirstName = json.get("authorFirstName").toLowerCase();
        String authorLastName = json.get("authorLastName").toLowerCase();
        String isbn = json.get("isbn");
        String plot = json.get("plot");
        Integer numberOfFullReads = new Integer(json.get("numberOfFullReads"));
        Boolean isBookEdited =  this.service.editBook(bookId, title, authorFirstName, authorLastName, isbn, plot,
                numberOfFullReads);
        Map<String, Boolean> responseMap = new HashMap<>();
        responseMap.put("isBookEdited", false);
        if (isBookEdited) {
            responseMap.put("isBookEdited", true);
            return ResponseHandler.responseBuilder("Book is edited successfully.", HttpStatus.OK, responseMap);
        }
        return ResponseHandler.responseBuilder("Book is not edited successfully.",
                HttpStatus.INTERNAL_SERVER_ERROR, responseMap);
    }
}
