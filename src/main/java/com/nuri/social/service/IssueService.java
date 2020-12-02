package com.nuri.social.service;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.nuri.social.domain.Issue;
import com.nuri.social.repository.IssueRepository;
import com.nuri.social.service.dto.IssueDTO;
import com.nuri.social.service.mapper.IssueMapper;

/**
 * Service Implementation for managing {@link Issue}.
 */
@Service
public class IssueService {

	private final Logger log = LoggerFactory.getLogger(IssueService.class);

	private final IssueRepository issueRepository;

	private final IssueMapper issueMapper;

	public IssueService(IssueRepository issueRepository, IssueMapper issueMapper) {
		this.issueRepository = issueRepository;
		this.issueMapper = issueMapper;
	}

	/**
	 * Save a issue.
	 *
	 * @param issueDTO the entity to save.
	 * @return the persisted entity.
	 */
	public IssueDTO save(IssueDTO issueDTO) {
		log.debug("Request to save Issue : {}", issueDTO);
		Issue issue = issueMapper.toEntity(issueDTO);
		issue = issueRepository.save(issue);
		return issueMapper.toDto(issue);
	}

	/**
	 * Get all the issues.
	 *
	 * @param pageable the pagination information.
	 * @return the list of entities.
	 */
	public Page<IssueDTO> findAll(Pageable pageable) {
		log.debug("Request to get all Issues");
		return issueRepository.findAll(pageable).map(issueMapper::toDto);
	}

	/**
	 * Get one issue by id.
	 *
	 * @param id the id of the entity.
	 * @return the entity.
	 */
	public Optional<IssueDTO> findOne(String id) {
		log.debug("Request to get Issue : {}", id);
		return issueRepository.findById(id).map(issueMapper::toDto);
	}

	/**
	 * Delete the issue by id.
	 *
	 * @param id the id of the entity.
	 */
	public void delete(String id) {
		log.debug("Request to delete Issue : {}", id);
		issueRepository.deleteById(id);
	}
}
