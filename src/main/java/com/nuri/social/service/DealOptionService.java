package com.nuri.social.service;

import com.nuri.social.domain.DealOption;
import com.nuri.social.repository.DealOptionRepository;
import com.nuri.social.service.dto.DealOptionDTO;
import com.nuri.social.service.mapper.DealOptionMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Service Implementation for managing {@link DealOption}.
 */
@Service
public class DealOptionService {

    private final Logger log = LoggerFactory.getLogger(DealOptionService.class);

    private final DealOptionRepository dealOptionRepository;

    private final DealOptionMapper dealOptionMapper;

    public DealOptionService(DealOptionRepository dealOptionRepository, DealOptionMapper dealOptionMapper) {
        this.dealOptionRepository = dealOptionRepository;
        this.dealOptionMapper = dealOptionMapper;
    }

    /**
     * Save a dealOption.
     *
     * @param dealOptionDTO the entity to save.
     * @return the persisted entity.
     */
    public DealOptionDTO save(DealOptionDTO dealOptionDTO) {
        log.debug("Request to save DealOption : {}", dealOptionDTO);
        DealOption dealOption = dealOptionMapper.toEntity(dealOptionDTO);
        dealOption = dealOptionRepository.save(dealOption);
        return dealOptionMapper.toDto(dealOption);
    }

    /**
     * Get all the dealOptions.
     *
     * @return the list of entities.
     */
    public List<DealOptionDTO> findAll() {
        log.debug("Request to get all DealOptions");
        return dealOptionRepository.findAll().stream()
            .map(dealOptionMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }


    /**
     * Get one dealOption by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    public Optional<DealOptionDTO> findOne(String id) {
        log.debug("Request to get DealOption : {}", id);
        return dealOptionRepository.findById(id)
            .map(dealOptionMapper::toDto);
    }

    /**
     * Delete the dealOption by id.
     *
     * @param id the id of the entity.
     */
    public void delete(String id) {
        log.debug("Request to delete DealOption : {}", id);
        dealOptionRepository.deleteById(id);
    }
}
