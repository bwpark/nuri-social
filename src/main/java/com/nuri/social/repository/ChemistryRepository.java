package com.nuri.social.repository;

import com.nuri.social.domain.Chemistry;

import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 * Spring Data MongoDB repository for the Chemistry entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ChemistryRepository extends MongoRepository<Chemistry, String> {
}
