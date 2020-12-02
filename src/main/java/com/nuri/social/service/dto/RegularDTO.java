package com.nuri.social.service.dto;

import java.io.Serializable;
import java.time.Instant;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.nuri.social.domain.enumeration.RegularStatus;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * A DTO for the {@link com.nuri.social.domain.Regular} entity.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RegularDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String id;

	@NotNull
	@Size(max = 128)
	private String name;

	@NotNull
	private RegularStatus status;

	@NotNull
	private Instant created;

	@NotNull
	private Instant modified;

	private String youId;

	private String meId;
}