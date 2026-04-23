package com.drool.backend.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class AuthRequest {

    // this is sent from the frontend the request DTO in ehich the user sends the username and the password
    @Email
    @NotBlank
    private String username;

    @NotBlank
    private String password;
}
