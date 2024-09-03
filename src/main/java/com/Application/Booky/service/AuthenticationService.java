package com.Application.Booky.service;

import com.Application.Booky.Security.AuthenticationRequest;
import com.Application.Booky.Security.AuthenticationResponse;
import com.Application.Booky.Security.JwtService;
import com.Application.Booky.entity.Token;
import com.Application.Booky.entity.User;
import com.Application.Booky.repository.RoleRepository;
import com.Application.Booky.repository.TokenRepository;
import com.Application.Booky.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class AuthenticationService {

    private final PasswordEncoder passwordEncoder;
    private final RoleRepository roleRepository;
    private final UserRepository userRepository; // Inject the UserRepository

    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;

    private final TokenRepository tokenRepository;

    public void register(RegisterationRequest request) {

        var userRole = roleRepository.findByname("USER")
                .orElseThrow(() -> new IllegalStateException("ROLE USER was not initiated"));

        var user = User.builder()
                .firstname(request.getFirstname())
                .lastname(request.getLastname())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .accountLocked(false)
                .role(userRole)
                .enabled(false)
                .build();

        userRepository.save(user); // Save the user to the database

    }

    public AuthenticationResponse authenticate(AuthenticationRequest request) {

        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword())
            );

            UserDetails userDetails = (UserDetails) authentication.getPrincipal();
            String jwtToken = jwtService.generateToken(userDetails);

            log.info("User authenticated successfully. Generating JWT token for email: {}", request.getEmail());
            return AuthenticationResponse.builder()
                    .token(jwtToken)
                    .build();
        } catch (Exception e) {
            log.error("Authentication failed for email: {}", request.getEmail(), e);
            throw e;
        }
    }


        // activating user by username
    public void activateAccount(String Username) {

        var user = userRepository.findByemail(Username).orElseThrow(() -> new UsernameNotFoundException("User not found."));
        user.setEnabled(true);
        userRepository.save(user);
        System.out.println("User enabled successfully");

    }
}





