package com.drool.backend.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
@RequiredArgsConstructor
@Slf4j
public class JwtAuthFilter extends OncePerRequestFilter {
    private final JwtService jwtService;
    private final CustomUserDetailsService customUserDetailsService;

    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        final String authHeader = request.getHeader("Authorization");
        final String jwt;
        final String userEmail;

        log.info("Processing request to: {} with Authorization header: {}", 
                 request.getRequestURI(), 
                 authHeader != null ? "Bearer ***" : "null");

        if(authHeader== null || !authHeader.startsWith("Bearer ") ){
            log.warn("No Bearer token found in request to {}", request.getRequestURI());
            filterChain.doFilter(request,response);
            return;
        }

        try {
            jwt = authHeader.substring(7);
            log.debug("JWT token extracted, length: {}", jwt.length());
            
            userEmail= jwtService.extractEmail(jwt);
            log.info("Extracted email from token: {}", userEmail);

            if(userEmail!= null && SecurityContextHolder.getContext().getAuthentication() == null){
                UserDetails userDetails = customUserDetailsService.loadUserByUsername(userEmail);
                log.info("Loaded user details for: {}, Authorities: {}", userEmail, userDetails.getAuthorities());

                if(jwtService.isValidToken(jwt,userDetails)){
                    UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());

                    authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                    SecurityContextHolder.getContext().setAuthentication(authToken);
                    log.info("✓ Authentication successful for user: {} with authorities: {}", userEmail, userDetails.getAuthorities());
                } else {
                    log.error("✗ Invalid token for user: {}", userEmail);
                }
            } else if (userEmail == null) {
                log.error("✗ Could not extract email from token");
            } else {
                log.debug("User already authenticated: {}", SecurityContextHolder.getContext().getAuthentication().getName());
            }
        } catch (Exception e) {
            log.error("✗ Error processing JWT token: {} - {}", e.getClass().getSimpleName(), e.getMessage(), e);
        }
        
        filterChain.doFilter(request,response);
    }
}
