package com.Application.Booky.Security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.lang.NonNull;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Slf4j
@Component
@RequiredArgsConstructor
public class JwtFilter extends OncePerRequestFilter {

    private final UserDetailsService userDetailsService;
    private final JwtService jwtService;

    @Override
    protected void doFilterInternal(@NonNull HttpServletRequest request, @NonNull HttpServletResponse response, @NonNull FilterChain filterChain) throws ServletException, IOException {

       System.out.println("Path = "+request.getServletPath());
        if (request.getServletPath().contains("/auth")) {
            System.out.println("WhiteListed end point ");
            filterChain.doFilter(request, response);
            return;
        }

        final String authHeader = request.getHeader("Authorization");
        log.info("authHeader={}",authHeader);
        final String jwt;
        final String userEmail;

        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            log.info("No JWT token found in the request header.");
            filterChain.doFilter(request, response);
            return;
        }

        jwt = authHeader.substring(7);
        userEmail = jwtService.extractUsername(jwt);

        if (userEmail != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            log.info("JWT token found. Extracting user details.");
            UserDetails userDetails = this.userDetailsService.loadUserByUsername(userEmail);
            if (jwtService.isTokenValid(jwt, userDetails)) {
                UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
                        userDetails,
                        null,
                        userDetails.getAuthorities()
                );
                authToken.setDetails(
                        new WebAuthenticationDetailsSource().buildDetails(request)
                );
                SecurityContextHolder.getContext().setAuthentication(authToken);
                log.info("User authenticated successfully with email: {}", userEmail);
            } else {
                log.warn("JWT token is invalid or expired.");
            }
        }
        filterChain.doFilter(request, response);
    }
}

/*

Bearer
eyJhbGciOiJIUzM4NCJ9.eyJzdWIiOiJ0ZXN0Mj
JheWFAZ21haWwuY29tIiwiaWF0IjoxNzI1MDM5MjIwLCJleHAiOjE3MjUwNDI4MjAsImF1dGhvcml0aWVzIjpbIlVTRVIiXX0
.jeVMRF66wZBCWq2DWMZ_7J26gjXsVzwA3Qm4p2UET4TFRyIKzXxU1SaJ0IvKghCx


  eyJhbGciOiJIUzM4NCJ9.eyJzdWIiOiJ0ZXN0MjJheWFAZ21haWwuY29tIiwiaWF0IjoxNzI1MDM5MjIwLCJleHAiOjE3MjUwNDI4MjAsImF1dGhvcml0aWVzIjpbIlVTRVIiXX0.
  jeVMRF66wZBCWq2DWMZ_7J26gjXsVzwA3Qm4p2UET4TFRyIKzXxU1SaJ0IvKghCx















 */
