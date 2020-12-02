package com.nuri.social.web.rest;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nuri.social.service.AvatarCategoryService;
import com.nuri.social.service.dto.AvatarCategoryDTO;
import com.nuri.social.web.rest.errors.BadRequestAlertException;

import io.github.jhipster.web.util.HeaderUtil;
import io.github.jhipster.web.util.ResponseUtil;

/**
 * REST controller for managing {@link com.nuri.social.domain.AvatarCategory}.
 */
@RestController
@RequestMapping("/api")
public class AvatarCategoryResource {

	private final Logger log = LoggerFactory.getLogger(AvatarCategoryResource.class);

	private static final String ENTITY_NAME = "avatarCategory";

	@Value("${jhipster.clientApp.name}")
	private String applicationName;

	private final AvatarCategoryService avatarCategoryService;

	public AvatarCategoryResource(AvatarCategoryService avatarCategoryService) {
		this.avatarCategoryService = avatarCategoryService;
	}

	/**
	 * {@code POST  /avatar-categories} : Create a new avatarCategory.
	 *
	 * @param avatarCategoryDTO the avatarCategoryDTO to create.
	 * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with
	 *         body the new avatarCategoryDTO, or with status
	 *         {@code 400 (Bad Request)} if the avatarCategory has already an ID.
	 * @throws URISyntaxException if the Location URI syntax is incorrect.
	 */
	@PostMapping("/avatar-categories")
	public ResponseEntity<AvatarCategoryDTO> createAvatarCategory(@Valid @RequestBody AvatarCategoryDTO avatarCategoryDTO) throws URISyntaxException {
		log.debug("REST request to save AvatarCategory : {}", avatarCategoryDTO);
		if (avatarCategoryDTO.getId() != null) {
			throw new BadRequestAlertException("A new avatarCategory cannot already have an ID", ENTITY_NAME, "idexists");
		}
		AvatarCategoryDTO result = avatarCategoryService.save(avatarCategoryDTO);
		return ResponseEntity.created(new URI("/api/avatar-categories/" + result.getId()))
				.headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId())).body(result);
	}

	/**
	 * {@code PUT  /avatar-categories} : Updates an existing avatarCategory.
	 *
	 * @param avatarCategoryDTO the avatarCategoryDTO to update.
	 * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body
	 *         the updated avatarCategoryDTO, or with status
	 *         {@code 400 (Bad Request)} if the avatarCategoryDTO is not valid, or
	 *         with status {@code 500 (Internal Server Error)} if the
	 *         avatarCategoryDTO couldn't be updated.
	 * @throws URISyntaxException if the Location URI syntax is incorrect.
	 */
	@PutMapping("/avatar-categories")
	public ResponseEntity<AvatarCategoryDTO> updateAvatarCategory(@Valid @RequestBody AvatarCategoryDTO avatarCategoryDTO) throws URISyntaxException {
		log.debug("REST request to update AvatarCategory : {}", avatarCategoryDTO);
		if (avatarCategoryDTO.getId() == null) {
			throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
		}
		AvatarCategoryDTO result = avatarCategoryService.save(avatarCategoryDTO);
		return ResponseEntity.ok().headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, avatarCategoryDTO.getId())).body(result);
	}

	/**
	 * {@code GET  /avatar-categories} : get all the avatarCategories.
	 *
	 * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list
	 *         of avatarCategories in body.
	 */
	@GetMapping("/avatar-categories")
	public List<AvatarCategoryDTO> getAllAvatarCategories() {
		log.debug("REST request to get all AvatarCategories");
		return avatarCategoryService.findAll();
	}

	/**
	 * {@code GET  /avatar-categories/:id} : get the "id" avatarCategory.
	 *
	 * @param id the id of the avatarCategoryDTO to retrieve.
	 * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body
	 *         the avatarCategoryDTO, or with status {@code 404 (Not Found)}.
	 */
	@GetMapping("/avatar-categories/{id}")
	public ResponseEntity<AvatarCategoryDTO> getAvatarCategory(@PathVariable String id) {
		log.debug("REST request to get AvatarCategory : {}", id);
		Optional<AvatarCategoryDTO> avatarCategoryDTO = avatarCategoryService.findOne(id);
		return ResponseUtil.wrapOrNotFound(avatarCategoryDTO);
	}

	/**
	 * {@code DELETE  /avatar-categories/:id} : delete the "id" avatarCategory.
	 *
	 * @param id the id of the avatarCategoryDTO to delete.
	 * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
	 */
	@DeleteMapping("/avatar-categories/{id}")
	public ResponseEntity<Void> deleteAvatarCategory(@PathVariable String id) {
		log.debug("REST request to delete AvatarCategory : {}", id);
		avatarCategoryService.delete(id);
		return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id)).build();
	}
}
