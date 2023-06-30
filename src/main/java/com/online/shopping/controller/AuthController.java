package com.online.shopping.controller;

import com.online.shopping.dto.UserLoginDto;
import com.online.shopping.model.auth.AuthUser;
import com.online.shopping.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import javax.validation.Valid;

/**
 * @author Pramosh Shrestha
 * @created 30/06/2023: 11:30
 */
@RestController
@RequestMapping(value = "/rest/user")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @GetMapping
    public Mono<AuthUser> findAuthUserByEmail(@Valid @RequestBody UserLoginDto userLoginDto) {
        return authService.findUserByEmail(userLoginDto);
    }

}
