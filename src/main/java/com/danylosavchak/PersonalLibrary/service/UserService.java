package com.danylosavchak.PersonalLibrary.service;

import java.util.Optional;

public interface UserService {

    Optional<Integer> logIn(String firstName, String lastName, String email);

    Integer register(String firstName, String lastName, String email);
}
