package com.nuri.social.service;

import com.nuri.social.domain.AvatarCategory;
import com.nuri.social.repository.AvatarCategoryRepository;
import com.nuri.social.service.dto.AvatarCategoryDTO;
import com.nuri.social.service.mapper.AvatarCategoryMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Service Implementation for managing {@link AvatarCategory}.
 */
@Service
public class AvatarCategoryService {

    private final Logger log = LoggerFactory.getLogger(AvatarCategoryService.class);

    private final AvatarCategoryRepository avatarCategoryRepository;

    private final AvatarCategoryMapper avatarCategoryMapper;

    public AvatarCategoryService(AvatarCategoryRepository avatarCategoryRepository, AvatarCategoryMapper avatarCategoryMapper) {
        this.avatarCategoryRepository = avatarCategoryRepository;
        this.avatarCategoryMapper = avatarCategoryMapper;
    }

    /**
     * Save a avatarCategory.
     *
     * @param avatarCategoryDTO the entity to save.
     * @return the persisted entity.
     */
    public AvatarCategoryDTO save(AvatarCategoryDTO avatarCategoryDTO) {
        log.debug("Request to save AvatarCategory : {}", avatarCategoryDTO);
        AvatarCategory avatarCategory = avatarCategoryMapper.toEntity(avatarCategoryDTO);
        avatarCategory = avatarCategoryRepository.save(avatarCategory);
        return avatarCategoryMapper.toDto(avatarCategory);
    }

    /**
     * Get all the avatarCategories.
     *
     * @return the list of entities.
     */
    public List<AvatarCategoryDTO> findAll() {
        log.debug("Request to get all AvatarCategories");
        return avatarCategoryRepository.findAll().stream()
            .map(avatarCategoryMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }


    /**
     * Get one avatarCategory by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    public Optional<AvatarCategoryDTO> findOne(String id) {
        log.debug("Request to get AvatarCategory : {}", id);
        return avatarCategoryRepository.findById(id)
            .map(avatarCategoryMapper::toDto);
    }

    /**
     * Delete the avatarCategory by id.
     *
     * @param id the id of the entity.
     */
    public void delete(String id) {
        log.debug("Request to delete AvatarCategory : {}", id);
        avatarCategoryRepository.deleteById(id);
    }
}
