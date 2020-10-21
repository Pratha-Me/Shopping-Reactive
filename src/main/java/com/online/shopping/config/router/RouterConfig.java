package com.online.shopping.config.router;

import com.online.shopping.handler.AuthHandler;
import com.online.shopping.repository.AuthRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RequestPredicates.POST;
import static org.springframework.web.reactive.function.server.RequestPredicates.accept;

@Configuration
@RequiredArgsConstructor
public class RouterConfig {

    private final AuthRepository authRepository;

    private final AuthHandler authHandler;

    @Bean
    public RouterFunction<ServerResponse> authRoute(){
        return RouterFunctions
                .route(POST("/auth/login").and(accept(MediaType.APPLICATION_JSON)), authHandler::login);
    }
}
