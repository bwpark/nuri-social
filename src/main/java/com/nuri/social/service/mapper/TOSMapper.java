package com.nuri.social.service.mapper;

import org.mapstruct.Mapper;

import com.nuri.social.domain.TOS;
import com.nuri.social.service.dto.TOSDTO;

/**
 * Mapper for the entity {@link TOS} and its DTO {@link TOSDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface TOSMapper extends EntityMapper<TOSDTO, TOS> {

	default TOS fromId(String id) {
		if (id == null) {
			return null;
		}
		TOS tOS = new TOS();
		tOS.setId(id);
		return tOS;
	}
}
