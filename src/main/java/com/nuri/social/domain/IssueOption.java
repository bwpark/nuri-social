package com.nuri.social.domain;

import java.io.Serializable;
import java.time.Instant;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.nuri.social.domain.enumeration.IssueOptionStatus;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * A IssueOption.
 */
@Document(collection = "issue_option")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class IssueOption implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	private String id;

	@NotNull
	@Size(max = 128)
	@Field("name")
	private String name;

	@Size(max = 1024)
	@Field("description")
	private String description;

	@NotNull
	@Field("coin")
	private Integer coin;

	@NotNull
	@Field("point")
	private Integer point;

	@NotNull
	@Field("status")
	private IssueOptionStatus status;

	@NotNull
	@Field("created")
	private Instant created;

	@NotNull
	@Field("modified")
	private Instant modified;

	@DBRef
	@Field("issue")
	@JsonIgnoreProperties(value = "options", allowSetters = true)
	private Issue issue;
}