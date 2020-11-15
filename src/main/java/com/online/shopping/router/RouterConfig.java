package com.online.shopping.router;

import com.online.shopping.handler.AuthHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RequestPredicates.*;

@Configuration
@RequiredArgsConstructor
public class RouterConfig {

    private final AuthHandler authHandler;

    @Bean
    public RouterFunction<ServerResponse> authRoute(){
        return RouterFunctions
                .route(POST("/auth/login").and(accept(MediaType.APPLICATION_JSON)), authHandler::login)
                .andRoute(POST("/auth/signup").and(accept(MediaType.APPLICATION_JSON)), authHandler::signUp)
                .andRoute(POST("/auth/verify").and(accept(MediaType.APPLICATION_JSON)), authHandler::verify)
                .andRoute(GET("/auth/user"), authHandler::user);
    }
}
