package com.nuri.social.service;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.nuri.social.domain.Regular;
import com.nuri.social.repository.RegularRepository;
import com.nuri.social.service.dto.RegularDTO;
import com.nuri.social.service.mapper.RegularMapper;

/**
 * Service Implementation for managing {@link Regular}.
 */
@Service
public class RegularService {

	private final Logger log = LoggerFactory.getLogger(RegularService.class);

	private final RegularRepository regularRepository;

	private final RegularMapper regularMapper;

	public RegularService(RegularRepository regularRepository, RegularMapper regularMapper) {
		this.regularRepository = regularRepository;
		this.regularMapper = regularMapper;
	}

	/**
	 * Save a regular.
	 *
	 * @param regularDTO the entity to save.
	 * @return the persisted entity.
	 */
	public RegularDTO save(RegularDTO regularDTO) {
		log.debug("Request to save Regular : {}", regularDTO);
		Regular regular = regularMapper.toEntity(regularDTO);
		regular = regularRepository.save(regular);
		return regularMapper.toDto(regular);
	}

	/**
	 * Get all the regulars.
	 *
	 * @return the list of entities.
	 */
	public List<RegularDTO> findAll() {
		log.debug("Request to get all Regulars");
		return regularRepository.findAll().stream().map(regularMapper::toDto).collect(Collectors.toCollection(LinkedList::new));
	}

	/**
	 * Get one regular by id.
	 *
	 * @param id the id of the entity.
	 * @return the entity.
	 */
	public Optional<RegularDTO> findOne(String id) {
		log.debug("Request to get Regular : {}", id);
		return regularRepository.findById(id).map(regularMapper::toDto);
	}

	/**
	 * Delete the regular by id.
	 *
	 * @param id the id of the entity.
	 */
	public void delete(String id) {
		log.debug("Request to delete Regular : {}", id);
		regularRepository.deleteById(id);
	}
}
