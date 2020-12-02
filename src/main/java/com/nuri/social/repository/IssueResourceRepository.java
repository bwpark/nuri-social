package com.nuri.social.repository;

import com.nuri.social.domain.IssueResource;

import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 * Spring Data MongoDB repository for the IssueResource entity.
 */
@SuppressWarnings("unused")
@Repository
public interface IssueResourceRepository extends MongoRepository<IssueResource, String> {
}
