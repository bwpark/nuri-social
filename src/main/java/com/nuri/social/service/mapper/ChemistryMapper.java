package com.nuri.social.service.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.nuri.social.domain.Chemistry;
import com.nuri.social.service.dto.ChemistryDTO;

/**
 * Mapper for the entity {@link Chemistry} and its DTO {@link ChemistryDTO}.
 */
@Mapper(componentModel = "spring", uses = { AvatarMapper.class })
public interface ChemistryMapper extends EntityMapper<ChemistryDTO, Chemistry> {

	@Mapping(source = "you.id", target = "youId")
	@Mapping(source = "me.id", target = "meId")
	ChemistryDTO toDto(Chemistry chemistry);

	@Mapping(source = "youId", target = "you")
	@Mapping(source = "meId", target = "me")
	Chemistry toEntity(ChemistryDTO chemistryDTO);

	default Chemistry fromId(String id) {
		if (id == null) {
			return null;
		}
		Chemistry chemistry = new Chemistry();
		chemistry.setId(id);
		return chemistry;
	}
}
