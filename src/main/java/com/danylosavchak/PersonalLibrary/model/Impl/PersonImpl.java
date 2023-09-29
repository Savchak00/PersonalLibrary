package com.danylosavchak.PersonalLibrary.model.Impl;

import com.danylosavchak.PersonalLibrary.model.Person;

public class PersonImpl implements Person {

    private final Integer person_id;
    private final String firstName;
    private final String lastName;

    public PersonImpl(Integer person_id, String firstName, String lastName) {
        this.person_id = person_id;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    @Override
    public Integer getPersonId() {
        return person_id;
    }

    @Override
    public String getFirstName() {
        return firstName;
    }

    @Override
    public String getLastName() {
        return lastName;
    }
}
