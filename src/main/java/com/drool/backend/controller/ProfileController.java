package com.drool.backend.controller;

import com.drool.backend.dto.UpdateProfileRequest;
import com.drool.backend.dto.UpdateProfileResponse;
import com.drool.backend.service.UpdateProfileService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/profile")
@RequiredArgsConstructor
public class ProfileController {
    private final UpdateProfileService profileService;

    @GetMapping
    public UpdateProfileResponse getCurrentUserProfile(Authentication authentication) {
        String email = authentication.getName();
        return profileService.getCurrentUserProfile(email);
    }

    @PutMapping
    public UpdateProfileResponse updateCurrentUserProfile(
            Authentication authentication,
            @RequestBody UpdateProfileRequest request
    ) {
        String email = authentication.getName();
        return profileService.updateCurrentUserProfile(email, request);
    }

}
