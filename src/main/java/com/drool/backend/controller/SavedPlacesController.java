package com.drool.backend.controller;

import com.drool.backend.dto.SavedPlaceResponse;
import com.drool.backend.entity.SavedPlace;
import com.drool.backend.service.SavedPlaceService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/saved")
@RequiredArgsConstructor
public class SavedPlacesController {
    private final SavedPlaceService savedPlaceService;

    @GetMapping
    public List<SavedPlaceResponse> getSavedPlaces(Authentication authentication) {
        String email = authentication.getName();
        return savedPlaceService.getSavedPlaces(email);
    }

    @PostMapping("/{placeId}")
    public String savePlace(Authentication authentication, @PathVariable Long placeId) {
        String email =  authentication.getName();
        return savedPlaceService.savePlace(email,placeId);
    }

    @DeleteMapping("/{placeId}")
    public String removeSavedPlace(
            Authentication authentication,
            @PathVariable Long placeId
    ) {
        String userEmail = authentication.getName();
        return savedPlaceService.removePlace(userEmail, placeId);
    }
}
