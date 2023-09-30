package com.danylosavchak.PersonalLibrary.api;

import com.danylosavchak.PersonalLibrary.model.Book;
import com.danylosavchak.PersonalLibrary.response.ResponseHandler;
import com.danylosavchak.PersonalLibrary.service.LibraryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController("libraryController")
@RequestMapping("api/library")
public class LibraryController {

    private final LibraryService service;

    @Autowired
    public LibraryController(@Qualifier("libraryService") LibraryService service) {
        this.service = service;
    }

    @GetMapping("{userId}")
    public ResponseEntity<Object> getLibrary(@PathVariable("userId") Integer userId) {
        Map<String, List<Book>> responseMap = new HashMap<>();
        responseMap.put("library", this.service.getLibrary(userId));
        return ResponseHandler.responseBuilder("Library for user: " + userId + " is returned.",
                HttpStatus.OK, responseMap);
    }

    @PostMapping("/addBook")
    public ResponseEntity<Object> addBook(@RequestBody Map<String, String> json) {
        String title = json.get("title");
        String authorFirstName = json.get("authorFirstName");
        String authorLastName = json.get("authorLastName");
        String isbn = json.get("isbn");
        Date additionDate = Date.valueOf(LocalDate.parse(json.get("additionDate")));
        String plot = json.get("plot");
        Integer numberOfFullReads = new Integer(json.get("numberOfFullReads"));
        Integer owner_id = new Integer(json.get("userId"));
        Boolean isBookAdded = this.service.addBook(title, authorFirstName, authorLastName,
                isbn, additionDate, plot,numberOfFullReads, owner_id);
        Map<String, Boolean> responseMap = new HashMap<>();
        responseMap.put("isBookAdded", isBookAdded);
        if (isBookAdded) {
            return ResponseHandler.responseBuilder("Book is added to the library.",
                    HttpStatus.OK, responseMap);
        }
        return  ResponseHandler.responseBuilder("Book is not added to the library.",
                HttpStatus.INTERNAL_SERVER_ERROR, responseMap);
    }

    @PutMapping("/removeBook/{bookId}")
    public ResponseEntity<Object> removeBook(@PathVariable("bookId") Integer bookId) {
        Boolean isBookRemoved =  this.service.removeBook(bookId);
        Map<String, Boolean> responseMap = new HashMap<>();
        responseMap.put("isBookAdded", isBookRemoved);
        if (isBookRemoved) {
            return ResponseHandler.responseBuilder("Book is removed from the library.",
                    HttpStatus.OK, responseMap);
        }
        return  ResponseHandler.responseBuilder("Book is not removed from the library.",
                HttpStatus.INTERNAL_SERVER_ERROR, responseMap);
    }
}
