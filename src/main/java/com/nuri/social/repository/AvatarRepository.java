package com.nuri.social.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.nuri.social.domain.Avatar;

/**
 * Spring Data MongoDB repository for the Avatar entity.
 */
@Repository
public interface AvatarRepository extends MongoRepository<Avatar, String> {
}
