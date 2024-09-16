package org.springdb.repository;

import org.springdb.model.Team;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface TeamRepository extends MongoRepository<Team, String> {
    Optional<Team> findById(String id);
    Optional<Team> findByName(String name);
}
