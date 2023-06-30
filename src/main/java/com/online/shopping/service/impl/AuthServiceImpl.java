package com.online.shopping.service.impl;

import com.online.shopping.dto.ApiResponse;
import com.online.shopping.dto.UserLoginDto;
import com.online.shopping.model.auth.AuthUser;
import com.online.shopping.repo.AuthRepository;
import com.online.shopping.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

/**
 * @author Pramosh Shrestha
 * @created 30/06/2023: 11:35
 */
@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final AuthRepository authRepository;

    @Override
    public Mono<AuthUser> findUserByEmail(UserLoginDto userLoginDto) {
        Mono<AuthUser> byEmail = authRepository.findByEmail(userLoginDto.getEmail());
        return byEmail.switchIfEmpty(Mono.just(new AuthUser().setEmail("Not found")));
    }
}
