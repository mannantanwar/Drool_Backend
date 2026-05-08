package com.drool.backend.serviceImpl;

import com.drool.backend.dto.PlaceRequest;
import com.drool.backend.dto.PlaceResponse;
import com.drool.backend.entity.Place;
import com.drool.backend.entity.PlaceType;
import com.drool.backend.exception.ResourceNotFoundException;
import com.drool.backend.repository.PlaceRepository;
import com.drool.backend.service.PlaceService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PlaceServiceImpl implements PlaceService {
    private final PlaceRepository placeRepository;

    @Override
    public PlaceResponse createPlace(PlaceRequest request) {
        Place place = Place.builder()
                .name(request.getName())
                .cuisine(request.getCuisine())
                .type(request.getType())
                .address(request.getAddress())
                .city(request.getCity())
                .state(request.getState())
                .latitude(request.getLatitude())
                .longitude(request.getLongitude())
                .rating(request.getRating())
                .reviewsCount(request.getReviewsCount())
                .priceRange(request.getPriceRange())
                .avgCostPerPerson(request.getAvgCostPerPerson())
                .imageUrl(request.getImageUrl())
                .tags(request.getTags())
                .openedDate(request.getOpenedDate())
                .currentVisitors(request.getCurrentVisitors())
                .popularityScore(request.getPopularityScore())
                .trendingScore(request.getTrendingScore())
                .trustScore(request.getTrustScore())
                .build();

        return mapToResponse(placeRepository.save(place));
    }

    @Override
    public PlaceResponse updatePlace(PlaceRequest request) {
        return null;
    }

    @Override
    public PlaceResponse getPlaceById(Long id) {
        Place place = placeRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("cant find the place with id "+ id));
        return mapToResponse(place);
    }

    @Override
    public List<PlaceResponse> getAllPlaces() {
        return placeRepository.findAll()
                .stream()
                .map(this::mapToResponse)
                .toList();
    }

    @Override
    public List<PlaceResponse> searchPlaces(String query) {
        return placeRepository.findByNameContainingIgnoreCase(query)
                .stream()
                .map(this::mapToResponse)
                .toList();
    }

    @Override
    public List<PlaceResponse> getPlacesByType(PlaceType type) {
        return placeRepository.findByType(type)
                .stream()
                .map(this::mapToResponse)
                .toList();

    }

    @Override
    public List<PlaceResponse> getTrendingPlaces() {
        return placeRepository.findAll()
                .stream()
                .sorted(Comparator.comparing(Place::getTrendingScore).reversed())
                .limit(10)
                .map(this::mapToResponse)
                .toList();
    }

    @Override
    public List<PlaceResponse> getPopularPlaces() {
        return placeRepository.findAll()
                .stream()
                .sorted(Comparator.comparing(Place::getPopularityScore).reversed())
                .limit(10)
                .map(this::mapToResponse)
                .toList();
    }

    @Override
    public List<PlaceResponse> getJustOpenedPlaces() {
        LocalDate thirtyDaysAgo = LocalDate.now().minusDays(30);

        return placeRepository.findByOpenedDateAfter(thirtyDaysAgo)
                .stream()
                .map(this::mapToResponse)
                .toList();
    }
    private PlaceResponse mapToResponse(Place place) {
        return PlaceResponse.builder()
                .id(place.getId())
                .name(place.getName())
                .cuisine(place.getCuisine())
                .type(place.getType())
                .address(place.getAddress())
                .city(place.getCity())
                .state(place.getState())
                .latitude(place.getLatitude())
                .longitude(place.getLongitude())
                .rating(place.getRating())
                .reviewsCount(place.getReviewsCount())
                .priceRange(place.getPriceRange())
                .avgCostPerPerson(place.getAvgCostPerPerson())
                .imageUrl(place.getImageUrl())
                .tags(place.getTags())
                .openedDate(place.getOpenedDate())
                .currentVisitors(place.getCurrentVisitors())
                .popularityScore(place.getPopularityScore())
                .trendingScore(place.getTrendingScore())
                .trustScore(place.getTrustScore())
                .build();
    }
}
