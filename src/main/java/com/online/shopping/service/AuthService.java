package com.online.shopping.service;

import com.online.shopping.dto.UserLoginDto;
import com.online.shopping.model.auth.AuthUser;
import reactor.core.publisher.Mono;

/**
 * @author Pramosh Shrestha
 * @created 30/06/2023: 11:31
 */
public interface AuthService {

    Mono<AuthUser> findUserByEmail(UserLoginDto userLoginDto);
}
