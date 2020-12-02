package com.nuri.social.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.nuri.social.domain.Report;

/**
 * Spring Data MongoDB repository for the Report entity.
 */
@Repository
public interface ReportRepository extends MongoRepository<Report, String> {
}
