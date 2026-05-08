package com.drool.backend.repository;

import com.drool.backend.entity.Place;
import com.drool.backend.entity.PlaceType;
import com.drool.backend.entity.PriceRange;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface PlaceRepository extends JpaRepository<Place, Long> {
    List<Place> findByCityIgnoreCase(String city);

    List<Place> findByType(PlaceType type);

    List<Place> findByNameContainingIgnoreCase(String name);

    List<Place> findByCityIgnoreCaseAndType(String city, PlaceType type);

    List<Place> findByRatingGreaterThanEqual(Double rating);

    List<Place> findByPriceRange(PriceRange priceRange);

    List<Place> findByOpenedDateAfter(LocalDate date);
}