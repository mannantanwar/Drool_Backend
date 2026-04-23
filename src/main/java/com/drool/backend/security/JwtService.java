package com.drool.backend.security;

import io.jsonwebtoken.Claims;
import lombok.Value;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
public class JwtService {

    @Value("${jwt.secret}")
    private String secretKey;

    @Value("${jwt.expiration}")
    private long jwtExpiration;

    public String extractUsername( String token){
        return extractClaim(token, Claims::getSubject);
    }

    public <T> T extractClaims(String token , Function<Claims,T>claimsResolver){
        final Claims clain = extractAllClaims(token);
        return claimsResolver.apply(clain);
    }


}
