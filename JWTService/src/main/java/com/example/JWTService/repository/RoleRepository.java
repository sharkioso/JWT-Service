package com.example.JWTService.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.JWTService.entity.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {

}
