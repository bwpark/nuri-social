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
import com.nuri.social.domain.enumeration.InteractStatus;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * A Interact.
 */
@Document(collection = "interact")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Interact implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	private String id;

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
	@Field("status")
	private InteractStatus status;

	@NotNull
	@Field("created")
	private Instant created;

	@NotNull
	@Field("modified")
	private Instant modified;

	@Builder.Default
	@DBRef
	@Field("child")
	private Set<Interact> children = new HashSet<>();

	@DBRef
	@Field("you")
	@JsonIgnoreProperties(value = "interacts", allowSetters = true)
	private Avatar you;

	@DBRef
	@Field("issue")
	@JsonIgnoreProperties(value = "interacts", allowSetters = true)
	private Issue issue;

	@DBRef
	@Field("parent")
	@JsonIgnoreProperties(value = "children", allowSetters = true)
	private Interact parent;

	@DBRef
	@Field("me")
	@JsonIgnoreProperties(value = "interacts", allowSetters = true)
	private Avatar me;
}