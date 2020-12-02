package com.nuri.social.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.nuri.social.domain.Interact;

/**
 * Spring Data MongoDB repository for the Interact entity.
 */
@Repository
public interface InteractRepository extends MongoRepository<Interact, String> {
}
