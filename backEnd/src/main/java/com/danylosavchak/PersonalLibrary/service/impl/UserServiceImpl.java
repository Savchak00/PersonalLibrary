package com.danylosavchak.PersonalLibrary.service.impl;

import com.danylosavchak.PersonalLibrary.dao.Dao;
import com.danylosavchak.PersonalLibrary.model.Userr;
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

    public Optional<Integer> logIn(Userr user) {
        return this.dao.logIn(user.getFirstName().toLowerCase(), user.getLastName().toLowerCase(),
                user.getEmail().toLowerCase());
    }

    public Optional<Integer> register(Userr user) {
        user.setPersonId(this.dao.getPersonId(user.getFirstName().toLowerCase(),
                user.getLastName().toLowerCase()));
        if (user.getPersonId().isPresent()) {
            Boolean userCreated = this.dao.createUser(user.getPersonId().get(), user.getEmail());
            if (userCreated) {
                return this.dao.getUserId(user.getPersonId().get(), user.getEmail());
            }
        }
        this.dao.createPerson(user.getFirstName().toLowerCase(), user.getLastName().toLowerCase());
        return this.register(user);
    }
}
