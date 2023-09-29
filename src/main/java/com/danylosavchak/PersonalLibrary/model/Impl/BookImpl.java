package com.danylosavchak.PersonalLibrary.model.Impl;

import com.danylosavchak.PersonalLibrary.model.Book;
import com.danylosavchak.PersonalLibrary.model.Person;
import com.danylosavchak.PersonalLibrary.model.Userr;

import java.sql.Date;
import java.util.Optional;

public class BookImpl implements Book {

    private final Integer book_id;
    private String title;
    private Integer author_id;
    private String isbn;
    private Date additionDate;
    private Optional<Date> removalDate;
    private String plot;
    private Integer numberOfFullReads;
    private final Integer owner_id;

    public BookImpl(Integer book_id,
                    String title,
                    Integer author_id,
                    String isbn,
                    Date additionDate,
                    Date removalDate,
                    String plot,
                    Integer numberOfFullReads,
                    Integer owner_id) {
        this.book_id = book_id;
        this.title = title;
        this.author_id = author_id;
        this.isbn = isbn;
        this.additionDate = additionDate;
        this.removalDate = Optional.ofNullable(removalDate);
        this.plot = plot;
        this.numberOfFullReads = numberOfFullReads;
        this.owner_id = owner_id;
    }


    @Override
    public Integer getBookId() {
        return this.book_id;
    }

    @Override
    public String getTitle() {
        return this.title;
    }

    @Override
    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public Integer getAuthorId() {
        return this.author_id;
    }

    @Override
    public void setAuthorId(Integer author_id) {
        this.author_id = author_id;
    }

    @Override
    public String getISBN() {
        return this.isbn;
    }

    @Override
    public void setISBN(String isbn) {
        this.isbn = isbn;
    }

    @Override
    public Date getAdditionDate() {
        return this.additionDate;
    }

    @Override
    public void setAdditionDate(Date date) {
        this.additionDate = date;
    }

    @Override
    public Date getRemovalDate() {
        return this.removalDate.orElse(null);
    }

    @Override
    public void setRemovalDate(Date date) {
        this.removalDate = Optional.ofNullable(date);
    }

    @Override
    public String getPlot() {
        return this.plot;
    }

    @Override
    public void setPlot(String plot) {
        this.plot = plot;
    }

    @Override
    public Integer getNumberOfFullReads() {
        return this.numberOfFullReads;
    }

    @Override
    public void setNumberOfFullReads(Integer numberOfFullReads) {
        this.numberOfFullReads = numberOfFullReads;
    }

    @Override
    public Integer getOwnerId() {
        return this.owner_id;
    }
}
