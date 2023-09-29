package com.danylosavchak.PersonalLibrary.model.Impl;

import com.danylosavchak.PersonalLibrary.model.Book;
import com.danylosavchak.PersonalLibrary.model.Person;
import com.danylosavchak.PersonalLibrary.model.Userr;

import java.sql.Date;
import java.util.Optional;

public class BookImpl implements Book {

    private final Integer book_id;
    private String title;
    private Person author;
    private String isbn;
    private Date additionDate;
    private Optional<Date> removalDate;
    private String plot;
    private Integer numberOfFullReads;
    private final Userr owner;

    public BookImpl(Integer book_id,
                    String title,
                    Person author,
                    String isbn,
                    Date additionDate,
                    String plot,
                    Integer numberOfFullReads,
                    Userr owner) {
        this.book_id = book_id;
        this.title = title;
        this.author = author;
        this.isbn = isbn;
        this.additionDate = additionDate;
        this.plot = plot;
        this.numberOfFullReads = numberOfFullReads;
        this.owner = owner;
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
    public Person getAuthor() {
        return this.author;
    }

    @Override
    public void setAuthor(Person author) {
        this.author = author;
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
    public Userr getOwner() {
        return this.owner;
    }
}
