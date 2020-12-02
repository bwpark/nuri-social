package com.nuri.social.service;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.nuri.social.domain.Deal;
import com.nuri.social.repository.DealRepository;
import com.nuri.social.service.dto.DealDTO;
import com.nuri.social.service.mapper.DealMapper;

/**
 * Service Implementation for managing {@link Deal}.
 */
@Service
public class DealService {

	private final Logger log = LoggerFactory.getLogger(DealService.class);

	private final DealRepository dealRepository;

	private final DealMapper dealMapper;

	public DealService(DealRepository dealRepository, DealMapper dealMapper) {
		this.dealRepository = dealRepository;
		this.dealMapper = dealMapper;
	}

	/**
	 * Save a deal.
	 *
	 * @param dealDTO the entity to save.
	 * @return the persisted entity.
	 */
	public DealDTO save(DealDTO dealDTO) {
		log.debug("Request to save Deal : {}", dealDTO);
		Deal deal = dealMapper.toEntity(dealDTO);
		deal = dealRepository.save(deal);
		return dealMapper.toDto(deal);
	}

	/**
	 * Get all the deals.
	 *
	 * @return the list of entities.
	 */
	public List<DealDTO> findAll() {
		log.debug("Request to get all Deals");
		return dealRepository.findAll().stream().map(dealMapper::toDto).collect(Collectors.toCollection(LinkedList::new));
	}

	/**
	 * Get one deal by id.
	 *
	 * @param id the id of the entity.
	 * @return the entity.
	 */
	public Optional<DealDTO> findOne(String id) {
		log.debug("Request to get Deal : {}", id);
		return dealRepository.findById(id).map(dealMapper::toDto);
	}

	/**
	 * Delete the deal by id.
	 *
	 * @param id the id of the entity.
	 */
	public void delete(String id) {
		log.debug("Request to delete Deal : {}", id);
		dealRepository.deleteById(id);
	}
}
