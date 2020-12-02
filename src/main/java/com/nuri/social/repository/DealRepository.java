package com.nuri.social.repository;

import com.nuri.social.domain.Deal;

import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 * Spring Data MongoDB repository for the Deal entity.
 */
@SuppressWarnings("unused")
@Repository
public interface DealRepository extends MongoRepository<Deal, String> {
}
