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
import com.nuri.social.domain.enumeration.DealStatus;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * A Deal.
 */
@Document(collection = "deal")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Deal implements Serializable {

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
	@Field("quantity")
	private Integer quantity;

	@NotNull
	@Field("unit_price")
	private Integer unitPrice;

	@NotNull
	@Field("coin")
	private Integer coin;

	@NotNull
	@Field("point")
	private Integer point;

	@NotNull
	@Field("status")
	private DealStatus status;

	@NotNull
	@Field("created")
	private Instant created;

	@NotNull
	@Field("modified")
	private Instant modified;

	@Builder.Default
	@DBRef
	@Field("deal")
	private Set<DealOption> deals = new HashSet<>();

	@DBRef
	@Field("with")
	@JsonIgnoreProperties(value = "deals", allowSetters = true)
	private Issue with;

	@DBRef
	@Field("pack")
	@JsonIgnoreProperties(value = "deals", allowSetters = true)
	private Pack pack;

	@DBRef
	@Field("to")
	@JsonIgnoreProperties(value = "sales", allowSetters = true)
	private Avatar to;
}