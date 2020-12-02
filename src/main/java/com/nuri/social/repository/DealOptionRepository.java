package com.nuri.social.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.nuri.social.domain.DealOption;

/**
 * Spring Data MongoDB repository for the DealOption entity.
 */
@Repository
public interface DealOptionRepository extends MongoRepository<DealOption, String> {
}
