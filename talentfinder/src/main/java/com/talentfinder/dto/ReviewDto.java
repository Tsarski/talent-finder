package com.talentfinder.dto;

import com.talentfinder.model.Review;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ReviewDto {
    private long id;
    private int rating;
    private String description;
    private String author;

    public static ReviewDto fromEntity(Review reviewEntity){
        ReviewDto reviewDto = new ReviewDto();
        reviewDto.setId(reviewEntity.getId());
        reviewDto.setRating(reviewEntity.getRating());
        reviewDto.setDescription(reviewEntity.getDescription());
        reviewDto.setAuthor(reviewEntity.getUser().getUsername());
        return reviewDto;
    }
}
