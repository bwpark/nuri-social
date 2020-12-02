package com.nuri.social.service.dto;

import java.io.Serializable;
import java.time.Instant;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.nuri.social.domain.enumeration.PackStatus;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * A DTO for the {@link com.nuri.social.domain.Pack} entity.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PackDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String id;

	@Size(max = 1024)
	private String description;

	@NotNull
	private Integer coin;

	@NotNull
	private Integer point;

	@Size(max = 1024)
	private String shipTo;

	@NotNull
	private PackStatus status;

	@NotNull
	private Instant created;

	@NotNull
	private Instant modified;

	private String meId;
}