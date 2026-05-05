package com.drool.backend.serviceImpl;

import com.drool.backend.dto.UpdateProfileResponse;
import com.drool.backend.dto.UpdateProfileRequest;
import com.drool.backend.entity.User;
import com.drool.backend.exception.BadRequestException;
import com.drool.backend.exception.ResourceNotFoundException;
import com.drool.backend.repository.UserRepository;
import com.drool.backend.service.UpdateProfileService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UpdateProfileServiceImpl implements UpdateProfileService {
    private final UserRepository userRepository;
    @Override
    public UpdateProfileResponse getCurrentUserProfile(String email) {
        User user = userRepository.findByEmail(email).orElseThrow(()->new ResourceNotFoundException("User not found with this email"));
        return mapToProfileResponse(user);
    }

    @Override
    public UpdateProfileResponse updateCurrentUserProfile(String email, UpdateProfileRequest request) {
        User user = userRepository.findByEmail(email).orElseThrow(()->new ResourceNotFoundException("User not found with this email"));

        if(request.getUsername()!=null && !request.getUsername().equals(user.getUsername())){
            if(userRepository.existsByUsername(request.getUsername())){
                throw new BadRequestException("Username already exists");
            }
            user.setUsername(request.getUsername());
        }

        if(request.getEmail()!=null && !request.getEmail().equals(user.getEmail())){
            if(userRepository.existsByEmail(request.getEmail())){
                throw new BadRequestException("Email already exists");
            }
            user.setEmail(request.getEmail());
        }

        if(request.getName() != null) {
            user.setName(request.getName());
        }

        if (request.getBio() != null) {
            user.setBio(request.getBio());
        }

        if (request.getLocation() != null) {
            user.setLocation(request.getLocation());
        }

        if (request.getAvatar() != null) {
            user.setAvatar(request.getAvatar());
        }

        User updatedUser = userRepository.save(user);

        return mapToProfileResponse(updatedUser);
    }

    private UpdateProfileResponse mapToProfileResponse(User user) {
        return UpdateProfileResponse.builder()
                .id(user.getId())
                .name(user.getName())
                .username(user.getUsername())
                .email(user.getEmail())
                .avatar(user.getAvatar())
                .bio(user.getBio())
                .location(user.getLocation())
                .build();
    }
}
