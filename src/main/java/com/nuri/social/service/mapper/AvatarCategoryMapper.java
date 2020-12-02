package com.nuri.social.service.mapper;


import com.nuri.social.domain.*;
import com.nuri.social.service.dto.AvatarCategoryDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link AvatarCategory} and its DTO {@link AvatarCategoryDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface AvatarCategoryMapper extends EntityMapper<AvatarCategoryDTO, AvatarCategory> {



    default AvatarCategory fromId(String id) {
        if (id == null) {
            return null;
        }
        AvatarCategory avatarCategory = new AvatarCategory();
        avatarCategory.setId(id);
        return avatarCategory;
    }
}
