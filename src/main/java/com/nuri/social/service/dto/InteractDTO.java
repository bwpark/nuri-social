package com.nuri.social.service.dto;

import java.io.Serializable;
import java.time.Instant;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.nuri.social.domain.enumeration.InteractStatus;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * A DTO for the {@link com.nuri.social.domain.Interact} entity.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class InteractDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String id;

	private String text;

	@NotNull
	private Integer coin;

	@NotNull
	private Integer point;

	@NotNull
	private Integer respect;

	@NotNull
	private Integer diss;

	@NotNull
	@Size(max = 128)
	private String author;

	@NotNull
	private InteractStatus status;

	@NotNull
	private Instant created;

	@NotNull
	private Instant modified;

	private String youId;

	private String issueId;

	private String parentId;

	private String meId;
}