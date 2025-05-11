package com.talentfinder.service.impl;

import com.talentfinder.dto.UserProfileDto;
import com.talentfinder.model.User;
import com.talentfinder.repository.UserRepository;
import com.talentfinder.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
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
        user.setEmail(userProfileDto.getEmail());
        user.setFirstName(userProfileDto.getFirstName());
        user.setLastName(userProfileDto.getLastName());
        user.setDateOfBirth(userProfileDto.getDateOfBirth());
        user.setPhoneNumber(userProfileDto.getPhoneNumber());
        user.setCreatedAt(LocalDateTime.now());
        user.setLastLogin(LocalDateTime.now());
        user.setActive(true);
        User savedUser = userRepository.save(user);
        userProfileDto.setId(savedUser.getId());
        userProfileDto.setPassword("**");

        return ResponseEntity.ok(userProfileDto);
    }
}
