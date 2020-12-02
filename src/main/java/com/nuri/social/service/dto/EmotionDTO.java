package com.nuri.social.service.dto;

import java.io.Serializable;
import java.time.Instant;

import javax.validation.constraints.NotNull;

import com.nuri.social.domain.enumeration.EmotionStatus;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * A DTO for the {@link com.nuri.social.domain.Emotion} entity.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EmotionDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String id;

	@NotNull
	private EmotionStatus status;

	@NotNull
	private Instant created;

	@NotNull
	private Instant modified;

	private String youId;

	private String issueId;

	private String meId;
}