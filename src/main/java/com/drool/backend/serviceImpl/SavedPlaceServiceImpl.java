package com.drool.backend.serviceImpl;

import com.drool.backend.dto.SavedPlaceResponse;
import com.drool.backend.entity.Place;
import com.drool.backend.entity.SavedPlace;
import com.drool.backend.entity.User;
import com.drool.backend.exception.ResourceNotFoundException;
import com.drool.backend.repository.PlaceRepository;
import com.drool.backend.repository.SavedPlaceRepository;
import com.drool.backend.repository.UserRepository;
import com.drool.backend.service.SavedPlaceService;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class SavedPlaceServiceImpl implements SavedPlaceService {

    private final SavedPlaceRepository savedPlaceRepository;
    private final UserRepository userRepository;
    private final PlaceRepository placeRepository;
    @Override
    public String savePlace(String email, Long placeId) {
        User user = userRepository.findByEmail(email).orElseThrow(()->new ResourceNotFoundException("User not found"));
        Place place = placeRepository.findById(placeId).orElseThrow(()->new ResourceNotFoundException("Place not found"));

        SavedPlace savedPlace = SavedPlace.builder()
                .user(user)
                .place(place)
                .build();

        savedPlaceRepository.save(savedPlace);

        return "Place saved successfully";
    }

    @Override
    public String removePlace(String email, Long placeId) {
        User user = userRepository.findByEmail(email).orElseThrow(()->new ResourceNotFoundException("User not found"));
        Place place = placeRepository.findById(placeId).orElseThrow(()->new ResourceNotFoundException("Place not found"));

        SavedPlace savedPlace = savedPlaceRepository.findByUserAndPlace(user,place).orElseThrow(()->new ResourceNotFoundException("Saved place not found with the given user and place"));

        savedPlaceRepository.delete(savedPlace);
        return "Place removed successfully";
    }

    @Override
    public List<SavedPlaceResponse> getSavedPlaces(String email) {
        User user =  userRepository.findByEmail(email).orElseThrow(()->new ResourceNotFoundException("User not found"));
        return savedPlaceRepository.findByUser(user)
                .stream()
                .map(this::mapToResponse)
                .toList();
    }

    private SavedPlaceResponse mapToResponse(SavedPlace savedPlace) {
        Place place = savedPlace.getPlace();

        return SavedPlaceResponse.builder()
                .savedId(savedPlace.getId())
                .placeId(place.getId())
                .name(place.getName())
                .type(place.getType())
                .cuisine(place.getCuisine())
                .address(place.getAddress())
                .city(place.getCity())
                .state(place.getState())
                .rating(place.getRating())
                .priceRange(place.getPriceRange())
                .avgCostPerPerson(place.getAvgCostPerPerson())
                .imageUrl(place.getImageUrl())
                .tags(place.getTags())
                .savedDate(savedPlace.getSavedDate())
                .build();
    }
}
