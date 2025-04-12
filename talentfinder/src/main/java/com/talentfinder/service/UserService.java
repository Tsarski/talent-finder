package com.talentfinder.service;

import com.talentfinder.model.User;

import java.util.Optional;

public interface UserService {
    public Optional<User> getUserByUsernameAndPassword(String username, String password);
}
