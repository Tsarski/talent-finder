package com.talentfinder.dto;

import com.talentfinder.model.Location;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class LocationDto {
    private int id;
    private String locationName;

    public static LocationDto fromEntity(Location location) {
        return new LocationDto(location.getId(), location.getLocationName());
    }
}
