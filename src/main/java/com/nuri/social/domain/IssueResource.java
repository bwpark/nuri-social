package com.nuri.social.domain;

import java.io.Serializable;
import java.time.Instant;

import javax.validation.constraints.NotNull;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.nuri.social.domain.enumeration.ResourceType;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * A IssueResource.
 */
@Document(collection = "issue_resource")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class IssueResource implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	private String id;

	@NotNull
	@Field("type")
	private ResourceType type;

	@NotNull
	@Field("link")
	private String link;

	@NotNull
	@Field("created")
	private Instant created;

	@DBRef
	@Field("issue")
	@JsonIgnoreProperties(value = "resources", allowSetters = true)
	private Issue issue;
}