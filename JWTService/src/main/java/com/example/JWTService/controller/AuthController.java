package com.example.JWTService.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.JWTService.DTO.JwtResponseDTO;
import com.example.JWTService.DTO.LoginDTO;
import com.example.JWTService.DTO.MessageResponseDTO;
import com.example.JWTService.DTO.SignUpDTO;
import com.example.JWTService.service.AuthService;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

@Tag(name = "Auth", description = "Api for auth")
@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
@EnableMethodSecurity
public class AuthController {
    private final AuthService authService;

    @PostMapping("/signin")
    public ResponseEntity<JwtResponseDTO> authentiticateUser(@RequestBody LoginDTO loginDTO) {
        return ResponseEntity.ok(authService.authenicateUser(loginDTO));
    }

    @PostMapping("/signup")
    public ResponseEntity<MessageResponseDTO> registerUser(@RequestBody SignUpDTO signUpDTO) {
        System.out.print("Sign UP ");
        System.out.println(signUpDTO.toString());
        return ResponseEntity.ok(authService.registerUser(signUpDTO));
    }

    @PostMapping("/refresh")
    public ResponseEntity<JwtResponseDTO> refreshUserToken(@RequestHeader("Authorization") String authHeader) {
        return ResponseEntity.ok(authService.refreshToken(authHeader));
    }

    @GetMapping("/all")
    public String publicCheck() {
        return "Public content";
    }

    @GetMapping("/user")
    @PreAuthorize("hasAuthority('ROLE_PREMIUM_USER') or hasAuthority('ROLE_ADMIN')")
    public String userCheck() {
        return "User content";
    }

    @GetMapping("/admin")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public String adminCheck() {
        return "Admin content ";
    }
}
