package com.example.JWTService.security.jwt;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

@Component
public class JwtUtils {

    @Value("${app.jwt.secret}")
    private String jwtSecret;

    @Value("${app.jwt.expiration.ms}")
    private int jwtExpirationMs;

    @Value("${app.jwt.refresh.expiration.ms=604800000}")
    private int jwtRefreshDuration;


    public String generateJwtToken(Authentication authentication)
    {
        return "zaglushka";
    }
}
