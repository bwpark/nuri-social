package com.nuri.social.repository;

import com.nuri.social.domain.Pack;

import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 * Spring Data MongoDB repository for the Pack entity.
 */
@SuppressWarnings("unused")
@Repository
public interface PackRepository extends MongoRepository<Pack, String> {
}
