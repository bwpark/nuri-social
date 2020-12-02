package com.nuri.social.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.nuri.social.domain.Repute;

/**
 * Spring Data MongoDB repository for the Repute entity.
 */
@Repository
public interface ReputeRepository extends MongoRepository<Repute, String> {
}
