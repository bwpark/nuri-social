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
import com.nuri.social.domain.enumeration.PackStatus;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * A Pack.
 */
@Document(collection = "pack")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Pack implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	private String id;

	@Size(max = 1024)
	@Field("description")
	private String description;

	@NotNull
	@Field("coin")
	private Integer coin;

	@NotNull
	@Field("point")
	private Integer point;

	@Size(max = 1024)
	@Field("ship_to")
	private String shipTo;

	@NotNull
	@Field("status")
	private PackStatus status;

	@NotNull
	@Field("created")
	private Instant created;

	@NotNull
	@Field("modified")
	private Instant modified;

	@Builder.Default
	@DBRef
	@Field("deal")
	private Set<Deal> deals = new HashSet<>();

	@DBRef
	@Field("me")
	@JsonIgnoreProperties(value = "buys", allowSetters = true)
	private Avatar me;
}