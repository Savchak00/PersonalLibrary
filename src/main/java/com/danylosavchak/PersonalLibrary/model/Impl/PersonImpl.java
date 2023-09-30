package com.danylosavchak.PersonalLibrary.model.Impl;

import com.danylosavchak.PersonalLibrary.model.Person;
import com.fasterxml.jackson.annotation.JsonProperty;

public class PersonImpl implements Person {

    @JsonProperty("personId")
    private Integer personId;
    @JsonProperty("firstName")
    private String firstName;
    @JsonProperty("lastName")
    private String lastName;

    public PersonImpl(Integer personId, String firstName, String lastName) {
        this.personId = personId;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public PersonImpl(String firstName, String lastName) {
        this(null, firstName, lastName);
    }

    public PersonImpl() {

    }

    @Override
    public Integer getPersonId() {
        return personId;
    }

    @Override
    public void setPersonId(Integer personId) {
        if (this.personId == null) {
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
