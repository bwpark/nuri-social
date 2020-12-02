package com.nuri.social.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.nuri.social.domain.Regular;

/**
 * Spring Data MongoDB repository for the Regular entity.
 */
@Repository
public interface RegularRepository extends MongoRepository<Regular, String> {
}
