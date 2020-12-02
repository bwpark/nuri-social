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
import com.nuri.social.domain.enumeration.RegularStatus;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * A Regular.
 */
@Document(collection = "regular")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Regular implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	private String id;

	@NotNull
	@Size(max = 128)
	@Field("name")
	private String name;

	@NotNull
	@Field("status")
	private RegularStatus status;

	@NotNull
	@Field("created")
	private Instant created;

	@NotNull
	@Field("modified")
	private Instant modified;

	@DBRef
	@Field("you")
	@JsonIgnoreProperties(value = "regulars", allowSetters = true)
	private Avatar you;

	@DBRef
	@Field("me")
	@JsonIgnoreProperties(value = "regulars", allowSetters = true)
	private Avatar me;
}