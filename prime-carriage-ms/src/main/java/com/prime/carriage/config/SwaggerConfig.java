package com.prime.carriage.config;

import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;


@Configuration
@EnableSwagger2
public class SwaggerConfig {

    public Docket swagConfig() {
        return new Docket(DocumentationType.SWAGGER_2).select()
                .apis(RequestHandlerSelectors.basePackage("com.prime.carriage.controller"))
                .build()
                .apiInfo(getApiInfo());
    }

    private ApiInfo getApiInfo() {
        return new ApiInfo("Order microservice rest api",
                "Documentation for carriage-ms","1.0",
                "Terms of service for using carriage-ms",
                new Contact("Ayaz Nacafli",
                        "gitlab.com/ayaznacafli",
                        "ayaz.nacafli@mail.ru"),
                "Mit Lisence",
                "https://opensource.org/licenses/MIT",
                new ArrayList<>()
        );
    }

}
