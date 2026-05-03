package com.drool.backend.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@Builder
public class AuthResponse {

    // this is th response sent from the backend to the fronted as a response for their sign in or sign up request
    private Long id;
//    private String username;
    private String name;
    private String email;
    private String avatar;
    private String token;
}
