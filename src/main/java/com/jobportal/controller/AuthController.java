package com.jobportal.controller;

import com.jobportal.dto.*;
import com.jobportal.service.AuthService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@Tag(name = "Authentication", description = "Register and Login APIs")
public class AuthController {

    @Autowired
    private AuthService authService;

    @Operation(summary = "Register a new user",
               description = "Role can be: ADMIN, COMPANY, JOB_SEEKER")
    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody RegisterRequest request) {
        return ResponseEntity.ok(authService.register(request));
    }

    @Operation(summary = "Login and get JWT token",
               description = "Returns JWT token on successful login")
    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginRequest request) {
        return ResponseEntity.ok(authService.login(request));
    }
}