package com.nuri.social.service.dto;

import java.io.Serializable;
import java.time.Instant;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * A DTO for the {@link com.nuri.social.domain.Chemistry} entity.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ChemistryDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String id;

	@NotNull
	@Size(max = 128)
	private String yourName;

	@NotNull
	private Integer toYou;

	@NotNull
	private Integer toMe;

	@NotNull
	private Instant created;

	private String youId;

	private String meId;
}