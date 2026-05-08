package com.drool.backend.controller;

import com.drool.backend.dto.PlaceRequest;
import com.drool.backend.dto.PlaceResponse;
import com.drool.backend.entity.PlaceType;
import com.drool.backend.service.PlaceService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/places")
@RequiredArgsConstructor
public class PlaceController {

    private final PlaceService placeService;

    @PostMapping
    public PlaceResponse createPlace(@RequestBody PlaceRequest request) {
        return placeService.createPlace(request);
    }

    @GetMapping
    public List<PlaceResponse> getAllPlaces() {
        return placeService.getAllPlaces();
    }

    @GetMapping("/{id}")
    public PlaceResponse getPlaceById(@PathVariable Long id) {
        return placeService.getPlaceById(id);
    }

    @GetMapping("/search")
    public List<PlaceResponse> searchPlaces(@RequestParam String q) {
        return placeService.searchPlaces(q);
    }

    @GetMapping("/type/{type}")
    public List<PlaceResponse> getPlacesByType(@PathVariable PlaceType type) {
        return placeService.getPlacesByType(type);
    }

    @GetMapping("/trending")
    public List<PlaceResponse> getTrendingPlaces() {
        return placeService.getTrendingPlaces();
    }

    @GetMapping("/popular")
    public List<PlaceResponse> getPopularPlaces() {
        return placeService.getPopularPlaces();
    }

    @GetMapping("/just-opened")
    public List<PlaceResponse> getJustOpenedPlaces() {
        return placeService.getJustOpenedPlaces();
    }
}