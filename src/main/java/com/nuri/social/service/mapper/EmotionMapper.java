package com.nuri.social.service.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.nuri.social.domain.Emotion;
import com.nuri.social.service.dto.EmotionDTO;

/**
 * Mapper for the entity {@link Emotion} and its DTO {@link EmotionDTO}.
 */
@Mapper(componentModel = "spring", uses = { AvatarMapper.class, IssueMapper.class })
public interface EmotionMapper extends EntityMapper<EmotionDTO, Emotion> {

	@Mapping(source = "you.id", target = "youId")
	@Mapping(source = "issue.id", target = "issueId")
	@Mapping(source = "me.id", target = "meId")
	EmotionDTO toDto(Emotion emotion);

	@Mapping(source = "youId", target = "you")
	@Mapping(source = "issueId", target = "issue")
	@Mapping(source = "meId", target = "me")
	Emotion toEntity(EmotionDTO emotionDTO);

	default Emotion fromId(String id) {
		if (id == null) {
			return null;
		}
		Emotion emotion = new Emotion();
		emotion.setId(id);
		return emotion;
	}
}
