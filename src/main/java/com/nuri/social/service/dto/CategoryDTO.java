package com.nuri.social.service.dto;

import java.io.Serializable;
import java.time.Instant;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.nuri.social.domain.enumeration.CategoryStatus;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * A DTO for the {@link com.nuri.social.domain.Category} entity.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CategoryDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String id;

	@NotNull
	@Size(max = 128)
	private String path;

	@NotNull
	@Size(max = 128)
	private String name;

	@Size(max = 1024)
	private String icon;

	@Size(max = 1024)
	private String description;

	private Integer hitAndSort;

	private Integer respect;

	private Integer diss;

	private Integer join;

	@NotNull
	private CategoryStatus status;

	@NotNull
	private Instant created;

	@NotNull
	private Instant modified;

	private String meId;
}