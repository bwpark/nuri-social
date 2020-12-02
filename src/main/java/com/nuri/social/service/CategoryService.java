package com.nuri.social.service;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.nuri.social.domain.Category;
import com.nuri.social.repository.CategoryRepository;
import com.nuri.social.service.dto.CategoryDTO;
import com.nuri.social.service.mapper.CategoryMapper;

/**
 * Service Implementation for managing {@link Category}.
 */
@Service
public class CategoryService {

	private final Logger log = LoggerFactory.getLogger(CategoryService.class);

	private final CategoryRepository categoryRepository;

	private final CategoryMapper categoryMapper;

	public CategoryService(CategoryRepository categoryRepository, CategoryMapper categoryMapper) {
		this.categoryRepository = categoryRepository;
		this.categoryMapper = categoryMapper;
	}

	/**
	 * Save a category.
	 *
	 * @param categoryDTO the entity to save.
	 * @return the persisted entity.
	 */
	public CategoryDTO save(CategoryDTO categoryDTO) {
		log.debug("Request to save Category : {}", categoryDTO);
		Category category = categoryMapper.toEntity(categoryDTO);
		category = categoryRepository.save(category);
		return categoryMapper.toDto(category);
	}

	/**
	 * Get all the categories.
	 *
	 * @return the list of entities.
	 */
	public List<CategoryDTO> findAll() {
		log.debug("Request to get all Categories");
		return categoryRepository.findAll().stream().map(categoryMapper::toDto).collect(Collectors.toCollection(LinkedList::new));
	}

	/**
	 * Get one category by id.
	 *
	 * @param id the id of the entity.
	 * @return the entity.
	 */
	public Optional<CategoryDTO> findOne(String id) {
		log.debug("Request to get Category : {}", id);
		return categoryRepository.findById(id).map(categoryMapper::toDto);
	}

	/**
	 * Delete the category by id.
	 *
	 * @param id the id of the entity.
	 */
	public void delete(String id) {
		log.debug("Request to delete Category : {}", id);
		categoryRepository.deleteById(id);
	}
}
