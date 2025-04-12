package com.talentfinder.service.impl;

import com.talentfinder.model.User;
import com.talentfinder.repository.UserRepository;
import com.talentfinder.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public Optional<User> getUserByUsernameAndPassword(String username, String password) {
        return null;
    }
}
