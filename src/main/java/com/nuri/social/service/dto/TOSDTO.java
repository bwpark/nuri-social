package com.nuri.social.service.dto;

import java.io.Serializable;

import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * A DTO for the {@link com.nuri.social.domain.TOS} entity.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TOSDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String id;

	@Size(max = 1024)
	private String policy;

	private String text;

}