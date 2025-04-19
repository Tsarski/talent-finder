package com.talentfinder.service.impl;

import com.talentfinder.dto.UserProfileDto;
import com.talentfinder.model.User;
import com.talentfinder.repository.UserRepository;
import com.talentfinder.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public Optional<User> getUserByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public ResponseEntity<UserProfileDto> saveUser(UserProfileDto userProfileDto) {
        User user = new User();
        user.setUsername(userProfileDto.getUsername());
        user.setPassword(passwordEncoder.encode(userProfileDto.getPassword()));
        User savedUser = userRepository.save(user);
        userProfileDto.setId(savedUser.getId());
        userProfileDto.setPassword("**");

        return ResponseEntity.ok(userProfileDto);
    }
}
