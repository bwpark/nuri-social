package com.nuri.social.service;

import com.nuri.social.domain.Avatar;
import com.nuri.social.repository.AvatarRepository;
import com.nuri.social.service.dto.AvatarDTO;
import com.nuri.social.service.mapper.AvatarMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * Service Implementation for managing {@link Avatar}.
 */
@Service
public class AvatarService {

    private final Logger log = LoggerFactory.getLogger(AvatarService.class);

    private final AvatarRepository avatarRepository;

    private final AvatarMapper avatarMapper;

    public AvatarService(AvatarRepository avatarRepository, AvatarMapper avatarMapper) {
        this.avatarRepository = avatarRepository;
        this.avatarMapper = avatarMapper;
    }

    /**
     * Save a avatar.
     *
     * @param avatarDTO the entity to save.
     * @return the persisted entity.
     */
    public AvatarDTO save(AvatarDTO avatarDTO) {
        log.debug("Request to save Avatar : {}", avatarDTO);
        Avatar avatar = avatarMapper.toEntity(avatarDTO);
        avatar = avatarRepository.save(avatar);
        return avatarMapper.toDto(avatar);
    }

    /**
     * Get all the avatars.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    public Page<AvatarDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Avatars");
        return avatarRepository.findAll(pageable)
            .map(avatarMapper::toDto);
    }


    /**
     * Get one avatar by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    public Optional<AvatarDTO> findOne(String id) {
        log.debug("Request to get Avatar : {}", id);
        return avatarRepository.findById(id)
            .map(avatarMapper::toDto);
    }

    /**
     * Delete the avatar by id.
     *
     * @param id the id of the entity.
     */
    public void delete(String id) {
        log.debug("Request to delete Avatar : {}", id);
        avatarRepository.deleteById(id);
    }
}
