package com.drool.backend.service;

import com.drool.backend.dto.UpdateProfileResponse;
import com.drool.backend.dto.UpdateProfileRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
public interface UpdateProfileService {
    UpdateProfileResponse getCurrentUserProfile( String email);
    UpdateProfileResponse updateCurrentUserProfile(String email, UpdateProfileRequest request);
}
