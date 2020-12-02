package com.nuri.social.domain;

import java.io.Serializable;
import java.time.Instant;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.nuri.social.domain.enumeration.CategoryStatus;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * A Category.
 */
@Document(collection = "category")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Category implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	private String id;

	@NotNull
	@Size(max = 128)
	@Field("path")
	private String path;

	@NotNull
	@Size(max = 128)
	@Field("name")
	private String name;

	@Size(max = 1024)
	@Field("icon")
	private String icon;

	@Size(max = 1024)
	@Field("description")
	private String description;

	@Field("hit_and_sort")
	private Integer hitAndSort;

	@Field("respect")
	private Integer respect;

	@Field("diss")
	private Integer diss;

	@Field("join")
	private Integer join;

	@NotNull
	@Field("status")
	private CategoryStatus status;

	@NotNull
	@CreatedDate
	@Field("created")
	private Instant created;

	@NotNull
	@LastModifiedDate
	@Field("modified")
	private Instant modified;

	@DBRef
	@Field("me")
	@JsonIgnoreProperties(value = "categories", allowSetters = true)
	private Avatar me;
}