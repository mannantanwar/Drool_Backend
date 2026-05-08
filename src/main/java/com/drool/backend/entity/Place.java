package com.drool.backend.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@Table(name = "places")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Place {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String cuisine;

    @Enumerated(EnumType.STRING)
    private PlaceType type;

    private String address;
    private String city;
    private String state;

    private Double latitude;
    private Double longitude;

    private Double rating;
    private Integer reviewsCount;

    @Enumerated(EnumType.STRING)
    private PriceRange priceRange;

    private Integer avgCostPerPerson;

    private String imageUrl;

    private String tags;

    private LocalDate openedDate;

    private Integer currentVisitors;

    private Double popularityScore;
    private Double trendingScore;
    private Double trustScore;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    @PrePersist
    public void beforeSave() {
        createdAt = LocalDateTime.now();
        updatedAt = LocalDateTime.now();

        if (rating == null) rating = 0.0;
        if (reviewsCount == null) reviewsCount = 0;
        if (currentVisitors == null) currentVisitors = 0;
        if (popularityScore == null) popularityScore = 0.0;
        if (trendingScore == null) trendingScore = 0.0;
        if (trustScore == null) trustScore = 0.0;
    }

    @PreUpdate
    public void beforeUpdate() {
        updatedAt = LocalDateTime.now();
    }
}
