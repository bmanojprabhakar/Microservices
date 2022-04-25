package com.backspace.apigateway.configuration;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApiGatewayConfiguration {
    @Bean
    public RouteLocator gatewayRouter(RouteLocatorBuilder routeBuilder) {
        return routeBuilder.routes()
                .route("currency-exchange-module",
                        p -> p.path("/currency-exchange/**")
                    .uri("lb://currency-exchange-service"))
                .route("currency-conversion-module",
                        p -> p.path("/currency-conversion/**")
                                .uri("lb://currency-conversion-service"))
                .build();
    }
}
