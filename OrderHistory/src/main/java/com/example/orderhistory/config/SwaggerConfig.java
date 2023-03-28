package com.example.orderhistory.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

// http://localhost:8081/swagger-ui.html
@Configuration
public class SwaggerConfig {
    @Bean
    public Docket swaggerServiceApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("service")
                .select()
                .apis(RequestHandlerSelectors.any())
                .paths(PathSelectors.regex("\\/ordersHistory\\/(create|.*\\/delivery\\/.*$)"))
                .build();
    }

    @Bean
    public Docket swaggerProviderApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("provider")
                .select()
                .apis(RequestHandlerSelectors.any())
                .paths(PathSelectors.regex("\\/ordersHistory($|\\/\\{id\\}$)"))
                .build();
    }

}
