package com.example.JWTService.service;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.example.JWTService.DTO.JwtResponseDTO;
import com.example.JWTService.DTO.LoginDTO;
import com.example.JWTService.DTO.MessageResponseDTO;
import com.example.JWTService.DTO.SignUpDTO;
import com.example.JWTService.security.jwt.JwtUtils;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final AuthenticationManager authenticationManager;
    private final JwtUtils jwtUtils;
    private final UserService userService;


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
                jwtUtils.generateTokenFromName(username),
                refreshToken);
        }
        throw new RuntimeException("invalid refresh token");
    }

    public MessageResponseDTO registerUser(SignUpDTO signUpDTO)
    {
        userService.createUser(signUpDTO);
        return new MessageResponseDTO("Register successfully");

    }
}
