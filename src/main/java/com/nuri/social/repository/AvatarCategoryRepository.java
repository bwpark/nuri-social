package com.nuri.social.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.nuri.social.domain.AvatarCategory;

/**
 * Spring Data MongoDB repository for the AvatarCategory entity.
 */
@Repository
public interface AvatarCategoryRepository extends MongoRepository<AvatarCategory, String> {
}
