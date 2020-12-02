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

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * A Chemistry.
 */
@Document(collection = "chemistry")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Chemistry implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	private String id;

	@NotNull
	@Size(max = 128)
	@Field("your_name")
	private String yourName;

	@NotNull
	@Field("to_you")
	private Integer toYou;

	@NotNull
	@Field("to_me")
	private Integer toMe;

	@NotNull
	@Field("created")
	private Instant created;

	@DBRef
	@Field("you")
	@JsonIgnoreProperties(value = "chemistries", allowSetters = true)
	private Avatar you;

	@DBRef
	@Field("me")
	@JsonIgnoreProperties(value = "chemistries", allowSetters = true)
	private Avatar me;
}