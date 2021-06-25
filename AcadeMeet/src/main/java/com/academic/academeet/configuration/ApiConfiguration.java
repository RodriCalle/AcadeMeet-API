package com.academic.academeet.configuration;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApiConfiguration {
    @Bean(name = "acadeMeetApi")
    public OpenAPI acadeMeetApi() {
        return new OpenAPI()
                .components(new Components())
                .info(new Info()
                .title("AcadeMeet API")
                .description("AcadeMeet RESTFul API service developed in Java with Spring Boot and documented using springdoc-openapi."));
    }
}