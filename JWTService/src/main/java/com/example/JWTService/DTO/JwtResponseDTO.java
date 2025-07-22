package com.example.JWTService.DTO;

import java.util.Set;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class JwtResponseDTO {
    private String accessToken;
    private String refreshToken;
}
