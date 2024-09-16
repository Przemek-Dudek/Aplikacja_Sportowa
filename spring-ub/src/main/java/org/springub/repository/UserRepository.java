package org.springub.repository;

import org.springub.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface UserRepository extends MongoRepository<User, String>{
    Optional<User> findByLogin(String login);
    Optional<User> findById(String id);
}

