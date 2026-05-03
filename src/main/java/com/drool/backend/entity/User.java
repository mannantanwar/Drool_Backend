package com.drool.backend.entity;


import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "users")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    @Column(nullable=false)
    private String name;

    @Column(nullable=false, unique=true)
    private String email;

    @Column(nullable=false, unique = true)
    private String username;

    @Column(nullable=false)
    private String password;

    private String location;
    private String bio;
    private String avatar;

    @Enumerated(EnumType.STRING)
    private Role role;
}
