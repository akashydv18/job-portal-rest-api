package com.jobportal.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import io.swagger.v3.oas.models.Components;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
            .info(new Info()
                .title("Job Portal REST API")
                .version("1.0")
                .description("A production-ready Job Portal backend with JWT Authentication, Role-Based Access Control, and Pagination.")
                .contact(new Contact()
                    .name("Aakash Kumar Yadav")
                    .email("aakashrajyaduvanshi7@gmail.com")
                    .url("https://github.com/akashydv18")
                )
            )
            .addSecurityItem(new SecurityRequirement()
                .addList("Bearer Authentication")
            )
            .components(new Components()
                .addSecuritySchemes("Bearer Authentication",
                    new SecurityScheme()
                        .type(SecurityScheme.Type.HTTP)
                        .scheme("bearer")
                        .bearerFormat("JWT")
                        .description("JWT token daalo — Bearer mat likho, sirf token paste karo!")
                )
            );
    }
}