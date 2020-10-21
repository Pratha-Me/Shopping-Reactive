package com.online.shopping.handler;

import com.devglan.springwebfluxjwt.dto.ApiResponse;
import com.online.shopping.dto.AuthUserLoginDto;
import com.online.shopping.repository.AuthRepository;
import com.online.shopping.security.TokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
public class AuthHandler {

    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    private final TokenProvider tokenProvider;

    private final AuthRepository authRepository;

    public Mono<ServerResponse> login(ServerRequest request) {
        Mono<AuthUserLoginDto> authUserDtoMono = request.bodyToMono(AuthUserLoginDto.class);
        return authUserDtoMono.flatMap(authUserLoginDto -> authRepository.findByEmail(authUserLoginDto.getEmail())
                .flatMap(authUser -> {
                    if (bCryptPasswordEncoder.matches(authUserLoginDto.getPassword(), authUser.getPassword())){
                        return ServerResponse.ok()
                                .contentType(MediaType.APPLICATION_JSON)
                                .body(
                                        new ApiResponse(200, "Login Successful", null),
                                        ApiResponse.class
                                );
                    } else {
                        return ServerResponse.badRequest()
                                .body(new ApiResponse(400, "Invalid credentials", null), ApiResponse.class);
                    }
                }).switchIfEmpty(ServerResponse.badRequest()
                        .body(new ApiResponse(400, "User does not exist", null), ApiResponse.class)));
    }
}
