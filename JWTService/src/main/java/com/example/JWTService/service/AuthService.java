package com.example.JWTService.service;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.JWTService.DTO.JwtResponseDTO;
import com.example.JWTService.DTO.LoginDTO;
import com.example.JWTService.security.jwt.JwtUtils;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final AuthenticationManager authenticationManager;
    private final JwtUtils jwtUtils;
    private final UserService userService;
    private final PasswordEncoder passwordEncoder;


    public JwtResponseDTO authenicateUser(LoginDTO loginDTO)
    {
        Authentication authentication = authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(loginDTO.getUsername(), loginDTO.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        
        JwtResponseDTO jwtResponseDTO = new JwtResponseDTO();
        jwtResponseDTO.setAccessToken(jwtUtils.generateJwtToken(authentication));
        jwtResponseDTO.setRefreshToken(jwtUtils.generateRefreshJwtToken(authentication));

        return jwtResponseDTO;
    }

    public JwtResponseDTO refreshToken(String refreshToken)
    {
        if(jwtUtils.validateJwtToken(refreshToken))
        {
            String username = jwtUtils.getUserNameFromJwtToken(refreshToken);
            return new JwtResponseDTO(
                jwtUtils.generate)
            )
        }
    }
}
