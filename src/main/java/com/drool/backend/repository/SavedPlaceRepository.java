package com.drool.backend.repository;

import com.drool.backend.entity.Place;
import com.drool.backend.entity.SavedPlace;
import com.drool.backend.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface SavedPlaceRepository extends JpaRepository<SavedPlace, Long> {
    List<SavedPlace> findByUser(User user);
    Optional<SavedPlace> findByUserAndPlace(User user, Place place);
    Boolean existsByUserAndPlace(User user, Place place);
    void deleteByUserAndPlace(User user, Place place);
}