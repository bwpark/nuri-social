package com.nuri.social.service.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.nuri.social.domain.Regular;
import com.nuri.social.service.dto.RegularDTO;

/**
 * Mapper for the entity {@link Regular} and its DTO {@link RegularDTO}.
 */
@Mapper(componentModel = "spring", uses = { AvatarMapper.class })
public interface RegularMapper extends EntityMapper<RegularDTO, Regular> {

	@Mapping(source = "you.id", target = "youId")
	@Mapping(source = "me.id", target = "meId")
	RegularDTO toDto(Regular regular);

	@Mapping(source = "youId", target = "you")
	@Mapping(source = "meId", target = "me")
	Regular toEntity(RegularDTO regularDTO);

	default Regular fromId(String id) {
		if (id == null) {
			return null;
		}
		Regular regular = new Regular();
		regular.setId(id);
		return regular;
	}
}
