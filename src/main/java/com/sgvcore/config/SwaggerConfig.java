package com.sgvcore.config;

import io.swagger.v3.oas.annotations.enums.SecuritySchemeIn;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.*;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static springfox.documentation.builders.PathSelectors.regex;

@Configuration
@EnableSwagger2
@SecurityScheme(
        name = "bearerAuth",
        scheme = "bearer",
        bearerFormat = "JWT",
        type = SecuritySchemeType.HTTP,
        in = SecuritySchemeIn.HEADER
)

public class SwaggerConfig {

    @Bean
    public Docket svgApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.sgvcore.controllers"))
                .paths(regex("/api.*"))
                .build()
                .apiInfo(metaInfo())
                .securitySchemes(Arrays.asList(apiKey()))
                .securityContexts(Arrays.asList(securityContext()));

    }

    private ApiInfo metaInfo(){
        ApiInfo apiInfo= new ApiInfo(
                "sistema de gestao de viagens",
                "Api para gestao de dados de viajantes em transportes interprovinciais",
                "1.0",
                "Terms of service",
                new Contact("Israel Matusse", "https://github.com/IsraelMatusse",
                        "israelmathusse451@gmail.com"),
                "Apache License Version 2.0",
                "https://www.apache.org/license.html", new ArrayList<VendorExtension>()
        );
        return apiInfo;
    }
    private ApiKey apiKey() {
        return new ApiKey("Bearer Token", "Authorization", "header");
    }

    private SecurityContext securityContext() {
        return SecurityContext.builder()
                .securityReferences(defaultAuth())
                .forPaths(PathSelectors.any())
                .build();
    }

    private List<SecurityReference> defaultAuth() {
        return Arrays.asList(new SecurityReference("Bearer Token", new AuthorizationScope[0]));
    }

}
