package com.danylosavchak.PersonalLibrary.model.Impl;

import com.danylosavchak.PersonalLibrary.model.Person;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Optional;

public class PersonImpl implements Person {

    @JsonProperty("personId")
    private Optional<Integer> personId;
    @JsonProperty("firstName")
    private String firstName;
    @JsonProperty("lastName")
    private String lastName;

    public PersonImpl(Integer personId, String firstName, String lastName) {
        this.personId = Optional.ofNullable(personId);
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public PersonImpl(String firstName, String lastName) {
        this(null, firstName, lastName);
    }

    public PersonImpl() {
        this(null,null,null);
    }

    @Override
    public Optional<Integer> getPersonId() {
        return personId;
    }

    @Override
    public void setPersonId(Optional<Integer> personId) {
        if (!this.personId.isPresent()) {
            this.personId = personId;
        }
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
