package com.nuri.social.service.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.nuri.social.domain.Pack;
import com.nuri.social.service.dto.PackDTO;

/**
 * Mapper for the entity {@link Pack} and its DTO {@link PackDTO}.
 */
@Mapper(componentModel = "spring", uses = { AvatarMapper.class })
public interface PackMapper extends EntityMapper<PackDTO, Pack> {

	@Mapping(source = "me.id", target = "meId")
	PackDTO toDto(Pack pack);

	@Mapping(target = "deals", ignore = true)
	@Mapping(source = "meId", target = "me")
	Pack toEntity(PackDTO packDTO);

	default Pack fromId(String id) {
		if (id == null) {
			return null;
		}
		Pack pack = new Pack();
		pack.setId(id);
		return pack;
	}
}
