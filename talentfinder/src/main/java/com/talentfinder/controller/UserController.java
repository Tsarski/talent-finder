package com.talentfinder.controller;

import com.talentfinder.dto.UserProfileDto;
import com.talentfinder.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/api/auth")
public class UserController {

    private UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public ResponseEntity<UserProfileDto> saveUser(@RequestBody UserProfileDto userProfileDto){
        if(userProfileDto == null){
            throw new IllegalArgumentException("User cannot be null");
        }
        if(userService.getUserByUsername(userProfileDto.getUsername()).isPresent()){
            throw new IllegalArgumentException("User already exists");
        }
        return userService.saveUser(userProfileDto);
    }

    @GetMapping("/users/get")
    public  ResponseEntity<UserProfileDto> getUserByUsername(@RequestParam String username){
        Optional<UserProfileDto> user = userService.getUserByUsername(username);

        return user.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

}
