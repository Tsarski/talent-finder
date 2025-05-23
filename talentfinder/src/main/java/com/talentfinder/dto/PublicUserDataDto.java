package com.talentfinder.dto;

import com.talentfinder.model.User;
import lombok.Data;

import java.time.LocalDate;

@Data
public class PublicUserDataDto {
    private String username;
    private String email;
    private String firstName;
    private String lastName;
    private LocalDate dateOfBirth;

    public PublicUserDataDto() {};

    public PublicUserDataDto(User user) {
        this.username = user.getUsername();
        this.email = user.getEmail();
        this.firstName = user.getFirstName();
        this.lastName = user.getLastName();
        this.dateOfBirth = user.getDateOfBirth();
    }
}
