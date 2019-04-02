package com.dgi.dsi.winregistre.swagger2;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.*;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;


@Configuration
@EnableSwagger2
public class SpringFoxConfig {

    @Bean
    public Docket apiDocket() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.dgi.dsi.winregistre"))
                .paths(PathSelectors.any())
                .build()
                .apiInfo(getApiInfo())
                .securitySchemes(this.securitySchemes())
                .securityContexts(Arrays.asList(this.securityContext()));
    }


    public SecurityContext securityContext() {
        AuthorizationScope[] scopes = {new AuthorizationScope("read", "for read operation"),
                new AuthorizationScope("write", "for write operation")};
        List<SecurityReference> securityReferences = Arrays.asList(new SecurityReference("basicAuth", scopes),
                new SecurityReference("Key", scopes), new SecurityReference("User Authentification Token", scopes));
        return SecurityContext.builder().securityReferences(securityReferences)
                .forPaths(PathSelectors.any())
                .build();
    }

    public List<SecurityScheme> securitySchemes() {
        // SecurityScheme basicAuth = new BasicAuth("basicAuth");
        SecurityScheme userAuthToken = new ApiKey("User Authentification Token", "Authorization", "header");
        //  SecurityScheme keyAuth = new ApiKey("Key", "Key", "header");
        return Arrays.asList(
                // keyAuth,
                userAuthToken
                //, basicAuth
        );
    }


    private ApiInfo getApiInfo() {
        Contact contact = new Contact("Direction Générale des  Impôts", "https://impots.finances.gouv.bj", "stolode@finances.bj");
        return new ApiInfoBuilder()
                .title("SERVICE WEB POUR LE SERVICE D'ENREGISTREMENT")
                .description("Tous les services pour les applications la consommation des webservices")
                .version("1.0")
                .license("Impôts Benin 1.0")
                .licenseUrl("http://www.apache.org/licenses/LICENSE-2.0")
                .contact(contact)
                .build();
    }


}
