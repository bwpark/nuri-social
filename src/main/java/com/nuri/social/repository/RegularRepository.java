package com.nuri.social.repository;

import com.nuri.social.domain.Regular;

import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 * Spring Data MongoDB repository for the Regular entity.
 */
@SuppressWarnings("unused")
@Repository
public interface RegularRepository extends MongoRepository<Regular, String> {
}
