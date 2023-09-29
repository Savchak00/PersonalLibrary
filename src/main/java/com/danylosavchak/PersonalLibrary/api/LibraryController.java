package com.danylosavchak.PersonalLibrary.api;

import com.danylosavchak.PersonalLibrary.model.Book;
import com.danylosavchak.PersonalLibrary.service.LibraryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.time.LocalDate;
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
    public List<Book> getLibrary(@PathVariable("userId") Integer userId) {
        return this.service.getLibrary(userId);
    }

    @PostMapping("/addBook")
    public Boolean addBook(@RequestBody Map<String, String> json) {
        String title = json.get("title");
        String authorFirstName = json.get("authorFirstName");
        String authorLastName = json.get("authorLastName");
        String isbn = json.get("isbn");
        Date additionDate = Date.valueOf(LocalDate.parse(json.get("additionDate")));
        String plot = json.get("plot");
        Integer numberOfFullReads = new Integer(json.get("numberOfFullReads"));
        Integer owner_id = new Integer(json.get("userId"));
        return this.service.addBook(title, authorFirstName, authorLastName,
                isbn, additionDate, plot,numberOfFullReads, owner_id);
    }

    @PostMapping("/removeBook/{bookId}")
    public Boolean removeBook(@PathVariable("bookId") Integer bookId) {
        return this.service.removeBook(bookId);
    }
}
