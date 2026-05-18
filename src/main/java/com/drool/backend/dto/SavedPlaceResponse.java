package com.drool.backend.dto;

import com.drool.backend.entity.PlaceType;
import com.drool.backend.entity.PriceRange;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
public class SavedPlaceResponse {

    private Long savedId;
    private Long placeId;

    private String name;
    private PlaceType type;
    private String cuisine;

    private String address;
    private String city;
    private String state;

    private Double rating;
    private PriceRange priceRange;
    private Integer avgCostPerPerson;

    private String imageUrl;
    private String tags;

    private LocalDateTime savedDate;
}