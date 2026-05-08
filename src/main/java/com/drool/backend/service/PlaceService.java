package com.drool.backend.service;

import com.drool.backend.dto.PlaceRequest;
import com.drool.backend.dto.PlaceResponse;
import com.drool.backend.entity.PlaceType;
import org.springframework.util.PlaceholderResolutionException;

import java.util.List;

public interface PlaceService {
    PlaceResponse createPlace( PlaceRequest request );
    PlaceResponse updatePlace( PlaceRequest request );
    PlaceResponse getPlaceById( Long id );

    List<PlaceResponse> getAllPlaces();

    List<PlaceResponse> searchPlaces(String query);

    List<PlaceResponse> getPlacesByType(PlaceType type);

    List<PlaceResponse> getTrendingPlaces();

    List<PlaceResponse> getPopularPlaces();

    List<PlaceResponse> getJustOpenedPlaces();
}
