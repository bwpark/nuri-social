package com.nuri.social.repository;

import com.nuri.social.domain.Repute;

import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 * Spring Data MongoDB repository for the Repute entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ReputeRepository extends MongoRepository<Repute, String> {
}
