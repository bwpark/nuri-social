package com.nuri.social.service.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.nuri.social.domain.Avatar;
import com.nuri.social.service.dto.AvatarDTO;

/**
 * Mapper for the entity {@link Avatar} and its DTO {@link AvatarDTO}.
 */
@Mapper(componentModel = "spring", uses = { AvatarCategoryMapper.class, UserMapper.class })
public interface AvatarMapper extends EntityMapper<AvatarDTO, Avatar> {

	@Mapping(source = "category.id", target = "categoryId")
	@Mapping(source = "user.id", target = "userId")
	AvatarDTO toDto(Avatar avatar);

	@Mapping(target = "issues", ignore = true)
	@Mapping(target = "interacts", ignore = true)
	@Mapping(target = "emotions", ignore = true)
	@Mapping(target = "reputes", ignore = true)
	@Mapping(target = "regulars", ignore = true)
	@Mapping(target = "reports", ignore = true)
	@Mapping(target = "chemistries", ignore = true)
	@Mapping(target = "buys", ignore = true)
	@Mapping(target = "sales", ignore = true)
	@Mapping(source = "categoryId", target = "category")
	@Mapping(source = "userId", target = "user")
	
	@Mapping(source = "coin", target = "coin",defaultValue = "0")
	@Mapping(source = "point", target = "point",defaultValue = "0")
	@Mapping(source = "respect", target = "respect",defaultValue = "0")
	@Mapping(source = "diss", target = "diss",defaultValue = "0")
	@Mapping(source = "grade", target = "grade",defaultValue = "0")
	@Mapping(source = "credit", target = "credit",defaultValue = "0")
	@Mapping(source = "views", target = "views",defaultValue = "0")
	@Mapping(source = "comments", target = "comments",defaultValue = "0")
	Avatar toEntity(AvatarDTO avatarDTO);

	default Avatar fromId(String id) {
		if (id == null) {
			return null;
		}
		Avatar avatar = new Avatar();
		avatar.setId(id);
		return avatar;
	}
}
