package com.nuri.social.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.nuri.social.domain.Authority;

/**
 * Spring Data MongoDB repository for the {@link Authority} entity.
 */
public interface AuthorityRepository extends MongoRepository<Authority, String> {
}
