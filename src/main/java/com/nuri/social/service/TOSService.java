package com.nuri.social.service;

import com.nuri.social.domain.TOS;
import com.nuri.social.repository.TOSRepository;
import com.nuri.social.service.dto.TOSDTO;
import com.nuri.social.service.mapper.TOSMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Service Implementation for managing {@link TOS}.
 */
@Service
public class TOSService {

    private final Logger log = LoggerFactory.getLogger(TOSService.class);

    private final TOSRepository tOSRepository;

    private final TOSMapper tOSMapper;

    public TOSService(TOSRepository tOSRepository, TOSMapper tOSMapper) {
        this.tOSRepository = tOSRepository;
        this.tOSMapper = tOSMapper;
    }

    /**
     * Save a tOS.
     *
     * @param tOSDTO the entity to save.
     * @return the persisted entity.
     */
    public TOSDTO save(TOSDTO tOSDTO) {
        log.debug("Request to save TOS : {}", tOSDTO);
        TOS tOS = tOSMapper.toEntity(tOSDTO);
        tOS = tOSRepository.save(tOS);
        return tOSMapper.toDto(tOS);
    }

    /**
     * Get all the tOS.
     *
     * @return the list of entities.
     */
    public List<TOSDTO> findAll() {
        log.debug("Request to get all TOS");
        return tOSRepository.findAll().stream()
            .map(tOSMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }


    /**
     * Get one tOS by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    public Optional<TOSDTO> findOne(String id) {
        log.debug("Request to get TOS : {}", id);
        return tOSRepository.findById(id)
            .map(tOSMapper::toDto);
    }

    /**
     * Delete the tOS by id.
     *
     * @param id the id of the entity.
     */
    public void delete(String id) {
        log.debug("Request to delete TOS : {}", id);
        tOSRepository.deleteById(id);
    }
}
