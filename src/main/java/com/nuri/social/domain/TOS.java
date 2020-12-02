package com.nuri.social.domain;

import java.io.Serializable;

import javax.validation.constraints.Size;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * A TOS.
 */
@Document(collection = "tos")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TOS implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	private String id;

	@Size(max = 1024)
	@Field("policy")
	private String policy;

	@Field("text")
	private String text;
}