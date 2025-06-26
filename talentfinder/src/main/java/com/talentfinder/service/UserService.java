package com.talentfinder.service;

import com.talentfinder.dto.UserProfileDto;
import org.springframework.http.ResponseEntity;

import java.util.Optional;

public interface UserService {
    public Optional<UserProfileDto> getUserByUsername(String username);

    public ResponseEntity<UserProfileDto> saveUser(UserProfileDto userProfileDto);
}
