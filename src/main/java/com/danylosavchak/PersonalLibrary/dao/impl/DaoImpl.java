package com.danylosavchak.PersonalLibrary.dao.impl;

import com.danylosavchak.PersonalLibrary.dao.Dao;
import com.danylosavchak.PersonalLibrary.model.Book;
import com.danylosavchak.PersonalLibrary.model.Impl.BookImpl;
import com.danylosavchak.PersonalLibrary.model.Impl.PersonImpl;
import com.danylosavchak.PersonalLibrary.model.Impl.UserrImpl;
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
            this.connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/hastega");
        } catch (Exception e) {
            System.out.println("There is an error during connection to database: " + e);
        }
    }

    @Override
    public List<Book> getLibrary(Integer user_id) {
        String query = "SELECT b.book_id as book_id, b.title as title, b.isbn as isbn, b.date_of_addition as date_of_addition, b.plot as plot, b.num_of_full_reads as num_of_full_reads,\n" +
                "       b.author_id as author_id, p.first_name as author_first_name, p.last_name as author_last_name,\n" +
                "       u.person_id as user_person_id, b.owner_id as user_id, up.first_name as user_first_name,\n" +
                "       up.last_name as user_last_name, u.email as email\n" +
                "FROM book b\n" +
                "    INNER JOIN userr u ON u.user_id = b.owner_id\n" +
                "    INNER JOIN person p on b.author_id = p.person_id\n" +
                "    INNER JOIN person up on u.person_id = up.person_id\n" +
                "WHERE owner_id = ? AND date_of_removal IS NULL;";
        List<Book> library = new ArrayList<>();
        try {
            PreparedStatement stmt = connection.prepareStatement(query);
            stmt.setInt(1, user_id);
            stmt.execute();
            ResultSet resultSet = stmt.getResultSet();
            while (resultSet.next()) {
                Book book = this.formABook(resultSet);
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
    public Boolean addBook(Book book) {
        String query = "INSERT INTO book(book_id, title, author_id, isbn, date_of_addition, date_of_removal, plot, num_of_full_reads, owner_id)\n" +
                "VALUES ((SELECT MAX(book_id) + 1 FROM book), ?, ?, ?, ?, ?, ?, ?, ?);";
        int rowsCount = 0;
        try {
            PreparedStatement stmt = connection.prepareStatement(query);
            stmt.setString(1, book.getTitle());
            stmt.setInt(2, book.getAuthor().getPersonId().get());
            stmt.setString(3, book.getISBN());
            stmt.setDate(4, book.getAdditionDate());
            stmt.setDate(5, null);
            stmt.setString(6, book.getPlot());
            stmt.setInt(7, book.getNumberOfFullReads());
            stmt.setInt(8, book.getOwner().getUserId());
            rowsCount = stmt.executeUpdate();
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "DaoImpl.addBook " + e.getMessage());
        }
        return rowsCount > 0;
    }

    @Override
    public Boolean removeBook(Integer bookId) {
        String query = "UPDATE book SET date_of_removal = cast(now() as date) WHERE book_id = ?;";
        int rowsCount = 0;
        try {
            PreparedStatement stmt = connection.prepareStatement(query);
            stmt.setInt(1, bookId);
            rowsCount = stmt.executeUpdate();
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "DaoImpl.removeBook " + e.getMessage());
        }
        return rowsCount > 0;
    }

    @Override
    public Optional<Book> getBook(Integer bookId) {
        String query = "SELECT b.book_id as book_id, b.title as title, b.isbn as isbn, b.date_of_addition as date_of_addition, b.plot as plot, b.num_of_full_reads as num_of_full_reads,\n" +
                "       b.author_id as author_id, p.first_name as author_first_name, p.last_name as author_last_name,\n" +
                "       u.person_id as user_person_id, b.owner_id as user_id, up.first_name as user_first_name,\n" +
                "       up.last_name as user_last_name, u.email as email\n" +
                "FROM book b\n" +
                "    INNER JOIN userr u ON u.user_id = b.owner_id\n" +
                "    INNER JOIN person p on b.author_id = p.person_id\n" +
                "    INNER JOIN person up on u.person_id = up.person_id\n" +
                "WHERE book_id = ? AND date_of_removal IS NULL;";
        Optional<Book> book = Optional.empty();
        try {
            PreparedStatement stmt = connection.prepareStatement(query);
            stmt.setInt(1, bookId);
            stmt.execute();
            ResultSet resultSet = stmt.getResultSet();
            if (resultSet.next()) {
                book = Optional.of(this.formABook(resultSet));
            }
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "DaoImpl.getBook " + e.getMessage());
        }
        return book;
    }

    @Override
    public Boolean editBook(Book book) {
        String query = "UPDATE book \n" +
                "SET title = ?, isbn = ?, plot = ?, num_of_full_reads = ?, author_id = ? \n" +
                "WHERE book_id = ?;";
        int rowsCount = 0;
        try {
            PreparedStatement stmt = connection.prepareStatement(query);
            stmt.setString(1, book.getTitle());
            stmt.setString(2, book.getISBN());
            stmt.setString(3, book.getPlot());
            stmt.setInt(4, book.getNumberOfFullReads());
            stmt.setInt(5, book.getAuthor().getPersonId().get());
            stmt.setInt(6, book.getBookId());
            rowsCount = stmt.executeUpdate();
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "DaoImpl.editBook " + e.getMessage());
        }
        return rowsCount > 0;
    }

    private Book formABook(ResultSet resultSet) throws SQLException {
        Integer bookId = resultSet.getInt("book_id");
        String title = resultSet.getString("title");
        Integer authorId = resultSet.getInt("author_id");
        String authorFirstName = resultSet.getString("author_first_name");
        String authorLastName = resultSet.getString("author_last_name");
        String isbn = resultSet.getString("isbn");
        Date additionDate = resultSet.getDate("date_of_addition");
        String plot = resultSet.getString("plot");
        Integer numberOfFullReads = resultSet.getInt("num_of_full_reads");
        Integer ownerId = resultSet.getInt("user_id");
        String ownerEmail = resultSet.getString("email");
        String ownerFirstName = resultSet.getString("user_first_name");
        String ownerLastName = resultSet.getString("user_last_name");
        Integer ownerPersonId = resultSet.getInt("user_person_id");

        PersonImpl author = new PersonImpl(authorId, authorFirstName, authorLastName);
        PersonImpl ownerPerson = new PersonImpl(ownerPersonId, ownerFirstName, ownerLastName);
        UserrImpl owner = new UserrImpl(ownerPerson, ownerEmail, ownerId);
        Book book = new BookImpl(bookId,
                title,
                author,
                isbn,
                additionDate,
                null,
                plot,
                numberOfFullReads,
                owner);
        return book;
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
