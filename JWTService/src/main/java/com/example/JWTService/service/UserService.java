package com.example.JWTService.service;


import com.example.JWTService.entity.ERole;
import com.example.JWTService.entity.User;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.JWTService.DTO.SignUpDTO;
import com.example.JWTService.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public void createUser(SignUpDTO signUpDTO){
    }

}
