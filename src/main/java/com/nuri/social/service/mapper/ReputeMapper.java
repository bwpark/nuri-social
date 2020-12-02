package com.nuri.social.service.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.nuri.social.domain.Repute;
import com.nuri.social.service.dto.ReputeDTO;

/**
 * Mapper for the entity {@link Repute} and its DTO {@link ReputeDTO}.
 */
@Mapper(componentModel = "spring", uses = { AvatarMapper.class })
public interface ReputeMapper extends EntityMapper<ReputeDTO, Repute> {

	@Mapping(source = "you.id", target = "youId")
	@Mapping(source = "me.id", target = "meId")
	ReputeDTO toDto(Repute repute);

	@Mapping(source = "youId", target = "you")
	@Mapping(source = "meId", target = "me")
	Repute toEntity(ReputeDTO reputeDTO);

	default Repute fromId(String id) {
		if (id == null) {
			return null;
		}
		Repute repute = new Repute();
		repute.setId(id);
		return repute;
	}
}
