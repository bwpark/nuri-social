package com.nuri.social.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.nuri.social.domain.Chemistry;

/**
 * Spring Data MongoDB repository for the Chemistry entity.
 */
@Repository
public interface ChemistryRepository extends MongoRepository<Chemistry, String> {
}
