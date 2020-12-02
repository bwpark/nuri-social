package com.nuri.social.domain;

import java.io.Serializable;
import java.time.Instant;

import javax.validation.constraints.NotNull;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.nuri.social.domain.enumeration.EmotionStatus;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * A Emotion.
 */
@Document(collection = "emotion")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Emotion implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	private String id;

	@NotNull
	@Field("status")
	private EmotionStatus status;

	@NotNull
	@Field("created")
	private Instant created;

	@NotNull
	@Field("modified")
	private Instant modified;

	@DBRef
	@Field("you")
	@JsonIgnoreProperties(value = "emotions", allowSetters = true)
	private Avatar you;

	@DBRef
	@Field("issue")
	@JsonIgnoreProperties(value = "emotions", allowSetters = true)
	private Issue issue;

	@DBRef
	@Field("me")
	@JsonIgnoreProperties(value = "emotions", allowSetters = true)
	private Avatar me;
}