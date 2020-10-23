package com.online.shopping.handler;

import com.online.shopping.dto.ApiResponse;
import com.online.shopping.dto.AuthUserLoginDto;
import com.online.shopping.dto.AuthUserOtpDto;
import com.online.shopping.model.auth.AuthUser;
import com.online.shopping.model.auth.Role;
import com.online.shopping.model.auth.Status;
import com.online.shopping.repository.AuthRepository;
import com.online.shopping.security.TokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import java.util.Date;

import static org.springframework.http.MediaType.APPLICATION_JSON;

@Component
@RequiredArgsConstructor
public class AuthHandler {

    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    private final TokenProvider tokenProvider;

    private final AuthRepository authRepository;

    private final ReactiveMongoTemplate mongoTemplate;

    public Mono<ServerResponse> login(ServerRequest request) {
        Mono<AuthUserLoginDto> userLoginDto = request.bodyToMono(AuthUserLoginDto.class);
        return userLoginDto.flatMap(authUserLoginDto -> authRepository.findByEmail(authUserLoginDto.getEmail())
                .flatMap(authUser -> {
                    if (bCryptPasswordEncoder.matches(authUserLoginDto.getPassword(), authUser.getPassword())){
                        return ServerResponse
                                .ok()
                                .contentType(MediaType.APPLICATION_JSON)
                                .header(HttpHeaders.AUTHORIZATION, tokenProvider.generateToken(authUser))
                                .body(BodyInserters.fromValue(new ApiResponse(200, "Login Successful", null)));
                    } else {
                        return ServerResponse
                                .badRequest()
                                .body(BodyInserters.fromValue(new ApiResponse(400, "Invalid credentials", null)));
                    }
                }).switchIfEmpty(ServerResponse.badRequest()
                        .body(BodyInserters.fromValue(new ApiResponse(400, "User does not exist", null)))));
    }

    public Mono<ServerResponse> signUp(ServerRequest request) {
        Mono<AuthUser> authUserMono = request.bodyToMono(AuthUser.class);

        return authUserMono.map(authUser -> {
            authUser.setRole(Role.ROLE_USER)
                    .setPassword(bCryptPasswordEncoder.encode(authUser.getPassword()))
                    .setStatus(Status.NOT_VERIFIED)
                    .setOtp(com.karyathalo.pudo.util.StringUtils.generateOtp())
                    .setUserId(com.karyathalo.pudo.util.StringUtils.getRandomUserId())
                    .setOtpDate(new Date())
                    .setJoinedDate(new Date());
            return authUser;
        }).flatMap(authUser -> authRepository.findByEmail(authUser.getEmail())
//                Use this to insert Body in response body.
                .flatMap(dbUser -> ServerResponse.badRequest()
                        .body(BodyInserters.fromValue(new ApiResponse(400, "User already exist", null))))
                .switchIfEmpty(authRepository.save(authUser)
                        .flatMap(savedUser -> ServerResponse.status(HttpStatus.CREATED)
                                .contentType(APPLICATION_JSON)
                                .body(BodyInserters.fromValue(new ApiResponse(201, "User is created", null))))));
    }

    public Mono<ServerResponse> verify(ServerRequest request) {
        Mono<AuthUserOtpDto> dtoMono = request.bodyToMono(AuthUserOtpDto.class);
        return dtoMono.flatMap(dto -> authRepository.findByEmail(dto.getEmail())
                .flatMap(authUser -> {
                    if (dto.getOtp().equals(authUser.getOtp())){
                        authUser.setStatus(Status.VERIFIED)
                                .setOtp(null)
                                .setOtpDate(null);
                        mongoTemplate.save(authUser);
                    } else {
                        System.out.println("Error is caught, now it needs to be thrown and handled aptly");
                        Mono.error(RuntimeException::new);
                    }
                    return ServerResponse.ok()
                            .body(BodyInserters.fromValue( new ApiResponse(200, "Verified", null)));
                })
                .switchIfEmpty(ServerResponse.badRequest()
                        .body(BodyInserters.fromValue(new ApiResponse(400, "User does not exist", null)))));
    }
}
