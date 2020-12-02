package com.nuri.social.repository;

import com.nuri.social.domain.TOS;

import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 * Spring Data MongoDB repository for the TOS entity.
 */
@SuppressWarnings("unused")
@Repository
public interface TOSRepository extends MongoRepository<TOS, String> {
}
