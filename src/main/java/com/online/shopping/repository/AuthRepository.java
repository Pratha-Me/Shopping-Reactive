package com.online.shopping.repository;

import com.online.shopping.model.auth.AuthUser;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

@Repository
public interface AuthRepository extends ReactiveMongoRepository<AuthUser, String> {

    Mono<AuthUser> findByEmail(String email);
}
