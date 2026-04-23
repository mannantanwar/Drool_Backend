package com.drool.backend.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class RegisterRequest {

    // this is the Dto entered by the user upon registering the user in the table

    @NotBlank
    private String username;

    @NotBlank
    private String password;

    @NotBlank
    private String name ;

    @NotBlank
    private String email;
}
