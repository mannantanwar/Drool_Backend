package com.drool.backend.service;

import com.drool.backend.dto.SavedPlaceResponse;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public interface SavedPlaceService {
    String savePlace(String email, Long Place_id);
    String removePlace(String email, Long Place_id);

    List<SavedPlaceResponse> getSavedPlaces(String email);
}
