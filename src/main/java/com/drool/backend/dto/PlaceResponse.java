package com.drool.backend.dto;

import com.drool.backend.entity.PlaceType;
import com.drool.backend.entity.PriceRange;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PlaceResponse {
    private Long id;
    private String name;
    private String cuisine;
    private PlaceType type;

    private String address;
    private String city;
    private String state;

    private Double latitude;
    private Double longitude;

    private Double rating;
    private Integer reviewsCount;

    private PriceRange priceRange;
    private Integer avgCostPerPerson;

    private String imageUrl;
    private String tags;

    private LocalDate openedDate;

    private Integer currentVisitors;

    private Double popularityScore;
    private Double trendingScore;
    private Double trustScore;
}
