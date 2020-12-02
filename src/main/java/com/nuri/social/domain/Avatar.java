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
import com.nuri.social.domain.enumeration.AvatarStatus;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * A Avatar.
 */
@Document(collection = "avatar")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Avatar implements Serializable {

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

	@Field("logo")
	private byte[] logo;

	@Field("logo_content_type")
	private String logoContentType;

	@Field("banner")
	private byte[] banner;

	@Field("banner_content_type")
	private String bannerContentType;

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
	@Field("grade")
	private Integer grade;

	@NotNull
	@Field("credit")
	private Integer credit;

	@NotNull
	@Field("views")
	private Integer views;

	@NotNull
	@Field("comments")
	private Integer comments;

	@NotNull
	@Field("status")
	private AvatarStatus status;

	@NotNull
	@Field("created")
	private Instant created;

	@NotNull
	@Field("modified")
	private Instant modified;

	@Builder.Default
	@DBRef
	@Field("issue")
	private Set<Issue> issues = new HashSet<>();

	@Builder.Default
	@DBRef
	@Field("interact")
	private Set<Interact> interacts = new HashSet<>();

	@Builder.Default
	@DBRef
	@Field("emotion")
	private Set<Emotion> emotions = new HashSet<>();

	@Builder.Default
	@DBRef
	@Field("repute")
	private Set<Repute> reputes = new HashSet<>();

	@Builder.Default
	@DBRef
	@Field("regular")
	private Set<Regular> regulars = new HashSet<>();

	@Builder.Default
	@DBRef
	@Field("report")
	private Set<Report> reports = new HashSet<>();

	@Builder.Default
	@DBRef
	@Field("chemistry")
	private Set<Chemistry> chemistries = new HashSet<>();

	@Builder.Default
	@DBRef
	@Field("buy")
	private Set<Pack> buys = new HashSet<>();

	@Builder.Default
	@DBRef
	@Field("sale")
	private Set<Deal> sales = new HashSet<>();

	@DBRef
	@Field("category")
	@JsonIgnoreProperties(value = "avatars", allowSetters = true)
	private AvatarCategory category;

	@DBRef
	@Field("user")
	@JsonIgnoreProperties(value = "avatars", allowSetters = true)
	private User user;

}