package com.nuri.social.repository;

import com.nuri.social.domain.Interact;

import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 * Spring Data MongoDB repository for the Interact entity.
 */
@SuppressWarnings("unused")
@Repository
public interface InteractRepository extends MongoRepository<Interact, String> {
}
