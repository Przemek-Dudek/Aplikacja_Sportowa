package org.springub.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springub.model.User;

import java.util.Optional;

public interface UserRepository extends MongoRepository<User, String> {
    Optional<User> findByLogin(String login);
    Optional<User> findById(String id);
}

