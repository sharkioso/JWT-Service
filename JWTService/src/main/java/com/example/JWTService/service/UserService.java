package com.example.JWTService.service;

import com.example.JWTService.entity.ERole;
import com.example.JWTService.entity.Role;
import com.example.JWTService.entity.User;

import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.example.JWTService.DTO.SignUpDTO;
import com.example.JWTService.repository.RoleRepository;
import com.example.JWTService.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    public void createUser(SignUpDTO signUpDTO) {
        User user = new User();
        user.setUsername(signUpDTO.getUsername());
        user.setEmail(signUpDTO.getEmail());
        user.setPassword(signUpDTO.getPassword());
        user.setRoles(resolveRoles(signUpDTO.getRoles()));

        userRepository.save(user);
    }

    private Set<Role> resolveRoles(Set<String> reqRoles) {
        if (reqRoles == null || reqRoles.isEmpty()) {
            return Set.of(getDefaultRole());
        }

        return reqRoles.stream()
                .map(role -> findRoleByName(role))
                .collect(Collectors.toSet());
    }

    private Role getDefaultRole() {
        return roleRepository.findByName(ERole.GUEST)
                .orElseThrow(() -> new IllegalStateException("Gues Role Not Found"));
    }

    private Role findRoleByName(String role) {
        return roleRepository.findByName(ERole.valueOf(role))
                .orElseGet(() -> getDefaultRole());
    }

}
