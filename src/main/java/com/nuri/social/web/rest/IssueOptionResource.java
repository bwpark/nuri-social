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

import com.nuri.social.service.IssueOptionService;
import com.nuri.social.service.dto.IssueOptionDTO;
import com.nuri.social.web.rest.errors.BadRequestAlertException;

import io.github.jhipster.web.util.HeaderUtil;
import io.github.jhipster.web.util.ResponseUtil;

/**
 * REST controller for managing {@link com.nuri.social.domain.IssueOption}.
 */
@RestController
@RequestMapping("/api")
public class IssueOptionResource {

	private final Logger log = LoggerFactory.getLogger(IssueOptionResource.class);

	private static final String ENTITY_NAME = "issueOption";

	@Value("${jhipster.clientApp.name}")
	private String applicationName;

	private final IssueOptionService issueOptionService;

	public IssueOptionResource(IssueOptionService issueOptionService) {
		this.issueOptionService = issueOptionService;
	}

	/**
	 * {@code POST  /issue-options} : Create a new issueOption.
	 *
	 * @param issueOptionDTO the issueOptionDTO to create.
	 * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with
	 *         body the new issueOptionDTO, or with status {@code 400 (Bad Request)}
	 *         if the issueOption has already an ID.
	 * @throws URISyntaxException if the Location URI syntax is incorrect.
	 */
	@PostMapping("/issue-options")
	public ResponseEntity<IssueOptionDTO> createIssueOption(@Valid @RequestBody IssueOptionDTO issueOptionDTO) throws URISyntaxException {
		log.debug("REST request to save IssueOption : {}", issueOptionDTO);
		if (issueOptionDTO.getId() != null) {
			throw new BadRequestAlertException("A new issueOption cannot already have an ID", ENTITY_NAME, "idexists");
		}
		IssueOptionDTO result = issueOptionService.save(issueOptionDTO);
		return ResponseEntity.created(new URI("/api/issue-options/" + result.getId()))
				.headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId())).body(result);
	}

	/**
	 * {@code PUT  /issue-options} : Updates an existing issueOption.
	 *
	 * @param issueOptionDTO the issueOptionDTO to update.
	 * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body
	 *         the updated issueOptionDTO, or with status {@code 400 (Bad Request)}
	 *         if the issueOptionDTO is not valid, or with status
	 *         {@code 500 (Internal Server Error)} if the issueOptionDTO couldn't be
	 *         updated.
	 * @throws URISyntaxException if the Location URI syntax is incorrect.
	 */
	@PutMapping("/issue-options")
	public ResponseEntity<IssueOptionDTO> updateIssueOption(@Valid @RequestBody IssueOptionDTO issueOptionDTO) throws URISyntaxException {
		log.debug("REST request to update IssueOption : {}", issueOptionDTO);
		if (issueOptionDTO.getId() == null) {
			throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
		}
		IssueOptionDTO result = issueOptionService.save(issueOptionDTO);
		return ResponseEntity.ok().headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, issueOptionDTO.getId())).body(result);
	}

	/**
	 * {@code GET  /issue-options} : get all the issueOptions.
	 *
	 * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list
	 *         of issueOptions in body.
	 */
	@GetMapping("/issue-options")
	public List<IssueOptionDTO> getAllIssueOptions() {
		log.debug("REST request to get all IssueOptions");
		return issueOptionService.findAll();
	}

	/**
	 * {@code GET  /issue-options/:id} : get the "id" issueOption.
	 *
	 * @param id the id of the issueOptionDTO to retrieve.
	 * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body
	 *         the issueOptionDTO, or with status {@code 404 (Not Found)}.
	 */
	@GetMapping("/issue-options/{id}")
	public ResponseEntity<IssueOptionDTO> getIssueOption(@PathVariable String id) {
		log.debug("REST request to get IssueOption : {}", id);
		Optional<IssueOptionDTO> issueOptionDTO = issueOptionService.findOne(id);
		return ResponseUtil.wrapOrNotFound(issueOptionDTO);
	}

	/**
	 * {@code DELETE  /issue-options/:id} : delete the "id" issueOption.
	 *
	 * @param id the id of the issueOptionDTO to delete.
	 * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
	 */
	@DeleteMapping("/issue-options/{id}")
	public ResponseEntity<Void> deleteIssueOption(@PathVariable String id) {
		log.debug("REST request to delete IssueOption : {}", id);
		issueOptionService.delete(id);
		return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id)).build();
	}
}
