package com.example.JWTService.Сonfig;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;

@Configuration
@SecurityScheme(
    name = "bearerAuth",
    type = SecuritySchemeType.HTTP,
    bearerFormat = "JWT",
    scheme = "bearer"
)
public class SWGConfig {
    @Bean
    public OpenAPI customApi()
    {
        return new OpenAPI().info(new Info()
                            .title("JWT Auth Service Api")
                            .version("1.0")
                            .description("API for JWT auth with rolse"));
    }
}
