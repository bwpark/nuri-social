package com.nuri.social.service.mapper;


import com.nuri.social.domain.*;
import com.nuri.social.service.dto.CategoryDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link Category} and its DTO {@link CategoryDTO}.
 */
@Mapper(componentModel = "spring", uses = {AvatarMapper.class})
public interface CategoryMapper extends EntityMapper<CategoryDTO, Category> {

    @Mapping(source = "me.id", target = "meId")
    CategoryDTO toDto(Category category);

    @Mapping(source = "meId", target = "me")
    Category toEntity(CategoryDTO categoryDTO);

    default Category fromId(String id) {
        if (id == null) {
            return null;
        }
        Category category = new Category();
        category.setId(id);
        return category;
    }
}
