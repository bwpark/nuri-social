package com.nuri.social.repository;

import com.nuri.social.domain.IssueOption;

import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 * Spring Data MongoDB repository for the IssueOption entity.
 */
@SuppressWarnings("unused")
@Repository
public interface IssueOptionRepository extends MongoRepository<IssueOption, String> {
}
