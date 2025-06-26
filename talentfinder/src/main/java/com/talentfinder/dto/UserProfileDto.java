package com.talentfinder.dto;

import com.talentfinder.model.User;
import jakarta.persistence.Column;
import lombok.Data;

import java.time.LocalDate;

@Data
public class UserProfileDto {
    private long id;
    private String username;
    private String email;
    private String password;
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private LocalDate dateOfBirth;

    public static UserProfileDto fromEntity(User user) {
        UserProfileDto userProfileDto = new UserProfileDto();
        userProfileDto.setId(user.getId());
        userProfileDto.setUsername(user.getUsername());
        userProfileDto.setEmail(user.getEmail());
        userProfileDto.setPassword(user.getPassword());
        userProfileDto.setFirstName(user.getFirstName());
        userProfileDto.setLastName(user.getLastName());
        userProfileDto.setPhoneNumber(user.getPhoneNumber());
        userProfileDto.setDateOfBirth(user.getDateOfBirth());
        return userProfileDto;
    }

}
