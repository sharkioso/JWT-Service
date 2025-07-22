package com.example.JWTService.security;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.example.JWTService.security.jwt.AuthEntryPoint;
import com.example.JWTService.security.jwt.AuthTokenFilter;

import lombok.RequiredArgsConstructor;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final  AuthEntryPoint authEntryPoint;
    private final UserDetailsService userDetailsService;
    private final AuthTokenFilter authTokenFilter;


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
            http.addFilterBefore(authTokenFilter, UsernamePasswordAuthenticationFilter.class);
            return http.build();

        }


    @Bean
    public AuthenticationProvider authenticationProvider()
    {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider(passwordEncoder());

        authProvider.setUserDetailsService(userDetailsService);
        authProvider.setPasswordEncoder(passwordEncoder());
        return authProvider;
        
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
}
