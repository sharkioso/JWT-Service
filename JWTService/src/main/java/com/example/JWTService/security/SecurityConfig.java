package com.example.JWTService.security;

import java.beans.BeanProperty;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;

import com.example.JWTService.security.jwt.AuthEntryPoint;

import lombok.RequiredArgsConstructor;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final  AuthEntryPoint authEntryPoint;
    private final UserDetailsService userDetailsService;
    private final JwtAuthFilter jwtAuthFilter;


    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
        http    
            .cors(cors -> cors.disable()) //отключил корс потому что предполагаю использование этого сервиса как микросевриса в проекте, а насколько мне известно в таких местах оно отключается
            .csrf(csrf->csrf.disable())
            .exceptionHandling(exception -> exception.authenticationEntryPoint(authEntryPoint))
            .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
            .authorizeHttpRequests(auth->auth.requestMatchers(
                "/api/auth/**",
                "/v3/api-docs",
                "/swagger-ui",
                "/swagger-resources/**"
            ).permitAll()
            .anyRequest().authenticated()
            );
        
            http.authenticationProvider(authenticationProvider());
            http.addFilterBefore(authenticationJwtTokenFilter(), UsernamePasswordAuthenticationFilter.class);
            return http.build();

        }
}
