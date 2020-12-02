package com.nuri.social.service.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.nuri.social.domain.IssueOption;
import com.nuri.social.service.dto.IssueOptionDTO;

/**
 * Mapper for the entity {@link IssueOption} and its DTO {@link IssueOptionDTO}.
 */
@Mapper(componentModel = "spring", uses = { IssueMapper.class })
public interface IssueOptionMapper extends EntityMapper<IssueOptionDTO, IssueOption> {

	@Mapping(source = "issue.id", target = "issueId")
	IssueOptionDTO toDto(IssueOption issueOption);

	@Mapping(source = "issueId", target = "issue")
	IssueOption toEntity(IssueOptionDTO issueOptionDTO);

	default IssueOption fromId(String id) {
		if (id == null) {
			return null;
		}
		IssueOption issueOption = new IssueOption();
		issueOption.setId(id);
		return issueOption;
	}
}
