package com.Application.Booky.controller;

import com.Application.Booky.Security.AuthenticationRequest;
import com.Application.Booky.Security.AuthenticationResponse;
import com.Application.Booky.service.AuthenticationService;
import com.Application.Booky.service.RegisterationRequest;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("auth")
@RequiredArgsConstructor
@Tag(name = "Authentication")
public class AuthenticationController {

    private final AuthenticationService service;

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody @Valid RegisterationRequest request) {
            service.register(request);
            return ResponseEntity.status(HttpStatus.CREATED).body("User registered successfully");
            // Log the exception

    }



    @PostMapping("/login")
    public ResponseEntity<AuthenticationResponse> authenticate(@RequestBody @Valid AuthenticationRequest request){

        return ResponseEntity.ok(service.authenticate(request));

    }




}
