package com.nuri.social.service.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.nuri.social.domain.IssueResource;
import com.nuri.social.service.dto.IssueResourceDTO;

/**
 * Mapper for the entity {@link IssueResource} and its DTO
 * {@link IssueResourceDTO}.
 */
@Mapper(componentModel = "spring", uses = { IssueMapper.class })
public interface IssueResourceMapper extends EntityMapper<IssueResourceDTO, IssueResource> {

	@Mapping(source = "issue.id", target = "issueId")
	IssueResourceDTO toDto(IssueResource issueResource);

	@Mapping(source = "issueId", target = "issue")
	IssueResource toEntity(IssueResourceDTO issueResourceDTO);

	default IssueResource fromId(String id) {
		if (id == null) {
			return null;
		}
		IssueResource issueResource = new IssueResource();
		issueResource.setId(id);
		return issueResource;
	}
}
