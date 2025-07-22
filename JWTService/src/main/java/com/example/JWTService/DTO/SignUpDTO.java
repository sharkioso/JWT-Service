package com.example.JWTService.DTO;

import java.util.Set;

import jakarta.validation.constraints.Email;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SignUpDTO {
    private String username;
    
    @Email
    private String email;

    private String password;
    private Set<String> roles;
}
