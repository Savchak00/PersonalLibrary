package com.danylosavchak.PersonalLibrary.dao;

import com.danylosavchak.PersonalLibrary.model.Book;

import java.util.List;
import java.util.Optional;

public interface Dao {

    List<Book> getLibrary(Integer user_id);

    Optional<Integer> logIn(String firstName, String lastName, String email);

    Optional<Integer> getPersonId(String firstName, String lastName);

    Boolean createUser(Integer integer, String email);

    Optional<Integer> getUserId(Integer integer, String email);

    Boolean createPerson(String firstName, String lastName);

    Boolean addBook(Book book);

    Boolean removeBook(Integer bookId);

    Optional<Book> getBook(Integer bookId);

    Boolean editBook(Book book);

}
