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

import com.nuri.social.service.ChemistryService;
import com.nuri.social.service.dto.ChemistryDTO;
import com.nuri.social.web.rest.errors.BadRequestAlertException;

import io.github.jhipster.web.util.HeaderUtil;
import io.github.jhipster.web.util.ResponseUtil;

/**
 * REST controller for managing {@link com.nuri.social.domain.Chemistry}.
 */
@RestController
@RequestMapping("/api")
public class ChemistryResource {

	private final Logger log = LoggerFactory.getLogger(ChemistryResource.class);

	private static final String ENTITY_NAME = "chemistry";

	@Value("${jhipster.clientApp.name}")
	private String applicationName;

	private final ChemistryService chemistryService;

	public ChemistryResource(ChemistryService chemistryService) {
		this.chemistryService = chemistryService;
	}

	/**
	 * {@code POST  /chemistries} : Create a new chemistry.
	 *
	 * @param chemistryDTO the chemistryDTO to create.
	 * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with
	 *         body the new chemistryDTO, or with status {@code 400 (Bad Request)}
	 *         if the chemistry has already an ID.
	 * @throws URISyntaxException if the Location URI syntax is incorrect.
	 */
	@PostMapping("/chemistries")
	public ResponseEntity<ChemistryDTO> createChemistry(@Valid @RequestBody ChemistryDTO chemistryDTO) throws URISyntaxException {
		log.debug("REST request to save Chemistry : {}", chemistryDTO);
		if (chemistryDTO.getId() != null) {
			throw new BadRequestAlertException("A new chemistry cannot already have an ID", ENTITY_NAME, "idexists");
		}
		ChemistryDTO result = chemistryService.save(chemistryDTO);
		return ResponseEntity.created(new URI("/api/chemistries/" + result.getId()))
				.headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId())).body(result);
	}

	/**
	 * {@code PUT  /chemistries} : Updates an existing chemistry.
	 *
	 * @param chemistryDTO the chemistryDTO to update.
	 * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body
	 *         the updated chemistryDTO, or with status {@code 400 (Bad Request)} if
	 *         the chemistryDTO is not valid, or with status
	 *         {@code 500 (Internal Server Error)} if the chemistryDTO couldn't be
	 *         updated.
	 * @throws URISyntaxException if the Location URI syntax is incorrect.
	 */
	@PutMapping("/chemistries")
	public ResponseEntity<ChemistryDTO> updateChemistry(@Valid @RequestBody ChemistryDTO chemistryDTO) throws URISyntaxException {
		log.debug("REST request to update Chemistry : {}", chemistryDTO);
		if (chemistryDTO.getId() == null) {
			throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
		}
		ChemistryDTO result = chemistryService.save(chemistryDTO);
		return ResponseEntity.ok().headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, chemistryDTO.getId())).body(result);
	}

	/**
	 * {@code GET  /chemistries} : get all the chemistries.
	 *
	 * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list
	 *         of chemistries in body.
	 */
	@GetMapping("/chemistries")
	public List<ChemistryDTO> getAllChemistries() {
		log.debug("REST request to get all Chemistries");
		return chemistryService.findAll();
	}

	/**
	 * {@code GET  /chemistries/:id} : get the "id" chemistry.
	 *
	 * @param id the id of the chemistryDTO to retrieve.
	 * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body
	 *         the chemistryDTO, or with status {@code 404 (Not Found)}.
	 */
	@GetMapping("/chemistries/{id}")
	public ResponseEntity<ChemistryDTO> getChemistry(@PathVariable String id) {
		log.debug("REST request to get Chemistry : {}", id);
		Optional<ChemistryDTO> chemistryDTO = chemistryService.findOne(id);
		return ResponseUtil.wrapOrNotFound(chemistryDTO);
	}

	/**
	 * {@code DELETE  /chemistries/:id} : delete the "id" chemistry.
	 *
	 * @param id the id of the chemistryDTO to delete.
	 * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
	 */
	@DeleteMapping("/chemistries/{id}")
	public ResponseEntity<Void> deleteChemistry(@PathVariable String id) {
		log.debug("REST request to delete Chemistry : {}", id);
		chemistryService.delete(id);
		return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id)).build();
	}
}
