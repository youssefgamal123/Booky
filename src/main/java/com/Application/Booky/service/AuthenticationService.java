package com.Application.Booky.service;

import com.Application.Booky.Security.AuthenticationRequest;
import com.Application.Booky.Security.AuthenticationResponse;
import com.Application.Booky.Security.JwtService;
import com.Application.Booky.entity.User;
import com.Application.Booky.repository.RoleRepository;
import com.Application.Booky.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final PasswordEncoder passwordEncoder;
    private final RoleRepository roleRepository;
    private final UserRepository userRepository; // Inject the UserRepository

    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;

    public void register(RegisterationRequest request) {

        var userRole = roleRepository.findByname("USER")
                .orElseThrow(() -> new IllegalStateException("ROLE USER was not initiated"));

        var user = User.builder()
                .firstname(request.getFirstname())
                .lastname(request.getLastname())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .accountLocked(false)
                .enabled(false)
                .roles(List.of(userRole))
                .build();

        userRepository.save(user); // Save the user to the database

    }

    public AuthenticationResponse authenticate(AuthenticationRequest request) {

        var auth= authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getEmail(),request.getPassword())
        );

        var claims = new HashMap<String, Object>();
        var user = ((User) auth.getPrincipal());
        var jwtToken = jwtService.generateToken(claims,user);

        return AuthenticationResponse.builder().token(jwtToken).build();



    }




}
