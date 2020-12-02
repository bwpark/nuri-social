package com.nuri.social.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.nuri.social.domain.Category;

/**
 * Spring Data MongoDB repository for the Category entity.
 */
@Repository
public interface CategoryRepository extends MongoRepository<Category, String> {
}
