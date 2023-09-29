package com.danylosavchak.PersonalLibrary.dao.impl;

import com.danylosavchak.PersonalLibrary.dao.Dao;
import com.danylosavchak.PersonalLibrary.model.Book;
import com.danylosavchak.PersonalLibrary.model.Impl.BookImpl;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;

@Repository("postgresqlRepository")
public class DaoImpl implements Dao {
    public Connection connection;

    private final Logger LOGGER = Logger.getLogger(DaoImpl.class.getName());

    public DaoImpl() {
        try {
            Class.forName("org.postgresql.Driver");
            Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/hastega");
            this.connection = connection;
        } catch (Exception e) {
            System.out.println("There is an error during connection to database: " + e);
        }
    }

    @Override
    public List<Book> getLibrary(Integer user_id) {
        String query = "SELECT * FROM book WHERE owner_id = " + user_id + ";";
        List<Book> library = new ArrayList<>();
        try {
            PreparedStatement stmt = connection.prepareStatement(query);
            stmt.execute();
            ResultSet resultSet = stmt.getResultSet();
            while (resultSet.next()) {

                Integer book_id = resultSet.getInt("book_id");
                String title = resultSet.getString("title");
                Integer author_id = resultSet.getInt("author_id");
                String isbn = resultSet.getString("isbn");
                Date additionDate = resultSet.getDate("date_of_addition");
                Date removalDate = resultSet.getDate("date_of_removal");
                String plot = resultSet.getString("plot");
                Integer numberOfFullReads = resultSet.getInt("num_of_full_reads");
                Integer owner_id = resultSet.getInt("owner_id");

                Book book = new BookImpl(book_id,
                        title,
                        author_id,
                        isbn,
                        additionDate,
                        removalDate,
                        plot,
                        numberOfFullReads,
                        owner_id);
                library.add(book);
            }
            stmt.close();
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "DaoImpl.getLibrary " + e.getMessage());
        }
        return library;
    }

    @Override
    public Optional<Integer> logIn(String firstName, String lastName, String email) {
        String query = "SELECT user_id FROM userr WHERE email = ? AND person_id = (SELECT person_id FROM person WHERE first_name = ? AND last_name = ?);";
        Optional<Integer> userId = Optional.empty();
        try {
            PreparedStatement stmt = connection.prepareStatement(query);
            stmt.setString(1,email);
            stmt.setString(2, firstName);
            stmt.setString(3, lastName);
            stmt.execute();
            ResultSet resultSet = stmt.getResultSet();
            if (resultSet.next()) {
                userId = Optional.of(resultSet.getInt("user_id"));
            }

        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "DaoImpl.logIn " + e.getMessage());
        }
        return userId;
    }

    @Override
    public Optional<Integer> getPersonId(String firstName, String lastName) {
        String query = "SELECT person_id FROM person WHERE first_name = ? AND last_name = ?;";
        Optional<Integer> personId = Optional.empty();
        try {
            PreparedStatement stmt = connection.prepareStatement(query);
            stmt.setString(1, firstName);
            stmt.setString(2,lastName);
            stmt.execute();
            ResultSet resultSet = stmt.getResultSet();
            if (resultSet.next()) {
                personId = Optional.of(resultSet.getInt("person_id"));
            }
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "DaoImpl.getPersonId " + e.getMessage());
        }
        return personId;
    }

    @Override
    public Boolean createUser(Integer personId, String email) {
        String query = "INSERT INTO userr(user_id, person_id, email)\n" +
                "VALUES ( (SELECT MAX(user_id) + 1 FROM userr), ?, ? );";
        int rowsCount = 0;
        try {
            PreparedStatement stmt = connection.prepareStatement(query);
            stmt.setInt(1, personId);
            stmt.setString(2, email);
            rowsCount = stmt.executeUpdate();
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "DaoImpl.createUser " + e.getMessage());
        }
        return  rowsCount > 0;
    }

    @Override
    public Boolean createPerson(String first_name, String last_name) {
        String query = "INSERT INTO person(person_id, first_name, last_name) VALUES ( (SELECT MAX(person_id) + 1 FROM person), ?, ? );";
        int rowsCount = 0;
        try {
            PreparedStatement stmt = connection.prepareStatement(query);
            stmt.setString(1, first_name);
            stmt.setString(2, last_name);
            rowsCount = stmt.executeUpdate();
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "DaoImpl.createPerson " + e.getMessage());
        }
        return  rowsCount > 0;
    }


    @Override
    public Boolean addBook(String title, Integer authorId, String isbn, Date additionDate, String plot, Integer numberOfFullReads, Integer owner_id) {
        String query = "INSERT INTO book(book_id, title, author_id, isbn, date_of_addition, date_of_removal, plot, num_of_full_reads, owner_id)\n" +
                "VALUES ((SELECT MAX(book_id) + 1 FROM book), ?, ?, ?, ?, ?, ?, ?, ?);";
        int rowsCount = 0;
        try {
            PreparedStatement stmt = connection.prepareStatement(query);
            stmt.setString(1, title);
            stmt.setInt(2, authorId);
            stmt.setString(3, isbn);
            stmt.setDate(4, additionDate);
            stmt.setDate(5, null);
            stmt.setString(6, plot);
            stmt.setInt(7, numberOfFullReads);
            stmt.setInt(8, owner_id);
            rowsCount = stmt.executeUpdate();
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "DaoImpl.addBook " + e.getMessage());
        }
        return rowsCount > 0;
    }

    @Override
    public Optional<Integer> getUserId(Integer personId, String email) {
        String query = "SELECT user_id FROM userr WHERE person_id = ? AND email = ?;";
        Optional<Integer> userId = Optional.empty();
        try {
            PreparedStatement stmt = connection.prepareStatement(query);
            stmt.setInt(1, personId);
            stmt.setString(2, email);
            stmt.execute();
            ResultSet resultSet = stmt.getResultSet();
            if (resultSet.next()) {
                userId = Optional.of(resultSet.getInt("user_id"));
            }
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "DaoImpl.getUserId " + e.getMessage());
        }
        return userId;
    }
}
