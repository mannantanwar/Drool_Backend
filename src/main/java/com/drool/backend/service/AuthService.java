package com.drool.backend.service;

import com.drool.backend.dto.AuthRequest;
import com.drool.backend.dto.AuthResponse;
import com.drool.backend.dto.RegisterRequest;

public interface AuthService {
   AuthResponse register(RegisterRequest request);
   AuthResponse login(AuthRequest request);
}
