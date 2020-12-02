package com.nuri.social.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.nuri.social.domain.Deal;

/**
 * Spring Data MongoDB repository for the Deal entity.
 */
@Repository
public interface DealRepository extends MongoRepository<Deal, String> {
}
