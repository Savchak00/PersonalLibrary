package com.danylosavchak.PersonalLibrary.service.impl;

import com.danylosavchak.PersonalLibrary.dao.Dao;
import com.danylosavchak.PersonalLibrary.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service("userService")
public class UserServiceImpl implements UserService {

    private final Dao dao;

    @Autowired
    public UserServiceImpl(Dao dao) {
        this.dao = dao;
    }

    public Optional<Integer> logIn(String firstName, String lastName, String email) {
        return this.dao.logIn(firstName, lastName, email);
    }

    public Integer register(String firstName, String lastName, String email) {
        Optional<Integer> personId = this.dao.getPersonId(firstName, lastName);
        if (personId.isPresent()) {
            Boolean userCreated = this.dao.createUser(personId.get(), email);
            if (userCreated) {
                return this.dao.getUserId(personId.get(), email).orElse(-100);
            }
        }
        this.dao.createPerson(firstName, lastName);
        return this.register(firstName, lastName, email);
    }
}
