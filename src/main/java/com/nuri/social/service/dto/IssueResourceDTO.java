package com.nuri.social.service.dto;

import java.io.Serializable;
import java.time.Instant;

import javax.validation.constraints.NotNull;

import com.nuri.social.domain.enumeration.ResourceType;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * A DTO for the {@link com.nuri.social.domain.IssueResource} entity.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class IssueResourceDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String id;

	@NotNull
	private ResourceType type;

	@NotNull
	private String link;

	@NotNull
	private Instant created;

	private String issueId;
}