package com.nuri.social.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.nuri.social.domain.TOS;

/**
 * Spring Data MongoDB repository for the TOS entity.
 */
@Repository
public interface TOSRepository extends MongoRepository<TOS, String> {
}
