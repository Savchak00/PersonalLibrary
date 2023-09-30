package com.danylosavchak.PersonalLibrary.model.Impl;

import com.danylosavchak.PersonalLibrary.model.Person;
import com.danylosavchak.PersonalLibrary.model.Userr;
import com.fasterxml.jackson.annotation.JsonProperty;

public class UserrImpl extends PersonImpl implements Userr {

    @JsonProperty("userId")
    private Integer userId;
    @JsonProperty("email")
    private String email;

    public UserrImpl(Person person, String email, Integer userId) {
        super(person.getPersonId(), person.getFirstName(), person.getLastName());
        this.email = email;
        this.userId = userId;
    }

    public UserrImpl(String firstName, String lastName, String email) {
        this(new PersonImpl(firstName, lastName), email,null);
    }

    public UserrImpl() {
        super();
    };

    @Override
    public void setUserId(Integer userId) {
        if (this.userId == null) {
            this.userId = userId;
        }
    }

    @Override
    public String getEmail() {
        return email;
    }

    @Override
    public Integer getUserId() {
        return userId;
    }
}
