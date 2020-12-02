package com.nuri.social.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.nuri.social.domain.IssueResource;

/**
 * Spring Data MongoDB repository for the IssueResource entity.
 */
@Repository
public interface IssueResourceRepository extends MongoRepository<IssueResource, String> {
}
