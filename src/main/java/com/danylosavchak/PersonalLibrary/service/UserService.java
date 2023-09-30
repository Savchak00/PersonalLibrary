package com.danylosavchak.PersonalLibrary.service;

import com.danylosavchak.PersonalLibrary.model.Userr;

import java.util.Optional;

public interface UserService {

    Optional<Integer> logIn(Userr user);

    Optional<Integer> register(Userr user);
}
