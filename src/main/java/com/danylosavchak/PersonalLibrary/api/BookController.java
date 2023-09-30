package com.danylosavchak.PersonalLibrary.api;

import com.danylosavchak.PersonalLibrary.model.Book;
import com.danylosavchak.PersonalLibrary.model.Impl.BookImpl;
import com.danylosavchak.PersonalLibrary.response.ResponseHandler;
import com.danylosavchak.PersonalLibrary.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

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
        Optional<Book> bookOptional =  this.service.getBook(bookId);
        Map<String, Book> responseMap = new HashMap<>();
        responseMap.put("book", null);
        if (bookOptional.isPresent()) {
            responseMap.put("book", bookOptional.get());
            return ResponseHandler.responseBuilder("Book is returned.", HttpStatus.OK, responseMap);
        }
        return ResponseHandler.responseBuilder("Book is not returned.",
                HttpStatus.INTERNAL_SERVER_ERROR, responseMap);
    }

    @PutMapping("/edit")
    public ResponseEntity<Object> editBook(@RequestBody BookImpl book) {
        Boolean isBookEdited =  this.service.editBook(book);
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
