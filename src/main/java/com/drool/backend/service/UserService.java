package com.drool.backend.service;

import com.drool.backend.entity.User;

public interface UserService {
    User getUserByEmail(String email);
}
