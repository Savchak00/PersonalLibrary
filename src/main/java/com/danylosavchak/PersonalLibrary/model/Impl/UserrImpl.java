package com.danylosavchak.PersonalLibrary.model.Impl;

import com.danylosavchak.PersonalLibrary.model.Person;
import com.danylosavchak.PersonalLibrary.model.Userr;

public class UserrImpl implements Userr {

    private final Integer user_id;
    private final String email;
    private final Person person;

    public UserrImpl(Person person, String email, Integer user_id) {
        this.person = person;
        this.email = email;
        this.user_id = user_id;
    }

    @Override
    public Integer getPersonId() {
        return person.getPersonId();
    }

    @Override
    public String getFirstName() {
        return person.getFirstName();
    }

    @Override
    public String getLastName() {
        return person.getLastName();
    }

    @Override
    public String getEmail() {
        return email;
    }

    @Override
    public Integer getUserId() {
        return user_id;
    }
}
