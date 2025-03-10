package com.smartagilify.core.environments;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Collections;

@Configuration
@EnableSwagger2
public class Swagger2Configuration {

    @Bean
    public Docket swaggerConfig() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.gamification.core.controllers"))
                .build()
                .apiInfo(apiInfoDetails());
    }

    private ApiInfo apiInfoDetails() {
        return new ApiInfo("gamification",
                "This is a feature of scrume methology APIs",
                "1.0",
                "127.0.0.1:9092",
                new Contact("smartagilify",
                        "http:\\smartagilify.com",
                        "smartagilify@gmail.com"),
                "API licence",
                "http:\\smartagilify.ir",
                Collections.emptyList());
    }

}
