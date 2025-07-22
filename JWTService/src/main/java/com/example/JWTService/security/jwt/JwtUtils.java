package com.example.JWTService.security.jwt;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class JwtUtils {

    @Value("${app.jwt.secret}")
    private String jwtSecret;
}
