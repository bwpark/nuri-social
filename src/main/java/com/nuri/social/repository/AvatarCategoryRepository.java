package com.nuri.social.repository;

import com.nuri.social.domain.AvatarCategory;

import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 * Spring Data MongoDB repository for the AvatarCategory entity.
 */
@SuppressWarnings("unused")
@Repository
public interface AvatarCategoryRepository extends MongoRepository<AvatarCategory, String> {
}
