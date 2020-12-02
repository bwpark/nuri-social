package com.nuri.social.repository;

import com.nuri.social.domain.Avatar;

import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Spring Data MongoDB repository for the Avatar entity.
 */
@SuppressWarnings("unused")
@Repository
public interface AvatarRepository extends MongoRepository<Avatar, String> {
}
