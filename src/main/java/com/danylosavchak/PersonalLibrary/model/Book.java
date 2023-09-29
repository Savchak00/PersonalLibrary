package com.danylosavchak.PersonalLibrary.model;

import java.sql.Date;

public interface Book {

    Integer getBookId();

    String getTitle();

    void setTitle(String title);

    Person getAuthor();

    void setAuthor(Person author);

    String getISBN();

    void setISBN(String isbn);

    Date getAdditionDate();

    void setAdditionDate(Date date);

    Date getRemovalDate();

    void setRemovalDate(Date date);

    String getPlot();

    void setPlot(String plot);

    Integer getNumberOfFullReads();

    void setNumberOfFullReads(Integer numberOfFullReads);

    Userr getOwner();
}
