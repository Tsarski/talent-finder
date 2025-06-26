package com.talentfinder.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class CreateBusinessServiceDto {
    private String title;
    private String description;
    private double price;
    private String serviceCategory;
    private String location;
    private String username;
    private List<MultipartFile> images;
    private List<String> videoLinks;
}
