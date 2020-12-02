package com.nuri.social.repository;

import com.nuri.social.domain.Issue;

import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 * Spring Data MongoDB repository for the Issue entity.
 */
@SuppressWarnings("unused")
@Repository
public interface IssueRepository extends MongoRepository<Issue, String> {
}
