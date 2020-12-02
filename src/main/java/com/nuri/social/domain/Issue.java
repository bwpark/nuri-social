package com.nuri.social.domain;

import java.io.Serializable;
import java.time.Instant;
import java.util.HashSet;
import java.util.Set;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.nuri.social.domain.enumeration.IssueStatus;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * A Issue.
 */
@Document(collection = "issue")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Issue implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	private String id;

	@Size(max = 128)
	@Field("category_name")
	private String categoryName;

	@NotNull
	@Size(max = 128)
	@Field("name")
	private String name;

	@Size(max = 1024)
	@Field("description")
	private String description;

	@Field("text")
	private String text;

	@NotNull
	@Field("coin")
	private Integer coin;

	@NotNull
	@Field("point")
	private Integer point;

	@NotNull
	@Field("respect")
	private Integer respect;

	@NotNull
	@Field("diss")
	private Integer diss;

	@NotNull
	@Size(max = 128)
	@Field("author")
	private String author;

	@NotNull
	@Field("views")
	private Integer views;

	@NotNull
	@Field("comments")
	private Integer comments;

	@NotNull
	@Field("status")
	private IssueStatus status;

	@NotNull
	@Field("created")
	private Instant created;

	@NotNull
	@Field("modified")
	private Instant modified;

	@Builder.Default
	@DBRef
	@Field("option")
	private Set<IssueOption> options = new HashSet<>();

	@Builder.Default
	@DBRef
	@Field("interact")
	private Set<Interact> interacts = new HashSet<>();

	@Builder.Default
	@DBRef
	@Field("resource")
	private Set<IssueResource> resources = new HashSet<>();

	@Builder.Default
	@DBRef
	@Field("emotion")
	private Set<Emotion> emotions = new HashSet<>();

	@DBRef
	@Field("category")
	@JsonIgnoreProperties(value = "issues", allowSetters = true)
	private Category category;

	@DBRef
	@Field("me")
	@JsonIgnoreProperties(value = "issues", allowSetters = true)
	private Avatar me;
}