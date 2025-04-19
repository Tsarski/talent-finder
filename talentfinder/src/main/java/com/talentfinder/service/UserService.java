package com.talentfinder.service;

import com.talentfinder.dto.UserProfileDto;
import com.talentfinder.model.User;
import org.springframework.http.ResponseEntity;

import java.util.Optional;

public interface UserService {
    public Optional<User> getUserByUsername(String username);

    public ResponseEntity<UserProfileDto> saveUser(UserProfileDto userProfileDto);
}
