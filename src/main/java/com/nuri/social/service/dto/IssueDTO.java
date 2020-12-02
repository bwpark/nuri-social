package com.nuri.social.service.dto;

import java.io.Serializable;
import java.time.Instant;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.nuri.social.domain.enumeration.IssueStatus;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * A DTO for the {@link com.nuri.social.domain.Issue} entity.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class IssueDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String id;

	@Size(max = 128)
	private String categoryName;

	@NotNull
	@Size(max = 128)
	private String name;

	@Size(max = 1024)
	private String description;

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
	private Integer views;

	@NotNull
	private Integer comments;

	@NotNull
	private IssueStatus status;

	@NotNull
	private Instant created;

	@NotNull
	private Instant modified;

	private String categoryId;

	private String meId;
}