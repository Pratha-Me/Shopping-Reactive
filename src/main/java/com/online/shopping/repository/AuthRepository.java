package com.online.shopping.repository;

import com.online.shopping.model.auth.AuthUser;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthRepository extends ReactiveMongoRepository<AuthUser, String> {
}
