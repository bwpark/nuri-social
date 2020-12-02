package com.nuri.social.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.nuri.social.domain.Pack;

/**
 * Spring Data MongoDB repository for the Pack entity.
 */
@Repository
public interface PackRepository extends MongoRepository<Pack, String> {
}
