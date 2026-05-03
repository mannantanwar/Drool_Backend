package com.drool.backend.controller;

import com.drool.backend.dto.AuthRequest;
import com.drool.backend.dto.AuthResponse;
import com.drool.backend.dto.RegisterRequest;
import com.drool.backend.service.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class AuthController {

    private final AuthService authService;


    @PostMapping("/register")
    public AuthResponse register( @Valid @RequestBody RegisterRequest request) {
        return authService.register(request);
    }

    @PostMapping("/login")
    public AuthResponse login(@Valid @RequestBody AuthRequest request ) {
        return authService.login(request);
    }


}

