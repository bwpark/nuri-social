package com.nuri.social.repository;

import com.nuri.social.domain.DealOption;

import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 * Spring Data MongoDB repository for the DealOption entity.
 */
@SuppressWarnings("unused")
@Repository
public interface DealOptionRepository extends MongoRepository<DealOption, String> {
}
