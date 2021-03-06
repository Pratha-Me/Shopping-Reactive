package com.online.shopping.security;

import io.jsonwebtoken.Claims;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.ReactiveAuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class AuthenticationManager implements ReactiveAuthenticationManager {

    private final TokenProvider tokenProvider;

    @Override
    @SuppressWarnings("unchecked")
    public Mono<Authentication> authenticate(Authentication authentication) {

        String authToken = authentication.getCredentials().toString();
        String username;
        try {
            username = tokenProvider.getUsernameFromToken(authToken);
        } catch (Exception e) {
            username = null;
        }
        if (username != null && ! tokenProvider.isTokenExpired(authToken)) {
            JwtCredentials jwtCredentials = new JwtCredentials();

            Claims claims = tokenProvider.getAllClaimsFromToken(authToken);
            List<String> roles = claims.get("authorities", List.class);
            List<SimpleGrantedAuthority> authorities = roles.stream()
                    .map(SimpleGrantedAuthority::new)
                    .collect(Collectors.toList());

            if (claims.get("userId") != null){
                jwtCredentials.setUserId(claims.get("userId").toString());
            }

            UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(username, jwtCredentials, authorities);
            SecurityContextHolder.getContext().setAuthentication(auth);

            return Mono.just(auth);
        } else {
            return Mono.empty();
        }
    }
}