package com.danylosavchak.PersonalLibrary.model;

import java.util.Optional;

public interface Person {

    Optional<Integer> getPersonId();

    void setPersonId(Optional<Integer> personId);

    String getFirstName();

    String getLastName();
}
