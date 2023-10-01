package com.danylosavchak.PersonalLibrary.model.Impl;

import com.danylosavchak.PersonalLibrary.model.Book;
import com.danylosavchak.PersonalLibrary.model.Person;
import com.danylosavchak.PersonalLibrary.model.Userr;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.sql.Date;
import java.util.Optional;

public class BookImpl implements Book {

    @JsonProperty("bookId")
    private Integer bookId;
    @JsonProperty("title")
    private String title;
    @JsonProperty("author")
    private PersonImpl author;
    @JsonProperty("isbn")
    private String isbn;
    @JsonProperty("additionDate")
    private Date additionDate;
    @JsonProperty("removalDate")
    private Optional<Date> removalDate;
    @JsonProperty("plot")
    private String plot;
    @JsonProperty("numberOfFullReads")
    private Integer numberOfFullReads;
    @JsonProperty("owner")
    private UserrImpl owner;

    public BookImpl(Integer bookId,
                    String title,
                    PersonImpl author,
                    String isbn,
                    Date additionDate,
                    Date removalDate,
                    String plot,
                    Integer numberOfFullReads,
                    UserrImpl owner) {
        this.bookId = bookId;
        this.title = title;
        this.author = author;
        this.isbn = isbn;
        this.additionDate = additionDate;
        this.removalDate = Optional.ofNullable(removalDate);
        this.plot = plot;
        this.numberOfFullReads = numberOfFullReads;
        this.owner = owner;
    }

    public BookImpl() {};


    @Override
    public Integer getBookId() {
        return this.bookId;
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
    public void setAuthor(PersonImpl author) {
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
