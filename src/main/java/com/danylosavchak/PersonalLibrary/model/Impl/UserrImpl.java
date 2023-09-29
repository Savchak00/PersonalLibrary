package com.danylosavchak.PersonalLibrary.model.Impl;

import com.danylosavchak.PersonalLibrary.model.Userr;

public class UserrImpl extends PersonImpl implements Userr {

    private final Integer user_id;
    private final String email;

    public UserrImpl(String firstName, String lastName, Integer personId, String email, Integer user_id) {
        super(personId, firstName, lastName);
        this.email = email;
        this.user_id = user_id;
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
