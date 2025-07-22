package com.example.JWTService.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/api/v1/test")
public class TestController {

    @GetMapping("/all")
    public String publicCheck()
    {
        return "Public content";
    }

    @GetMapping("/user")
    @PreAuthorize("hasRole('PREMIUM_USER') or hasRole('ADMIN')")
    public String userCheck()
    {
        return "User content";
    }

    @GetMapping("/Admin")
    @PreAuthorize("hasRole('ADMIN')")
    public String adminCheck()
    {
        return "Admin content ";
    }

}
