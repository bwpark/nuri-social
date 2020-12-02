package com.nuri.social.service.mapper;


import com.nuri.social.domain.*;
import com.nuri.social.service.dto.DealOptionDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link DealOption} and its DTO {@link DealOptionDTO}.
 */
@Mapper(componentModel = "spring", uses = {DealMapper.class})
public interface DealOptionMapper extends EntityMapper<DealOptionDTO, DealOption> {

    @Mapping(source = "pack.id", target = "packId")
    DealOptionDTO toDto(DealOption dealOption);

    @Mapping(source = "packId", target = "pack")
    DealOption toEntity(DealOptionDTO dealOptionDTO);

    default DealOption fromId(String id) {
        if (id == null) {
            return null;
        }
        DealOption dealOption = new DealOption();
        dealOption.setId(id);
        return dealOption;
    }
}
