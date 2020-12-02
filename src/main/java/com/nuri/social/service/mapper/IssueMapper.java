package com.nuri.social.service.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.nuri.social.domain.Issue;
import com.nuri.social.service.dto.IssueDTO;

/**
 * Mapper for the entity {@link Issue} and its DTO {@link IssueDTO}.
 */
@Mapper(componentModel = "spring", uses = { CategoryMapper.class, AvatarMapper.class })
public interface IssueMapper extends EntityMapper<IssueDTO, Issue> {

	@Mapping(source = "category.id", target = "categoryId")
	@Mapping(source = "me.id", target = "meId")
	IssueDTO toDto(Issue issue);

	@Mapping(target = "options", ignore = true)
	@Mapping(target = "interacts", ignore = true)
	@Mapping(target = "resources", ignore = true)
	@Mapping(target = "emotions", ignore = true)
	@Mapping(source = "categoryId", target = "category")
	@Mapping(source = "meId", target = "me")
	Issue toEntity(IssueDTO issueDTO);

	default Issue fromId(String id) {
		if (id == null) {
			return null;
		}
		Issue issue = new Issue();
		issue.setId(id);
		return issue;
	}
}
