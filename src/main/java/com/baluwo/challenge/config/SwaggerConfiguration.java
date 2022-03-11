package com.baluwo.challenge.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

import java.util.ArrayList;

@Configuration
@Profile("!pro") // una forma de deshabilitar la página de swagger en el perfil pro
public class SwaggerConfiguration {
    public static final String API_RESPONSE_200 = "OK - The request was successful and the response body contains the representation requested.";
    public static final String API_RESPONSE_201 = "Created - The request was successful, we created a new resource and the response body contains the representation.";
    public static final String API_RESPONSE_204 = "No Content - The request was successful and the response body is empty.";
    public static final String API_RESPONSE_400 = "Bad Request - The data given in the POST failed validation. Inspect the response body for details.";
    public static final String API_RESPONSE_401 = "Unauthorized - The supplied credentials, if any, are not sufficient to access the resource.";
    public static final String API_RESPONSE_404 = "Resource Not Found";
    public static final String API_RESPONSE_409 = "Conflict - The request could not be processed because of conflict in the request";
    public static final String API_RESPONSE_500 = "Internal Server Error - Please try again.";

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.baluwo.challenge"))
                .paths(PathSelectors.any())
                .build()
                .apiInfo(getApiInfo());
    }

    private ApiInfo getApiInfo() {
        return new ApiInfo(
                "Baluwo challenge API",
                "API for Baluwo challenge",
                "v1",
                "",
                new Contact("", "", ""),
                "",
                "",
                new ArrayList<>()
        );
    }
}
