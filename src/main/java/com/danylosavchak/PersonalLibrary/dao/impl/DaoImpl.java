package com.danylosavchak.PersonalLibrary.dao.impl;

import com.danylosavchak.PersonalLibrary.dao.Dao;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.logging.Logger;

@Repository("postgresqlRepository")
public class DaoImpl implements Dao {
    public Connection connection;

    private final Logger LOGGER = Logger.getLogger(DaoImpl.class.getName());

    public DaoImpl() {
        try {
            Class.forName("org.postgresql.Driver");
            Connection con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/hastega");
            this.connection = con;
        } catch (Exception e) {
            System.out.println("There is an error aaaaa " + e);
        }
    }
}
